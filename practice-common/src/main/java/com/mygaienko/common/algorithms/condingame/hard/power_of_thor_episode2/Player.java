package com.mygaienko.common.algorithms.condingame.hard.power_of_thor_episode2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by dmygaenko on 03/04/2017.
 */
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int TX = in.nextInt();
        int TY = in.nextInt();

        Position thorPosition = new Position(TX, TY);
        // game loop
        while (true) {
            int strikes = in.nextInt(); // the remaining number of hammer strikes.

            List<Position> giants = initGiants(in);
            Map<Integer, Map<Integer, Position>> giantsMap = initGiantsMap(giants);

            giants.add(thorPosition);
            Position centroid = countCentroid(giants);
            System.err.println("thorPosition: " + thorPosition);
            System.err.println("centroid: " + centroid);

            String action = move(thorPosition, centroid, giantsMap, giants);

            // The movement or action to be carried out: WAIT STRIKE N NE E SE S SW W or N
            System.out.println(action);
        }
    }

    public static Map<Integer, Map<Integer, Position>> initGiantsMap(List<Position> giants) {
        return giants.stream().collect(Collectors.groupingBy(pos -> pos.x, Collectors.toMap(pos -> pos.y, pos -> pos)));
    }

    private static String move(Position thorPosition, Position centroid,
                               Map<Integer, Map<Integer, Position>> giantsMap, List<Position> giants) {

        Position newPosition = new Position(thorPosition);

        takeDirection(centroid, newPosition);

        newPosition = getSafeDirection(thorPosition, newPosition, giantsMap);

        if (waitingButGiantNear(newPosition, giantsMap) || canKillAllGiants(thorPosition, giants)) {
            newPosition.direction = "STRIKE";
        }

        thorPosition.setPosition(newPosition);
        return newPosition.direction;
    }

    private static boolean waitingButGiantNear(Position pos, Map<Integer, Map<Integer, Position>> giantsMap) {
        if ("WAIT".equals(pos.direction)) {
            return false;
        }
        Borders nearBorders = new Borders(pos.x - 1, pos.x + 1, pos.y - 1, pos.y + 1);
        return countGiants(nearBorders, giantsMap) > 0;
    }

    private static Position getSafeDirection(Position thorPosition, Position newPosition,
                                           Map<Integer, Map<Integer, Position>> giantsMap) {

        int radius = 3;
        for (int i = 0; i < 4; i ++) {
            Borders borders = countBorders(thorPosition, newPosition, radius);

            int giants = countGiants(borders, giantsMap);

            if (giants > 0) {
                newPosition.setPosition(thorPosition);
                setNewNearestDirection(thorPosition, newPosition);
            } else {
                return newPosition;
            }
        }
        newPosition.direction = "WAIT";
        newPosition.setPosition(thorPosition);
        return newPosition;
    }

    private static void setNewNearestDirection(Position thorPosition, Position newPosition) {
        String prevDirection = newPosition.direction;

        String direction;
        if (prevDirection.contains("W")) {
            direction = "N";
            newPosition.y = thorPosition.y - 1;
        } else if (prevDirection.contains("N")) {
            direction = "E";
            newPosition.x = thorPosition.x + 1;
        } else if (prevDirection.contains("E")) {
            direction = "S";
            newPosition.y = thorPosition.y + 1;
        } else {
            direction = "W";
            newPosition.x = thorPosition.x - 1;
        }

        newPosition.direction = direction;
    }

    private static Borders countBorders(Position prevPosition, Position newPosition, int radius) {
        String direction = newPosition.direction;
        Borders borders = new Borders();
        switch (direction) {
            case "N":
                borders.leftX = newPosition.x - radius;
                borders.rightX = newPosition.x + radius;

                borders.upY = newPosition.y - radius;
                borders.downY = prevPosition.y;
                break;
            case "NE":
                borders.leftX = prevPosition.x;
                borders.rightX = newPosition.x + radius;

                borders.upY = newPosition.y - radius;
                borders.downY = prevPosition.y;
                break;
            case "E":
                borders.leftX = prevPosition.x;
                borders.rightX = newPosition.x + radius;

                borders.upY = newPosition.y - radius;
                borders.downY = newPosition.y + radius;
                break;
            case "SE":
                borders.leftX = prevPosition.x;
                borders.rightX = newPosition.x + radius;

                borders.upY = prevPosition.y;
                borders.downY = newPosition.y + radius;
                break;
            case "S":
                borders.leftX = newPosition.x - radius;
                borders.rightX = newPosition.x + radius;

                borders.upY = prevPosition.y;
                borders.downY = newPosition.y + radius;
                break;
            case "SW":
                borders.leftX = newPosition.x - radius;
                borders.rightX = prevPosition.x;

                borders.upY = prevPosition.y;
                borders.downY = newPosition.y + radius;
                break;
            case "W":  //W
                borders.leftX = newPosition.x - radius;
                borders.rightX = prevPosition.x;

                borders.upY = newPosition.y - radius;
                borders.downY = newPosition.y + radius;
                break;
            case "NW":  //W
                borders.leftX = newPosition.x - radius;
                borders.rightX = prevPosition.x;

                borders.upY = newPosition.y - radius;
                borders.downY = prevPosition.y;
                break;
        }
        return borders;
    }

    private static int countGiants(Borders b, Map<Integer, Map<Integer, Position>> giantsMap) {
        int result = 0;
        for (int x = b.leftX; x <= b.rightX; x++) {
            for (int y = b.upY; y <= b.downY; y++) {
                if (giantExists(new Position(x, y), giantsMap) || x < 0 || y < 0 || x > 39 || y > 17) {
                    result++;
                }
            }
        }
        return result;

    }

    private static void takeDirection(Position centroid, Position newPosition) {
        int yDiff = centroid.y - newPosition.y;
        int xDiff = centroid.x - newPosition.x;

        String result = "";
        if (yDiff > 0) {
            result += "S";
            newPosition.y++;
        } else if (yDiff < 0) {
            result += "N";
            newPosition.y--;
        }

        if (xDiff > 0) {
            result += "E";
            newPosition.x++;
        } else if (xDiff < 0) {
            result += "W";
            newPosition.x--;
        }

        if (yDiff == 0 && xDiff == 0) {
            result = "WAIT";
        }
        newPosition.direction = result;
    }

    private static boolean canKillAllGiants(Position thorPosition, List<Position> giantsMap) {
        for (Position giantPosition : giantsMap) {
            if (!canKillGiant(giantPosition, thorPosition)) {
                return false;
            }
        }
        return true;
    }

    private static boolean canKillGiant(Position giantPosition, Position thorPosition) {
        int xDiff = Math.abs(thorPosition.x - giantPosition.x);
        int yDiff = Math.abs(thorPosition.y - giantPosition.y);
        return xDiff < 5 && yDiff < 5;
    }

    private static boolean giantExists(Position position, Map<Integer, Map<Integer, Position>> giantsMap) {
        Map<Integer, Position> integerPositionMap = giantsMap.get(position.x);
        if (integerPositionMap != null && integerPositionMap.containsKey(position.y)) {
            return true;
        }
        return false;
    }

    public static Position countCentroid(List<Position> giants) {
        Position centroid;
        if (giants.size() > 2) {
            centroid = countPolygonCentroid(giants);
        } else {
            centroid = countCentroidBetweenTwoPoints(giants);
        }
        return centroid;
    }

    private static Position countCentroidBetweenTwoPoints(List<Position> giants) {
        Position first = giants.get(0);
        Position second = giants.get(1);
        int cx = (first.x + second.x) / 2;
        int cy = (first.y + second.y) / 2;
        return new Position(cx, cy);
    }

    private static Position countPolygonCentroid(List<Position> giants) {
        double A = countA(giants);
        int cX = countCentroidX(A, giants);
        int cY = countCentroidY(A, giants);
        return new Position(cX, cY);
    }

    @SuppressWarnings("Duplicates")
    private static int countCentroidX(double A, List<Position> giants) {
        int sum = 0;

        for (int i = 0; i < giants.size(); i++) {
            Position iPosition = giants.get(i);
            int xi = iPosition.x;
            int yi = iPosition.y;

            Position i_1Position = giants.get(i + 1 == giants.size() ? 0 : i + 1);
            int xi_1 = i_1Position.x;
            int yi_1 = i_1Position.y;

            sum += ((xi + xi_1) * ((xi * yi_1) - (xi_1 * yi)));
        }

        return (int) (sum / (6 * A));
    }

    @SuppressWarnings("Duplicates")
    private static int countCentroidY(double A, List<Position> giants) {
        int sum = 0;

        for (int i = 0; i < giants.size(); i++) {
            Position iPosition = giants.get(i);
            int xi = iPosition.x;
            int yi = iPosition.y;

            Position i_1Position = giants.get(i + 1 == giants.size() ? 0 : i + 1);
            int xi_1 = i_1Position.x;
            int yi_1 = i_1Position.y;

            sum += ((yi + yi_1) * ((xi * yi_1) - (xi_1 * yi)));
        }

        return (int) (sum / (6 * A));
    }

    private static double countA(List<Position> giants) {
        double A = 0;
        for (int i = 0; i < giants.size(); i++) {
            Position iPosition = giants.get(i);
            int xi = iPosition.x;
            int yi = iPosition.y;

            Position i_1Position = giants.get(i + 1 == giants.size() ? 0 : i + 1);
            int xi_1 = i_1Position.x;
            int yi_1 = i_1Position.y;

            A += ((xi * yi_1) - (xi_1 * yi));
        }
        A /= 2d;
        return A == 0 ? 1 : A;
    }

    private static List<Position> initGiants(Scanner in) {
        List<Position> giants = new ArrayList<Position>();
        int N = in.nextInt(); // the number of giants which are still present on the map.
        System.err.println("giants start");
        for (int i = 0; i < N; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            Position giantPosition = new Position(x, y);
            System.err.println(x + " " + y);
            giants.add(giantPosition);
        }
        System.err.println("giants end");
        return giants;
    }

    public static class Position {
        private int x;
        private int y;
        public String direction;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position(Position position) {
            this.x = position.x;
            this.y = position.y;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public void setPosition(Position newPosition) {
            x = newPosition.x;
            y = newPosition.y;
        }
    }

    private static class Borders {
        private int leftX;
        private int rightX;
        private int upY;
        private int downY;

        public Borders(int leftX, int rightX, int upY, int downY) {
            this.leftX = leftX;
            this.rightX = rightX;
            this.upY = upY;
            this.downY = downY;
        }

        public Borders() {

        }
    }
}
