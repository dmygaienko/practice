package com.mygaienko.common.algorithms.condingame.hard.power_of_thor_episode2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by dmygaenko on 03/04/2017.
 */
public class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int TX = in.nextInt();
        int TY = in.nextInt();

        // game loop
        while (true) {
            int strikes = in.nextInt(); // the remaining number of hammer strikes.

            List<Position> giants = initGiants(in);
            Position centroid = countCentroid(giants);
            System.err.println(centroid);

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // The movement or action to be carried out: WAIT STRIKE N NE E SE S SW W or N
            System.out.println("WAIT");
        }
    }

    public static Position countCentroid(List<Position> giants) {
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
        return A/2L;
    }

    private static List<Position> initGiants(Scanner in) {
        List<Position> giants = new ArrayList<Position>();
        int N = in.nextInt(); // the number of giants which are still present on the map.
        for (int i = 0; i < N; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
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
