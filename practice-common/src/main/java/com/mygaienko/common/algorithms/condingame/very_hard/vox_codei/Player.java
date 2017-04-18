package com.mygaienko.common.algorithms.condingame.very_hard.vox_codei;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // width of the firewall grid
        int height = in.nextInt(); // height of the firewall grid
        if (in.hasNextLine()) {
            in.nextLine();
        }

        // game loop
        while (true) {
            int rounds = in.nextInt(); // number of rounds left before the end of the game
            int bombs = in.nextInt(); // number of bombs left
            if (in.hasNextLine()) {
                in.nextLine();
            }
            for (int i = 0; i < height; i++) {
                String mapRow = in.nextLine(); // one line of the firewall grid
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println("3 0");
        }
    }
}