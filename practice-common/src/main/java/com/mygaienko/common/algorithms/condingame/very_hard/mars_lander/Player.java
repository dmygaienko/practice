package com.mygaienko.common.algorithms.condingame.very_hard.mars_lander;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    private static final double G = -3.711;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        List<Point> points = initPoints(in);
        FlatGround flatGround = findFlatGround(points);

        // game loop
        while (true) {
            Point currentPoint = new Point(in.nextInt(), in.nextInt());

            int hSpeed = in.nextInt(); // the horizontal speed (in m/s), can be negative.
            int vSpeed = in.nextInt(); // the vertical speed (in m/s), can be negative.
            int fuel = in.nextInt(); // the quantity of remaining fuel in liters.
            int rotate = in.nextInt(); // the rotation angle in degrees (-90 to 90).
            int power = in.nextInt(); // the thrust power (0 to 4).

            double currentDirection = calculateCurrentDirection(hSpeed, vSpeed);

            Point targetPoint = getNextTargetPoint();

            double desiredDirection = calculateDesiredDirection(currentPoint, targetPoint);

            Controls controls = getNextControls(currentDirection, desiredDirection, power);

            System.out.println(controls.angle + " " + controls.power);
        }
    }

    private static Controls getNextControls(double currentDirection, double desiredDirection, int power) {
        double directionDelta = desiredDirection - currentDirection;

        int angleMultiplier = 1;
        if (Math.abs(desiredDirection) > 90) {
            angleMultiplier = -1;
        }

        //if
        return null;
    }

    private static Point getNextTargetPoint() {
        return null;
    }

    public static double calculateDesiredDirection(Point currentPoint, Point targetPoint) {
        int xDiff = targetPoint.x - currentPoint.x;
        int yDiff = targetPoint.y - currentPoint.y;
        return calculateDirection(xDiff, yDiff);
    }

    public static double calculateCurrentDirection(double hSpeed, double vSpeed) {
        double totalVerticalSpeed = vSpeed + G;
        //double tangentAlpha = hSpeed/totalVerticalSpeed;
        return calculateDirection(hSpeed, totalVerticalSpeed);
    }

    private static double calculateDirection(double xDiff, double yDiff) {
        return Math.toDegrees(Math.atan2(xDiff, yDiff));
    }

    private static List<Point> initPoints(Scanner in) {
        ArrayList<Point> points = new ArrayList<>();
        int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
        for (int i = 0; i < surfaceN; i++) {
            int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
            int landY = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
            points.add(new Point(landX, landY));
        }
        return points;
    }

    public static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class FlatGround {
        private final Point point;
        private final Point nextPoint;

        public FlatGround(Point point, Point nextPoint) {
            this.point = point;
            this.nextPoint = nextPoint;
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

    private static class Controls {
        public int angle;
        public int power;
    }
}