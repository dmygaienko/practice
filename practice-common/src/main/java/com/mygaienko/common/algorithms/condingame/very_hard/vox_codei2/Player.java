package com.mygaienko.common.algorithms.condingame.very_hard.vox_codei2;

import java.util.Scanner;

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
                case "." : node = new EmptyNode(width, height); break;
                case "#" : node = new PassiveNode(width, height); break;
                case "@" : node = new EmptyNode(width, height); registerSupervisor((EmptyNode) node); break;
            }
            return node;
        }

        private static void registerSupervisor(EmptyNode node) {
            new Supervisor(node);
        }
    }

    static class EmptyNode extends Node {
        
        private boolean mined = false;

        public EmptyNode(int width, int height) {
            super(width, height);
        }
    }

    static class PassiveNode extends Node {

        public PassiveNode(int width, int height) {
            super(width, height);
        }
    }

    static class Supervisor {
        
        private EmptyNode node;

        public Supervisor(EmptyNode node) {
            this.node = node;
        }

    }

}