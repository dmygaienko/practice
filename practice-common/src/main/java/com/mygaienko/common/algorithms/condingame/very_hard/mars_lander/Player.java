package com.mygaienko.common.algorithms.condingame.very_hard.mars_lander;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    private static final double G = -3.711;
    private static Vector gravityVector = new Vector(0, G);
    private static Vector landingVector = new Vector(0, -35);
    private static Vector extremeLandingVector = new Vector(20, -40).plus(gravityVector);

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

            Vector verticalVector = new Vector(0, vSpeed).plus(gravityVector);
            Vector horizontalVector = new Vector(hSpeed, 0);

            Vector currentVector = verticalVector.plus(horizontalVector);

            Vector deltaVector = getDeltaVector(currentPoint, currentVector, flatGround);

            Controls controls = getNextControls(verticalVector, currentVector, deltaVector, isLanding(currentPoint, flatGround));

            System.out.println(-(int) controls.angle + " " + controls.power);
        }
    }

    private static boolean isLanding(Point currentPoint, FlatGround flatGround) {
        boolean near = flatGround.startLandingPoint.distanceTo(currentPoint) < 300;
        if (near) {
            flatGround.setNextTargetPoint(flatGround.centralPoint);
        }
        //return flatGround.point.x <= currentPoint.x && currentPoint.x <= flatGround.nextPoint.x;
        return near || flatGround.getNextTargetPoint().equals(flatGround.centralPoint);
    }

    private static Vector getDeltaVector(Point currentPoint, Vector currentVector, FlatGround flatGround) {
        Vector deltaVector;

        if (isLanding(currentPoint, flatGround)) {
            deltaVector = getLevelingVector(currentVector);
        } else {
            deltaVector = getNonLevelingDeltaVector(currentPoint, currentVector, flatGround.getNextTargetPoint());
        }

        return deltaVector;
    }

    private static Vector getLevelingVector(Vector currentVector) {
        return landingVector.minus(currentVector);
    }

    private static Vector getNonLevelingDeltaVector(Point currentPoint, Vector currentVector, Point targetPoint) {
        Vector desiredVector = new Vector(currentPoint, targetPoint);

        while (desiredVector.length > currentVector.length * 1.2){
            desiredVector = desiredVector.multiply(0.8);
        }
        return desiredVector.minus(currentVector);
    }

    public static Controls getNextControls(Vector verticalVector, Vector currentVector, Vector deltaVector, boolean isLanding) {
        Controls controls;
        if (isLanding && currentVector.inBounds(extremeLandingVector)) {
            controls = getNextLandingControls(verticalVector);
        } else {
            controls = getNextNonLandingControls(currentVector, deltaVector);
        }
        return controls;
    }

    private static Controls getNextLandingControls(Vector verticalVector) {
        Controls controls = new Controls();
        controls.angle = 0;
        controls.power = verticalVector.y < -39 ? 4 : 3;
        return controls;
    }

    private static Controls getNextNonLandingControls(Vector currentVector, Vector deltaVector) {
        double angle = deltaVector.getAngle();
        boolean isOpposite = deltaVector.isOpposite(currentVector);

        Controls controls = new Controls();
        if (angle == 0) {
            controls.angle = 0;
            controls.power = 4;
        } else if (angle == 180) {
            controls.angle = 0;
            controls.power = 3;
        } else if (angle > 0 && angle < 90) {
            controls.angle = getSafeAngle(angle);
            controls.power = 4;
        } else if (angle > 90 && angle < 180) {
            controls.angle = getSafeAngle(isOpposite ? angle :(angle - 90));
            controls.power = isOpposite ? 4 : 3;
        } else if (angle > -90 && angle < 0) {
            controls.angle = getSafeAngle(angle);
            controls.power = 4;
        } else if (angle > -180 && angle < -90) {
            controls.angle = getSafeAngle(isOpposite ? angle :(angle + 90));
            controls.power = isOpposite ? 4 : 3;
        }

        return controls;
    }

    private static double getSafeAngle(double angle) {
        return angle * 22/90;
    }

    public static Vector calculateDesiredDirection(Point currentPoint, Point targetPoint) {
        return new Vector(currentPoint, targetPoint);
    }

    private static List<Point> initPoints(Scanner in) {
        ArrayList<Point> points = new ArrayList<>();
        int surfaceN = in.nextInt(); // the number of points used to draw the surface of Mars.
        for (int i = 0; i < surfaceN; i++) {
            int landX = in.nextInt(); // X coordinate of a surface point. (0 to 6999)
            int landY = in.nextInt(); // Y coordinate of a surface point. By linking all the points together in a sequential fashion, you form the surface of Mars.
            points.add(new Point(landX, landY));
        }
        System.err.println(points);
        return points;
    }

    public static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return "new Point(" + x + ", " + y +')';
        }

        public double distanceTo(Point that) {
            return Math.sqrt(pow(x - that.x, 2) + pow(y - that.y, 2));
        }
    }

    static class FlatGround {
        private final Point point;
        private final Point nextPoint;
        private final Point centralPoint;
        private final Point startLandingPoint;
        private Point nextTargetPoint;

        public FlatGround(Point point, Point nextPoint) {
            boolean inOrder = point.x < nextPoint.x;
            this.point = inOrder ? point : nextPoint;
            this.nextPoint = inOrder ? nextPoint : point;
            this.centralPoint = new Point((point.x + nextPoint.x)/2, (point.y + nextPoint.y)/2);
            this.startLandingPoint = new Point(centralPoint.x, centralPoint.y + 300);
            nextTargetPoint = startLandingPoint;
        }

        @Override
        public String toString() {
            return "FlatGround{" +
                    "point=" + point +
                    ", nextPoint=" + nextPoint +
                    ", centralPoint=" + centralPoint +
                    '}';
        }

        public void setNextTargetPoint(Point nextTargetPoint) {
            this.nextTargetPoint = nextTargetPoint;
        }

        public Point getNextTargetPoint() {
            return nextTargetPoint;
        }
    }

    public static FlatGround findFlatGround(List<Point> points) {
        FlatGround flatGround = null;

        outer:
        for (int i = 0, pointsSizeWithoutLast = points.size() - 1; i < pointsSizeWithoutLast; i++) {
            Point point = points.get(i);

            for (int j = i + 1, pointsSize = points.size(); j < pointsSize; j++) {
                Point nextPoint = points.get(j);
                if (point.y != nextPoint.y) {
                    break;
                }

                if (Math.abs(point.x - nextPoint.x) >= 1000) {
                    flatGround = new FlatGround(point, nextPoint);
                    break outer;
                }
            }
        }

        return flatGround;
    }

    public static class Controls {
        public double angle;
        public int power;
    }

    public static class Vector {

        private final double x;
        private final double y;
        private final double angle;
        private final double length;

        public Vector(double x, double y) {
            this.x = x;
            this.y = y;
            this.angle = Math.toDegrees(Math.atan2(x, y));
            this.length = Math.sqrt(x * x + y * y);
        }

        public Vector(Point currentPoint, Point targetPoint) {
            this(targetPoint.x - currentPoint.x, targetPoint.y - currentPoint.y);
        }

        public Vector plus(Vector that) {
            return new Vector(x + that.x, y + that.y);
        }

        public Vector minus(Vector that) {
            return new Vector(x - that.x, y - that.y);
        }

        public Vector rotate(double angle){
            double x1 = x * cos(angle) - y * sin(angle);
            double y1 = x * sin(angle) + y * cos(angle);
            return  new Vector(x1, y1);
        }

        @Override
        public String toString() {
            return "Vector{" +
                    "x=" + x +
                    ", y=" + y +
                    ", angle=" + angle +
                    ", length=" + length +
                    '}';
        }

        public double getAngle() {
            return angle;
        }

        public Vector multiply(double scalar) {
            return new Vector(x * scalar, y * scalar);
        }

        public boolean isOpposite(Vector that) {
            return angle/that.angle < 0;
        }

        public boolean inBounds(Vector that) {
            return Math.abs(x) <= Math.abs(that.x) && y >= that.y;
        }
    }
}