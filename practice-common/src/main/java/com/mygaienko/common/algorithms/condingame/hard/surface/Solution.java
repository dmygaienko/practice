package com.mygaienko.common.algorithms.condingame.hard.surface;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    private static final LakeSurface EMPTY_LAKE = new LakeSurface(0);

    private static final List<String> surfaceRaw = new ArrayList<>();
    private static final List<String> coordinatesRaw = new ArrayList<>();

    private static final List<Coordinate> coordinates = new ArrayList<>();
    private static Square[][] surface;
    private static int L;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        readSurfaceRaw(in);
        readCoordinatesRaw(in);
        //SurfaceUtil.initRaws(surfaceRaw, coordinatesRaw);

        parseSurfaceRaw();
        parseCoordinatesRaw();

        inspectSurface();

        for (int i = 0; i < coordinates.size(); i++) {
            printAnswer(coordinates.get(i));
        }
    }

    private static void inspectSurface() {
        for (int y = 0, surfaceLength = surface.length; y < surfaceLength; y++) {
            Square[] squares = surface[y];

            for (int x = 0, squaresLength = squares.length; x < squaresLength; x++) {
                Square square = squares[x];

                if (square.isInspected()) {
                    continue;
                }

                if (!square.isLake) {
                    square.setLakeSurface(EMPTY_LAKE);
                } else {
                    LakeSurface lakeSurface = new LakeSurface();
                    square.setLakeSurface(lakeSurface);
                    square.incrementLakeSurface();

                    LinkedList<Square> queue = new LinkedList<>();
                    inspectNeighbours(y, x, queue);
                    floodFillLakeSurfaceCount(lakeSurface, queue);
                }
            }
        }
    }

    //floodFillLakeSurfaceCount
    private static void floodFillLakeSurfaceCount(LakeSurface lakeSurface, LinkedList<Square> queue) {
        while (!queue.isEmpty()) {
            Square neighbour = queue.removeFirst();

            if (neighbour.isInspected()) {
                continue;
            }

            neighbour.setLakeSurface(lakeSurface);
            neighbour.incrementLakeSurface();

            inspectNeighbours(neighbour.getY(), neighbour.getX(), queue);
        }
    }

    private static void inspectNeighbours(int y, int x, LinkedList<Square> queue) {
        if (y - 1 >= 0) {
            addNeighbourLakeToQueue(y - 1, x, queue);
        }
        if (y + 1 < surface.length) {
            addNeighbourLakeToQueue(y + 1, x, queue);
        }
        if (x - 1 >= 0) {
            addNeighbourLakeToQueue(y, x - 1, queue);
        }
        if (x + 1 < surface[y].length) {
            addNeighbourLakeToQueue(y, x + 1, queue);
        }
    }

    private static void addNeighbourLakeToQueue(int y, int x, LinkedList<Square> queue) {
        Square neighbour = surface[y][x];
        if (!neighbour.isInspected() && neighbour.isLake) {
            queue.add(neighbour);
        }
    }

    private static void parseCoordinatesRaw() {
        for (String s : coordinatesRaw) {
            String[] coordinate = s.split(" ");
            int x = Integer.parseInt(coordinate[0]);
            int y = Integer.parseInt(coordinate[1]);
            coordinates.add(new Coordinate(x, y));
        }
    }

    private static void parseSurfaceRaw() {
        surface = new Square[surfaceRaw.size()][L];

        for (int y = 0; y < surfaceRaw.size(); y++) {
            String s = surfaceRaw.get(y);
            String[] squares = s.split("");
            for (int x = 0; x < squares.length; x++) {
                String type = squares[x];
                surface[y][x] = new Square(type, x, y);
            }
        }
    }

    private static void printAnswer(Coordinate coordinate) {
        System.out.println(surface[coordinate.y][coordinate.x].getLakeSurfaceArea());
    }

    private static void readCoordinatesRaw(Scanner in) {
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            coordinatesRaw.add(x + " " + y);
        }
    }

    private static void readSurfaceRaw(Scanner in) {
        L = in.nextInt();
        int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < H; i++) {
            surfaceRaw.add(in.nextLine());
        }
    }

    private static class Coordinate {
        private final int x;
        private final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Square {
        private LakeSurface lakeSurface;
        private boolean isLake;
        private final int x;
        private final int y;
        private boolean inspected;

        public Square(String type, int x, int y) {
            this.isLake = "O".equals(type);
            this.x = x;
            this.y = y;
        }

        public int getLakeSurfaceArea() {
            return lakeSurface.getValue();
        }

        public void setLakeSurface(LakeSurface lakeSurface) {
            this.lakeSurface = lakeSurface;
        }

        public boolean isLake() {
            return isLake;
        }

        public boolean isInspected() {
            return inspected;
        }

        public void incrementLakeSurface() {
            inspected = true;
            lakeSurface.increment();
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    private static class LakeSurface {
        private int value;

        public LakeSurface(int value) {
            this.value = value;
        }

        public LakeSurface() {

        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void increment() {
            ++value;
        }
    }
}