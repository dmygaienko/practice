package com.mygaienko.common.algorithms.condingame.hard.power_of_thor_episode2;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * Created by dmygaenko on 03/04/2017.
 */
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        Position thorPosition = new Position(in.nextInt(), in.nextInt());
        // game loop
        while (true) {
            int strikes = in.nextInt(); // the remaining number of hammer strikes.

            List<Position> giants = initGiants(in);
            Map<Integer, Map<Integer, Position>> giantsMap = initGiantsMap(giants);

            giants.add(thorPosition);
            System.err.println("thorPosition: " + thorPosition);

            thorPosition = move(thorPosition, giantsMap, giants);

            System.out.println(thorPosition.action.name());
        }
    }

    public static Map<Integer, Map<Integer, Position>> initGiantsMap(List<Position> giants) {
        return giants.stream().collect(Collectors.groupingBy(pos -> pos.x, toMap(pos -> pos.y, pos -> pos, (a, b) -> a)));
    }

    private static Position move(Position thorPosition, Map<Integer, Map<Integer, Position>> giantsMap, List<Position> giants) {

        Position nextTarget = chooseNextTargetPosition(giants);

        Position newPosition = takeDirection(nextTarget, thorPosition);

        newPosition = getSafeDirection(thorPosition, newPosition, giantsMap, 1);

        if (waitingButGiantNear(newPosition, giantsMap) || canKillAllGiants(thorPosition, giants)) {
            newPosition = thorPosition.applyAction(NonMovingAction.STRIKE);
        }

        return newPosition;
    }

    private static Position chooseNextTargetPosition(List<Position> giants) {
        Position centroid = countCentroid(giants);
        Position mostDistantGiant = existMostDistantGiant(centroid, giants);

        Position nextTarget;
        if (mostDistantGiant  != null) {
            nextTarget = mostDistantGiant;
        } else {
            nextTarget = centroid;
        }
        return nextTarget;
    }

    private static boolean waiting(Position pos) {
        return NonMovingAction.WAIT.equals(pos.action);
    }

    private static Position existMostDistantGiant(Position centroid, List<Position> giants) {
        Map<Position, Double> distances = giants.stream().collect(toMap(giant -> giant, giant -> calculateDistance(giant, centroid)));
        Double sum = distances.values().stream().reduce((a, b) -> a + b).orElse(0d);
        Double avg = sum/distances.values().size();

        if (avg == 0) return null;

        TreeMap<Double, Position> diffs = distances.entrySet().stream().collect(
                toMap(  entry -> getDiff(avg, entry.getValue()),
                        Map.Entry::getKey,
                        (a,b) -> a,
                        () -> new TreeMap<>((entry1, entry2) -> entry1 > entry2 ? 0 : 1)));

        Map.Entry<Double, Position> mostDistant = diffs.firstEntry();

        if (mostDistant.getKey() > 0.1) {
            return mostDistant.getValue();
        }
        return null;
    }

    private static double getDiff(Double avg, Double value) {
        return value/avg - 1;
    }

    private static Double calculateDistance(Position giantPosition, Position thorPosition) {
        return Math.sqrt(Math.pow(giantPosition.x - thorPosition.x, 2) + Math.pow(giantPosition.y - thorPosition.y, 2));
    }

    private static boolean waitingButGiantNear(Position pos, Map<Integer, Map<Integer, Position>> giantsMap) {
        if (!waiting(pos)) {
            return false;
        }
        Borders nearBorders = new Borders(pos.x - 1, pos.x + 1, pos.y - 1, pos.y + 1);
        return countGiants(nearBorders, giantsMap) > 0;
    }

    private static Position getSafeDirection(Position thorPosition, Position newPosition,
                                             Map<Integer, Map<Integer, Position>> giantsMap, int radius) {

        for (int i = 0; i < 8; i++) {
            Borders borders = newPosition.action.countBorders(thorPosition, newPosition, radius);

            int giants = countGiants(borders, giantsMap);

            if (giants > 0) {
                newPosition = setNewNearestDirection(thorPosition, newPosition.action);
            } else {
                return newPosition;
            }
        }
        return thorPosition.applyAction(NonMovingAction.WAIT);
    }

    private static Position setNewNearestDirection(Position currentPosition, Action prevAction) {
        Action[] values = MovingAction.values();

        int nextActionOrdinal;
        if (prevAction instanceof MovingAction) {
            nextActionOrdinal = (((MovingAction) prevAction).ordinal() + 1) % values.length;
        } else {
            nextActionOrdinal = 0;
        }

        Action nextAction = values[nextActionOrdinal];
        return currentPosition.applyAction(nextAction);
    }

    private static int countGiants(Borders b, Map<Integer, Map<Integer, Position>> giantsMap) {
        int result = 0;
        for (int x = b.leftX; x <= b.rightX; x++) {
            for (int y = b.upY; y <= b.downY; y++) {
                if (giantExists(new Position(x, y), giantsMap) || x < 0 || y < 0 || x > 39 || y > 17) {
                    result++;
                }
            }
        }
        return result;
    }

    private static Position takeDirection(Position nextTarget, Position currentPosition) {
        int yDiff = nextTarget.y - currentPosition.y;
        int xDiff = nextTarget.x - currentPosition.x;

        String action = "";
        if (yDiff > 0) {
            action += "S";
        } else if (yDiff < 0) {
            action += "N";
        }

        if (xDiff > 0) {
            action += "E";
        } else if (xDiff < 0) {
            action += "W";
        }


        Action nextAction;
        if (yDiff == 0 && xDiff == 0) {
            nextAction = NonMovingAction.WAIT;
        } else {
            nextAction = MovingAction.valueOf(action);
        }

        return currentPosition.applyAction(nextAction);
    }

    private interface Action {
        int getX();
        int getY();
        String name();
        Borders countBorders(Position thorPosition, Position newPosition, int radius);
    }

    private enum NonMovingAction implements Action {
        WAIT,
        STRIKE;

        private final int x = 0;
        private final int y = 0;

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }

        @Override
        public Borders countBorders(Position thorPosition, Position newPosition, int radius) {
            return new Borders(newPosition.x - 1, newPosition.x + 1, newPosition.y - 1, newPosition.y + 1);
        }

    }

    private enum MovingAction implements Action {

        N(0, -1) {
            @Override
            public Borders countBorders(Position prevPosition, Position newPosition, int radius) {
                Borders borders = new Borders();
                borders.leftX = newPosition.x - radius;
                borders.rightX = newPosition.x + radius;
                borders.upY = newPosition.y - radius;
                borders.downY = prevPosition.y;
                return borders;
            }
        },
        NE(1, -1) {
            @Override
            public Borders countBorders(Position prevPosition, Position newPosition, int radius) {
                Borders borders = new Borders();
                borders.leftX = prevPosition.x;
                borders.rightX = newPosition.x + radius;
                borders.upY = newPosition.y - radius;
                borders.downY = prevPosition.y;
                return borders;
            }
        },
        E(1, 0) {
            @Override
            public Borders countBorders(Position prevPosition, Position newPosition, int radius) {
                Borders borders = new Borders();
                borders.leftX = prevPosition.x;
                borders.rightX = newPosition.x + radius;
                borders.upY = newPosition.y - radius;
                borders.downY = newPosition.y + radius;
                return borders;
            }
        },
        SE(1, 1) {
            @Override
            public Borders countBorders(Position prevPosition, Position newPosition, int radius) {
                Borders borders = new Borders();
                borders.leftX = prevPosition.x;
                borders.rightX = newPosition.x + radius;
                borders.upY = prevPosition.y;
                borders.downY = newPosition.y + radius;
                return borders;
            }
        },

        S(0, 1) {
            @Override
            public Borders countBorders(Position prevPosition, Position newPosition, int radius) {
                Borders borders = new Borders();
                borders.leftX = newPosition.x - radius;
                borders.rightX = newPosition.x + radius;
                borders.upY = prevPosition.y;
                borders.downY = newPosition.y + radius;
                return borders;
            }
        },
        SW(-1, 1) {
            @Override
            public Borders countBorders(Position prevPosition, Position newPosition, int radius) {
                Borders borders = new Borders();
                borders.leftX = newPosition.x - radius;
                borders.rightX = prevPosition.x;
                borders.upY = prevPosition.y;
                borders.downY = newPosition.y + radius;
                return borders;
            }
        },
        W(-1, 0) {
            @Override
            public Borders countBorders(Position prevPosition, Position newPosition, int radius) {
                Borders borders = new Borders();
                borders.leftX = newPosition.x - radius;
                borders.rightX = prevPosition.x;
                borders.upY = newPosition.y - radius;
                borders.downY = newPosition.y + radius;
                return borders;
            }
        },
        NW(-1, -1) {
            @Override
            public Borders countBorders(Position prevPosition, Position newPosition, int radius) {
                Borders borders = new Borders();
                borders.leftX = newPosition.x - radius;
                borders.rightX = prevPosition.x;
                borders.upY = newPosition.y - radius;
                borders.downY = prevPosition.y;
                return borders;
            }
        };

        private final int x;
        private final int y;

        MovingAction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }
    }


    private static boolean canKillAllGiants(Position thorPosition, List<Position> giantsMap) {
        for (Position giantPosition : giantsMap) {
            if (!canKillGiant(giantPosition, thorPosition)) {
                return false;
            }
        }
        return true;
    }

    private static boolean canKillGiant(Position giantPosition, Position thorPosition) {
        int xDiff = Math.abs(thorPosition.x - giantPosition.x);
        int yDiff = Math.abs(thorPosition.y - giantPosition.y);
        int killDistance = 5;
        return xDiff <= killDistance && yDiff <= killDistance;
    }

    private static boolean giantExists(Position position, Map<Integer, Map<Integer, Position>> giantsMap) {
        Map<Integer, Position> integerPositionMap = giantsMap.get(position.x);
        if (integerPositionMap != null && integerPositionMap.containsKey(position.y)) {
            return true;
        }
        return false;
    }

    public static Position countCentroid(List<Position> giants) {
        Position centroid;
        if (giants.size() > 2) {
            centroid = countPolygonCentroid(giants);
        } else {
            centroid = countCentroidBetweenTwoPoints(giants);
        }
        System.err.println("centroid " + centroid);
        return centroid;
    }

    private static Position countCentroidBetweenTwoPoints(List<Position> giants) {
        Position first = giants.get(0);
        Position second = giants.get(1);
        int cx = (first.x + second.x) / 2;
        int cy = (first.y + second.y) / 2;
        return new Position(cx, cy);
    }

    private static Position countPolygonCentroid(List<Position> giants) {
        double A = countA(giants);
        int cX = countCentroidX(A, giants);
        int cY = countCentroidY(A, giants);
        return new Position(cX, cY);
    }

    @SuppressWarnings("Duplicates")
    private static int countCentroidX(double A, List<Position> giants) {
        int sum = 0;

        for (int i = 0; i < giants.size(); i++) {
            Position iPosition = giants.get(i);
            int xi = iPosition.x;
            int yi = iPosition.y;

            Position i_1Position = giants.get(i + 1 == giants.size() ? 0 : i + 1);
            int xi_1 = i_1Position.x;
            int yi_1 = i_1Position.y;

            sum += ((xi + xi_1) * ((xi * yi_1) - (xi_1 * yi)));
        }

        return (int) (sum / (6 * A));
    }

    @SuppressWarnings("Duplicates")
    private static int countCentroidY(double A, List<Position> giants) {
        int sum = 0;

        for (int i = 0; i < giants.size(); i++) {
            Position iPosition = giants.get(i);
            int xi = iPosition.x;
            int yi = iPosition.y;

            Position i_1Position = giants.get(i + 1 == giants.size() ? 0 : i + 1);
            int xi_1 = i_1Position.x;
            int yi_1 = i_1Position.y;

            sum += ((yi + yi_1) * ((xi * yi_1) - (xi_1 * yi)));
        }

        return (int) (sum / (6 * A));
    }

    private static double countA(List<Position> giants) {
        double A = 0;
        for (int i = 0; i < giants.size(); i++) {
            Position iPosition = giants.get(i);
            int xi = iPosition.x;
            int yi = iPosition.y;

            Position i_1Position = giants.get(i + 1 == giants.size() ? 0 : i + 1);
            int xi_1 = i_1Position.x;
            int yi_1 = i_1Position.y;

            A += ((xi * yi_1) - (xi_1 * yi));
        }
        A /= 2d;
        return A == 0 ? 1 : A;
    }

    private static List<Position> initGiants(Scanner in) {
        List<Position> giants = new ArrayList<Position>();
        int N = in.nextInt(); // the number of giants which are still present on the map.
        System.err.println("giants start");
        for (int i = 0; i < N; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            Position giantPosition = new Position(x, y);
            System.err.println(x + " " + y);
            giants.add(giantPosition);
        }
        System.err.println("giants end");
        return giants;
    }

    public static class Position {
        private int x;
        private int y;
        public Action action;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position(Position position) {
            this.x = position.x;
            this.y = position.y;
        }

        public Position() {
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public Position applyAction(Action action) {
            Position position = new Position();
            position.x = this.x + action.getX();
            position.y = this.y + action.getY();
            position.action = action;
            return position;
        }
    }

    private static class Borders {
        private int leftX;
        private int rightX;
        private int upY;
        private int downY;

        public Borders(int leftX, int rightX, int upY, int downY) {
            this.leftX = leftX;
            this.rightX = rightX;
            this.upY = upY;
            this.downY = downY;
        }

        public Borders() {

        }
    }
}
