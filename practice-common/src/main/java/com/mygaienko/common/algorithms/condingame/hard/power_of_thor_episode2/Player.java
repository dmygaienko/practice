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
            System.err.println(centroid);

            String action = moveToCentroid(thorPosition, centroid, giantsMap);

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // The movement or action to be carried out: WAIT STRIKE N NE E SE S SW W or N
            System.out.println("WAIT");
        }
    }

    public static Map<Integer, Map<Integer, Position>> initGiantsMap(List<Position> giants) {
        return giants.stream().collect(Collectors.groupingBy(pos -> pos.x, Collectors.toMap(pos -> pos.y, pos -> pos)));
    }

    private static String moveToCentroid(Position thorPosition, Position centroid, Map<Integer, Map<Integer, Position>> giantsMap) {
      /*  var yDiff = lightY-positionY;
        if (yDiff > 0) {
            move += 'S';
            positionY++;
        } else if (yDiff < 0) {
            move += 'N';
            positionY--;
        }

        var xDiff = lightX-positionX;
        if (xDiff > 0) {
            move += 'E';
            positionX++;
        } else if (xDiff < 0) {
            move += 'W';
            positionX--;
        };*/

      return "";


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
        long A = countA(giants);
        int cX = countCentroidX(A, giants);
        int cY = countCentroidY(A, giants);
        return new Position(cX, cY);
    }

    @SuppressWarnings("Duplicates")
    private static int countCentroidX(long A, List<Position> giants) {
        int sum = 0;

        for (int i = 0; i < giants.size(); i++) {
            Position iPosition = giants.get(i);
            int xi = iPosition.x;
            int yi = iPosition.y;

            Position i_1Position = giants.get(i + 1 == giants.size() ? 0 : i + 1);
            int xi_1 = i_1Position.x;
            int yi_1 = i_1Position.y;

            sum += (xi + xi_1) * (xi * yi_1 - xi_1 * yi);
        }

        return (int) (sum / (6 * A));
    }

    @SuppressWarnings("Duplicates")
    private static int countCentroidY(long A, List<Position> giants) {
        int sum = 0;

        for (int i = 0; i < giants.size(); i++) {
            Position iPosition = giants.get(i);
            int xi = iPosition.x;
            int yi = iPosition.y;

            Position i_1Position = giants.get(i + 1 == giants.size() ? 0 : i + 1);
            int xi_1 = i_1Position.x;
            int yi_1 = i_1Position.y;

            sum += (yi + yi_1) * (xi * yi_1 - xi_1 * yi);
        }

        return (int) (sum / (6 * A));
    }

    private static long countA(List<Position> giants) {
        int A = 0;
        for (int i = 0; i < giants.size(); i++) {
            Position iPosition = giants.get(i);
            int xi = iPosition.x;
            int yi = iPosition.y;

            Position i_1Position = giants.get(i + 1 == giants.size() ? 0 : i + 1);
            int xi_1 = i_1Position.x;
            int yi_1 = i_1Position.y;

            A += xi * yi_1 - xi_1 * yi;
        }
        A /= 2L;
        return A == 0 ? 1 : A;
    }

    private static List<Position> initGiants(Scanner in) {
        List<Position> giants = new ArrayList<Position>();
        int N = in.nextInt(); // the number of giants which are still present on the map.
        for (int i = 0; i < N; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            System.err.println(new Position(x, y));
            giants.add(new Position(x, y));
        }
        return giants;
    }

    public static class Position {
        private final int x;
        private final int y;

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
