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

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // The movement or action to be carried out: WAIT STRIKE N NE E SE S SW W or N
            System.out.println("WAIT");
        }
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

    private static class Position {
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
