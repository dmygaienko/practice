package com.mygaienko.common.algorithms.condingame.very_hard.mars_lander;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        List<Point> points = initPoints(in);
        FlatGround flatGround = findFlatGround(points);
        findPath(flatGround.getCentralPoint());

        // game loop
        while (true) {
            int X = in.nextInt();
            int Y = in.nextInt();
            int hSpeed = in.nextInt(); // the horizontal speed (in m/s), can be negative.
            int vSpeed = in.nextInt(); // the vertical speed (in m/s), can be negative.
            int fuel = in.nextInt(); // the quantity of remaining fuel in liters.
            int rotate = in.nextInt(); // the rotation angle in degrees (-90 to 90).
            int power = in.nextInt(); // the thrust power (0 to 4).

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // rotate power. rotate is the desired rotation angle. power is the desired thrust power.
            System.out.println("-20 3");
        }
    }

    private static void findPath(Point centralPoint) {

    }

    private static List<Point> initPoints(Scanner in) {
        ArrayList<Point> points = new ArrayList<>();
        int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
        for (int i = 0; i < surfaceN; i++) {
            int landX = in.nextInt(); // X coordinate of a surface startPoint. (0 to 6999)
            int landY = in.nextInt(); // Y coordinate of a surface startPoint. By linking all the points together in a sequential fashion, you form the surface of Mars.
            points.add(new Point(landX, landY));
        }
        return points;
    }

    private static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class FlatGround {
        private final Point startPoint;
        private final Point centralPoint;
        private final Point endPoint;

        public FlatGround(Point point, Point nextPoint) {
            this.startPoint = point;
            this.endPoint = nextPoint;
            this.centralPoint = new Point(endPoint.x - startPoint.x, endPoint.y);
        }

        public Point getCentralPoint() {
            return centralPoint;
        }
    }

    private static FlatGround findFlatGround(List<Point> points) {
        FlatGround flatGround = null;

        outer:
        for (int i = 0, pointsSizeWithoutLast = points.size() - 1; i < pointsSizeWithoutLast; i++) {
            Point point = points.get(i);

            for (int j = i, pointsSize = points.size(); j < pointsSize; j++) {
                Point nextPoint = points.get(j);
                if (point.y != nextPoint.y) {
                    break;
                }

                if (point.x - nextPoint.x >= 1000) {
                    flatGround = new FlatGround(point, nextPoint);
                    break outer;
                }
            }
        }

        return flatGround;
    }

}