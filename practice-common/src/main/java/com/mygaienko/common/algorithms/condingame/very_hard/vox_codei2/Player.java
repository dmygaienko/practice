package com.mygaienko.common.algorithms.condingame.very_hard.vox_codei2;

import java.util.*;
import java.io.*;
import java.math.*;

import static com.mygaienko.common.algorithms.condingame.very_hard.vox_codei2.Player.StaticNodeType.EMPTY;
import static com.mygaienko.common.algorithms.condingame.very_hard.vox_codei2.Player.StaticNodeType.PASSIVE;

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

    static class Node {

        private final int width;
        private final int height;

        public Node(int width, int height) {
            this.width = width;
            this.height = height;
        }

    }

    static class NodeFactory {

        static Node getNode(String s, int width, int height) {
            Node node = null;
            switch (s) {
                case "." : node = new StaticNode(EMPTY, width, height); break;
                case "#" : node = new StaticNode(PASSIVE, width, height); break;
                case "@" : node = new MovableNode(width, height); break;
            }
            return node;
        }

    }

    static class StaticNode extends Node {

        private final StaticNodeType type;

        public StaticNode(StaticNodeType type, int width, int height) {
            super(width, height);
            this.type = type;
        }

    }

    enum StaticNodeType {
        EMPTY, PASSIVE, MINED;
    }

    //SURVEILLANCE
    static class MovableNode extends Node {

        public MovableNode(int width, int height) {
            super(width, height);
        }


    }

}