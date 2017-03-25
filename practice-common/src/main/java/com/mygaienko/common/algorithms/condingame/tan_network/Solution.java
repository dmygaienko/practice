package com.mygaienko.common.algorithms.condingame.tan_network;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private static List<String> rawStops = new ArrayList<>();
    private static List<String> rawRoutes = new ArrayList<>();

    private static Map<String, StopArea> stops = new HashMap<>();

    public static void main(String args[]) {
       /* Scanner in = new Scanner(System.in);
        String startStopId = in.next();
        String endStopId = in.next();*/

        String startStopId = "StopArea:ABDU";
        String endStopId = "StopArea:ABLA";

        TanUtil.initRaw(rawStops, rawRoutes);
        /*readStops(in);
        readRoutes(in);*/
        parseRaw();

        fillTargetDistances(endStopId);

        List<StopArea> path = aStar(stops.get(startStopId), stops.get(endStopId));
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        printPath(path);
    }

    private static void printPath(List<StopArea> path) {
        System.out.println(path.isEmpty() ? "IMPOSSIBLE" :
                path.stream()
                        .map(stop -> stop.getDescription().replaceAll("\"", ""))
                        .collect(Collectors.joining("\n")));
    }

    public static List<StopArea> aStar(StopArea startStopArea, StopArea targetStopArea) {
        Set<StopArea> openQueue = new HashSet<>();
        Set<StopArea> closedQueue = new HashSet<>();

        Map<StopArea, StopArea> previous = new HashMap<>();
        Map<StopArea, Double> gScore = new HashMap<>();
        Map<StopArea, Double> fScore = new HashMap<>();

        openQueue.add(startStopArea);
        gScore.put(startStopArea, 0d);

        while (!openQueue.isEmpty()) {
            StopArea currentStopArea = findSmallestStopArea(openQueue, fScore);

            if (currentStopArea.equals(targetStopArea)) {
                return reconstructPath(currentStopArea, previous);
            }

            openQueue.remove(currentStopArea);
            closedQueue.add(currentStopArea);

            for (StopArea nextStopArea : currentStopArea.nextStops) {
                if (closedQueue.contains(nextStopArea)) {
                    continue; //ignore  the neighbour which is already evaluated
                }

                Double tentativeGScore = gScore.get(currentStopArea) + countDistance(currentStopArea, nextStopArea);
                if (!openQueue.add(nextStopArea)) {
                    if (tentativeGScore >= gScore.get(nextStopArea)) {
                        continue; // this is not the best path
                    }
                }

                //this path is best until now
                previous.put(nextStopArea, currentStopArea);
                gScore.put(nextStopArea, tentativeGScore);
                fScore.put(nextStopArea, tentativeGScore + nextStopArea.getTargetDistance());
            }
        }

        return Collections.EMPTY_LIST;
    }

    private static List<StopArea> reconstructPath(StopArea current, Map<StopArea, StopArea> previouses) {
        List<StopArea> path = new ArrayList<>();

        while (current != null) {
            path.add(current);
            current = previouses.get(current);
        }

        Collections.reverse(path);
        return path;
    }

    private static StopArea findSmallestStopArea(Set<StopArea> open, Map<StopArea, Double> fScores) {
        return open.stream()
                .reduce((a, b) -> fScores.get(a) < fScores.get(b) ? a : b)
                .get();
    }

    private static void parseRaw() {
        rawStops.forEach(Solution::parseStop);
        rawRoutes.forEach(Solution::parseRoute);
    }

    private static void fillTargetDistances(String targetStopId) {
        StopArea targetStop = stops.get(targetStopId);
        stops.values().forEach(stop -> stop.setTargetDistance(countDistance(stop, targetStop)));
    }

    private static double countDistance(StopArea a, StopArea b) {
        double x = (b.longitude - a.longitude) * cos((a.latitude + b.latitude)/2);
        double y = (b.latitude - a.latitude);
        return sqrt(pow(x, 2) + pow(y, 2)) * 6371;
    }

    private static void readRoutes(Scanner in) {
        int M = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < M; i++) {
            rawRoutes.add(in.nextLine());
        }
    }

    private static void parseRoute(String route) {
        String[] ids = route.split(" ");
        String startStopId = ids[0];
        String endStopId = ids[1];

        StopArea startStopArea = stops.get(startStopId);
        StopArea endStopArea = stops.get(endStopId);
        startStopArea.addRoute(endStopArea);
    }

    private static void readStops(Scanner in) {
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < N; i++) {
            rawStops.add(in.nextLine());
        }
    }

    private static void parseStop(String s) {
        String[] attributes = s.split(",");
        StopArea stopArea = new StopArea(
                attributes[0], attributes[1], attributes[2],
                attributes[3], attributes[4], attributes[5],
                attributes[6], attributes[7]
        );
        stops.put(stopArea.id, stopArea);
    }

    static class StopArea {

        private final String id;
        private final String fullName;
        private final String description;
        private final double latitude;
        private final double longitude;
        private final String zoneId;
        private final String url;
        private final String type;

        private final Set<StopArea> nextStops = new HashSet<>();
        private final Map<StopArea, Double> nextStopsDistances = new HashMap<>();

        private double targetDistance;

        StopArea(String id, String description, String fullName,
                 String latitude, String longitude, String zoneId,
                 String url, String type) {
            this.id = id;
            this.description = description;
            this.fullName = fullName;
            this.latitude = toRadians(Double.parseDouble(latitude));
            this.longitude = toRadians(Double.parseDouble(longitude));
            this.zoneId = zoneId;
            this.url = url;
            this.type = type;
        }

        void addRoute(StopArea nextStop) {
            nextStops.add(nextStop);
            nextStopsDistances.put(this, countDistance(this, nextStop));
        }

        public String getDescription() {
            return description;
        }

        double getDistanceTo(StopArea nextStop) {
            return nextStopsDistances.get(nextStop);
        }

        public Set<StopArea> getNextStops() {
            return nextStops;
        }

        public double getTargetDistance() {
            return targetDistance;
        }

        public void setTargetDistance(double targetDistance) {
            this.targetDistance = targetDistance;
        }

        @Override
        public String toString() {
            return "id='" + id + '\'' +
                    ", lat=" + latitude +
                    ", long=" + longitude +
                    ", dist=" + targetDistance +
                    ", nextSt=" + nextStops.stream().reduce(
                            new StringBuilder(),
                            (builder, stopArea) -> builder.append(stopArea.id),
                            (a, b) -> a.append(b.toString())).toString() +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            StopArea stopArea = (StopArea) o;

            return id != null ? id.equals(stopArea.id) : stopArea.id == null;

        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }

    }
}