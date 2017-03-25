package com.mygaienko.common.algorithms.condingame.tan_network;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private static Map<String, StopArea> stops = new HashMap<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String startPoint = in.next();
        String endPoint = in.next();

        readStops(in);
        readRoutes(in);

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println("IMPOSSIBLE");
    }

    private static void readRoutes(Scanner in) {
        int M = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < M; i++) {
            parseRoute(in.nextLine());
        }
    }

    private static void parseRoute(String route) {
        String[] ids = route.split(",");
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
            parseStop(in.nextLine());
        }
    }

    private static void parseStop(String s) {
        String[] attributes = s.split(",");
        StopArea stopArea = new StopArea(
                attributes[0], attributes[1], attributes[2],
                attributes[3], attributes[4], attributes[5],
                attributes[6], attributes[7], attributes[8]
        );
        stops.put(stopArea.getId(), stopArea);
    }

    private static class StopArea {

        private final String id;
        private final String fullName;
        private final String description;
        private final String latitude;
        private final String longitude;
        private final String zoneId;
        private final String url;
        private final String type;
        private final String motherStation;

        private Set<StopArea> nextStops = new HashSet<>();

        public StopArea(String id, String fullName, String description,
                        String latitude, String longitude, String zoneId,
                        String url, String type, String motherStation) {
            this.id = id;
            this.fullName = fullName;
            this.description = description;
            this.latitude = latitude;
            this.longitude = longitude;
            this.zoneId = zoneId;
            this.url = url;
            this.type = type;
            this.motherStation = motherStation;
        }

        public String getId() {
            return id;
        }

        public String getFullName() {
            return fullName;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getType() {
            return type;
        }

        public void addRoute(StopArea nextStop) {
            nextStops.add(nextStop);
        }

        public Set<StopArea> getNextStops() {
            return nextStops;
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