package com.mygaienko.common.algorithms.condingame.easy;

import java.util.*;


/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
//        String[] map = buildMap();
        String[] map = buildTestMap();
        connectAllLabels(map);
    }

    private static String[] buildTestMap() {
        return new String[] {
                "A  B  C",
                "|  |  |",
                "|--|  |",
                "|  |--|",
                "|  |--|",
                "|  |  |",
                "1  2  3"
        };
    }

    private static void connectAllLabels(String[] map) {
        String topLabels = map[0];
        for (int i = 0; i < topLabels.length(); i++) {
            char nextChar = topLabels.charAt(i);
            if (Character.isLetter(nextChar)) {
                char buttomLabel = connectLabel(map, new Pointer(i, 1));
                System.out.println(new String(new char[]{ nextChar, buttomLabel }));
            }
        }
    }

    private static char connectLabel(String[] map, Pointer pointer) {
        char currentChar = getCharAtPointer(map, pointer);

        System.err.println("CurrentChar: " + currentChar);

        if (!Character.isWhitespace(currentChar) && '|' != currentChar) {
            return currentChar;
        } else if (connectorExistOnTheRight(map, pointer)) {
            return connectLabel(map, pointer.moveRight().moveDown());
        } else if (connectorExistOnTheLeft(map, pointer)) {
            return connectLabel(map, pointer.moveLeft().moveDown());
        }

        return connectLabel(map, pointer.moveDown());
    }

    private static char getCharAtPointer(String[] map, Pointer pointer) {
        if (validPointer(map, pointer)) {
            return map[pointer.getY()].charAt(pointer.getX());
        } else {
            return ' ';
        }
    }

    private static boolean validPointer(String[] map, Pointer pointer) {
        return pointer.isValid() && map.length > pointer.getY() && map[0].length() > pointer.getX();
    }

    private static boolean connectorExistOnTheLeft(String[] map, Pointer pointer) {
        return isConnector(getCharAtPointer(map, pointer.seeLeft()));
    }

    private static boolean connectorExistOnTheRight(String[] map, Pointer pointer) {
        return isConnector(getCharAtPointer(map, pointer.seeRight()));
    }

    private static boolean isConnector(char currentChar) {
        return '-' == currentChar;
    }

    private static String[] buildMap() {
        Scanner in = new Scanner(System.in);

        int W = in.nextInt();
        int H = in.nextInt();

        String[] map = new String[H];

        for (int i = 0; i < H; i++) {
            map[i] = in.nextLine();
        }

        System.err.println("Built array is : " + Arrays.toString(map));
        return map;
    }

    private static class Pointer {
        private final int x;
        private final int y;

        private Pointer(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        @Override
        public String toString() {
            return "Pointer{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public int getY() {
            return y;
        }

        public Pointer seeRight() {
            return new Pointer(x + 1, y);
        }

        public Pointer seeLeft() {
            return new Pointer(x - 1, y);
        }

        public Pointer moveRight() {
            return new Pointer(x + 3, y);
        }

        public Pointer moveLeft() {
            return new Pointer(x - 3, y);
        }

        public Pointer moveDown() {
            return new Pointer(x, y + 1);
        }

        public boolean isValid() {
            return x >= 0 && y >=0;
        }
    }

}