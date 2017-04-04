package com.mygaienko.common.algorithms.condingame.hard.power_of_thor_episode2;

import java.util.*;
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

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // The movement or action to be carried out: WAIT STRIKE N NE E SE S SW W or N
            System.out.println(action);
        }
    }

    public static Map<Integer, Map<Integer, Position>> initGiantsMap(List<Position> giants) {
        return giants.stream().collect(Collectors.groupingBy(pos -> pos.x, Collectors.toMap(pos -> pos.y, pos -> pos)));
    }

    private static String move(Position thorPosition, Position centroid,
                               Map<Integer, Map<Integer, Position>> giantsMap, List<Position> giants) {
        String result = "";
        int newX = thorPosition.x;
        int newY= thorPosition.y;

        int yDiff = centroid.y - newY;
        if (yDiff > 0) {
            result += "S";
            newY++;
        } else if (yDiff < 0) {
            result += "N";
            newY--;
        }

        int xDiff = centroid.x - newX;
        if (xDiff > 0) {
            result += "E";
            newX++;
        } else if (xDiff < 0) {
            result += "W";
            newX--;
        }

        if (yDiff == 0 && xDiff == 0) {
            result = "WAIT";
        }

        if (cornered(thorPosition, giantsMap) || canKillAllGiants(thorPosition, giants)) {
            result = "STRIKE";
        }

        thorPosition.x = newX;
        thorPosition.y = newY;

        return result;
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

    private static boolean cornered(Position thorPosition, Map<Integer, Map<Integer, Position>> giantsMap) {
        int giantsAround = 0;

        ArrayList<Position> positionsAround = new ArrayList<>();
        int upY = thorPosition.y + 1;
        int downY = thorPosition.y - 1;

        int rightX = thorPosition.x + 1;
        int leftX = thorPosition.x - 1;

        giantsAround += getUpPositions(thorPosition, positionsAround, upY);
        giantsAround += getDownPositions(thorPosition, positionsAround, downY);
        giantsAround += getRightPositions(thorPosition, positionsAround, rightX);
        giantsAround += getLeftPositions(thorPosition, positionsAround, leftX);
        getDiagonalPositions(positionsAround, upY, downY, leftX, rightX);

        for (Position position : positionsAround) {
            if (giantExists(position, giantsMap)) {
                giantsAround++;
            }
        }

        return giantsAround > 3;
    }

    //clockwise
    private static void getDiagonalPositions(ArrayList<Position> positionsAround, int upY, int downY, int leftX, int rightX) {
        positionsAround.add(new Position(leftX, upY));
        positionsAround.add(new Position(rightX, upY));
        positionsAround.add(new Position(rightX, downY));
        positionsAround.add(new Position(leftX, downY));
    }

    private static boolean giantExists(Position position, Map<Integer, Map<Integer, Position>> giantsMap) {
        Map<Integer, Position> integerPositionMap = giantsMap.get(position.x);
        if (integerPositionMap != null && integerPositionMap.containsKey(position.y)) {
            return true;
        }
        return false;
    }

    private static int getLeftPositions(Position thorPosition, ArrayList<Position> around, int leftX) {
        int giantsAround = 0;
        if (leftX < 0) {
            giantsAround++;
        } else {
            around.add(new Position(leftX, thorPosition.y));
        }
        return giantsAround;
    }

    private static int getRightPositions(Position thorPosition, ArrayList<Position> around, int rightX) {
        int giantsAround = 0;
        if (rightX > 39) {
            giantsAround++;
        } else {
            around.add(new Position(rightX, thorPosition.y));
        }
        return giantsAround;
    }

    private static int getDownPositions(Position thorPosition, ArrayList<Position> around, int downY) {
        int giantsAround = 0;
        if (downY < 0) {
            giantsAround++;
        } else {
            around.add(new Position(thorPosition.x, downY));
        }
        return giantsAround;
    }

    private static int getUpPositions(Position thorPosition, ArrayList<Position> around, int upY) {
        int giantsAround = 0;
        if (upY > 17) {
            giantsAround++;
        } else {
            around.add(new Position(thorPosition.x, upY));
        }
        return giantsAround;
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
        for (int i = 0; i < N; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            Position giantPosition = new Position(x, y);
            System.err.println("giantPosition " + giantPosition);
            giants.add(giantPosition);
        }
        return giants;
    }

    public static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
