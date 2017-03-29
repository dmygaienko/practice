package com.mygaienko.common.algorithms.condingame.hard.the_bridge_episode2;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
public class Player {

    private static Point[][] bridge = new Point[4][];

    private static int stepsForward = 5;
    private static int minAvg = 2;
    private static int maxAvg = 5;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt(); // the amount of motorbikes to control
        int V = in.nextInt(); // the minimum amount of motorbikes that must survive

        initBridge(in);

        // game loop
        while (true) {
            Map<Integer, List<Actions>> bikeActions = new HashMap<>();

            int speed = in.nextInt(); // the motorbikes' speed
            for (int bikeId = 0; bikeId < M; bikeId++) {
                int x = in.nextInt(); // x coordinate of the motorbike
                int y = in.nextInt(); // y coordinate of the motorbike
                int a = in.nextInt(); // indicates whether the motorbike is activated "1" or destroyed "0"

                System.err.println(" speed " + speed + " x " + x + " y " + y + " a " + a);
                if (a != 0) {
                    bikeActions.put(bikeId, generateSafeActions(speed, x, y));
                }
            }

            List<Action> commonActions = findCommonActionsBetweenBikes(bikeActions);

            // A single line containing one of 6 keywords: SPEED, SLOW, JUMP, WAIT, UP, DOWN.
            System.out.println(commonActions.get(0).name());
        }
    }

    private static List<Action> findCommonActionsBetweenBikes(Map<Integer, List<Actions>> bikeActions) {
        Map<Actions, Long> collect = bikeActions.values().stream()
                .flatMap(Collection::stream)
                .collect(groupingBy(actions -> actions, Collectors.counting()));

        Optional<Map.Entry<Actions, Long>> mostCommon = collect.entrySet().stream()
                .reduce(reduceByMatchesAndAverageSpeed());
        return mostCommon.get().getKey().value;
    }

    private static BinaryOperator<Map.Entry<Actions, Long>> reduceByMatchesAndAverageSpeed() {
        return (entry1, entry2) -> {
            Map.Entry<Actions, Long> result;
            long value1 = entry1.getValue();
            long value2 = entry2.getValue();

            if (value1 > value2) {
                result = entry1;
            } else if (value1 < value2) {
                result = entry2;
            } else {
                result = getActionsWithClosestToAvgSpeed(entry1, entry2);
            }
            return  result;
        };
    }

    private static Map.Entry<Actions, Long> getActionsWithClosestToAvgSpeed(
            Map.Entry<Actions, Long> entry1, Map.Entry<Actions, Long> entry2) {
        int average1 = entry1.getKey().average;
        int average2 = entry2.getKey().average;

        Map.Entry<Actions, Long> result;
        if (minAvg <= average1 && average1 <= maxAvg) {
            result = entry1;
        } else if (minAvg <= average2 && average2 <= maxAvg){
            result = entry2;
        } else if (1 <= average1) {
            result = entry1;
        } else {
            result = entry2;
        }
        return result;
    }

    private static List<Actions> generateSafeActions(int speed, int x, int y) {
        List<Actions> allSafeActions = new ArrayList<>();
        permuteActions(Action.values(), stepsForward, new Actions(), allSafeActions, new ActionContext(speed, x, y));
        return allSafeActions;
    }

    private static void permuteActions(Player.Action[] actionValues, int maxActionLength,
                                       Actions currentActions, List<Actions> allSafeActions,
                                       ActionContext actionContext) {

        if (currentActions.size() < maxActionLength) {
            for (int i = 0; i < actionValues.length; i++) {
                checkAndPermute(actionValues, i, maxActionLength, new Actions(currentActions), allSafeActions, actionContext);
            }
        } else {
            currentActions.countAverage();
            allSafeActions.add(currentActions);
        }
    }

    private static void checkAndPermute(Player.Action[] actionValues, int nextAction, int maxActionLength,
                                       Actions currentActions, List<Actions> allSafeActions,
                                       ActionContext actionContext) {
        Action action = actionValues[nextAction];
        ActionContext nextActionContext = action.doAction(actionContext);
        if (!nextActionContext.result) {
            return;
        }
        currentActions.add(action, nextActionContext.speed);

        permuteActions(actionValues, maxActionLength, currentActions, allSafeActions, nextActionContext);
    }

    private static void initBridge(Scanner in) {
        String safeWay = "............................";
        initLine(0, in.next() + safeWay);
        initLine(1, in.next() + safeWay);
        initLine(2, in.next() + safeWay);
        initLine(3, in.next() + safeWay);
    }

    public static void initLine(int y, String s) {
        String[] way = s.split("");
        bridge[y] = new Point[way.length];
        for (int x = 0, wayLength = way.length; x < wayLength; x++) {
            String step = way[x];
            boolean safe = ".".equals(step);
            bridge[y][x] = new Point(safe);
        }
    }

    public static class Point {
        private final boolean safe;

        public Point(boolean safe) {
            this.safe = safe;
        }
    }

    //SPEED, SLOW, JUMP, WAIT, UP, DOWN
    public enum Action {
        SPEED {
            ActionContext doAction(ActionContext context) {
                int nextSpeed = context.speed + 1;
                boolean result = checkStraightLine(bridge[context.y], context.x, nextSpeed);
                int nextX = context.x + nextSpeed;
                return new ActionContext(nextSpeed, nextX, context.y, result);
            }
        },
        SLOW {
            ActionContext doAction(ActionContext context) {
                int nextSpeed = context.speed > 0 ? context.speed - 1 : 0;
                boolean result = nextSpeed > 0 && checkStraightLine(bridge[context.y], context.x, nextSpeed);
                int nextX = context.x + nextSpeed;
                return new ActionContext(nextSpeed, nextX, context.y, result);
            }
        },
        JUMP {
            ActionContext doAction(ActionContext context) {
                int nextX = context.x + context.speed;
                boolean result = checkNextPoint(bridge[context.y], context.x, context.speed);
                return new ActionContext(context.speed, nextX, context.y, result);
            }
        },
        WAIT {
            ActionContext doAction(ActionContext context) {
                int nextX = context.x + context.speed;
                boolean result = checkStraightLine(bridge[context.y], context.x, context.speed);
                return new ActionContext(context.speed, nextX, context.y, result);
            }
        },
        UP {
            ActionContext doAction(ActionContext context) {
                int nextX = context.x + context.speed;
                int nextY = context.y - 1;
                boolean result = checkVerticalMoving(nextX, nextY, context.speed);
                return new ActionContext(context.speed, nextX, nextY, result);
            }
        },
        DOWN {
            ActionContext doAction(ActionContext context) {
                int nextX = context.x + context.speed;
                int nextY = context.y + 1;
                boolean result = checkVerticalMoving(nextX, nextY, context.speed);
                return new ActionContext(context.speed, nextX, nextY, result);
            }
        };

        private static boolean checkVerticalMoving(int x, int y, int speed) {
            return checkVerticalBounds(y) && checkNextPoint(bridge[y], x, speed)
                    && checkStraightLine(bridge[y], x, speed);
        }

        private static boolean checkVerticalBounds(int y) {
            return y > -1 && y < bridge.length;
        }

        private static boolean checkNextPoint(Point[] points, int x, int speed) {
            int nextX = x + speed;
            return nextX >= points.length || points[nextX].safe;
        }

        private static boolean checkStraightLine(Point[] points, int x, int speed) {
            for (int i = x; i < x + speed && i < points.length; i++) {
                if (!points[i].safe) {
                    return false;
                }
            }
            return true;
        }

        abstract ActionContext doAction(ActionContext context);
    }

    private static class ActionContext {
        private int speed;
        private int x;
        private int y;
        private boolean result;

        public ActionContext(int speed, int x, int y) {
            this.speed = speed;
            this.x = x;
            this.y = y;
        }

        public ActionContext(int speed, int x, int y, boolean result) {
            this(speed, x, y);
            this.result = result;
        }
    }

    private static class Actions extends AbstractList<Action> {
        
        private int average = 0;
        private List<Action> value = new ArrayList<>();
        private List<Integer> speeds = new ArrayList<>();

        public Actions(Actions currentActions) {
            value.addAll(currentActions.value);
            speeds.addAll(currentActions.speeds);
        }

        public Actions() {

        }

        public void countAverage() {
            Optional<Integer> sum = speeds.stream().reduce((aLong1, aLong2) -> aLong1 + aLong2);
            average = sum.orElse(0)/ speeds.size();
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return value.equals(obj);
        }

        @Override
        public int size() {
            return value.size();
        }

        @Override
        public boolean isEmpty() {
            return value.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return value.contains(o);
        }

        @Override
        public Iterator<Action> iterator() {
            return value.iterator();
        }

        @Override
        public Object[] toArray() {
            return value.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return value.toArray(a);
        }

        @Override
        public boolean add(Action action) {
            return value.add(action);
        }

        @Override
        public void add(int index, Action action) {
            value.add(index, action);
        }

        @Override
        public Action get(int index) {
            return value.get(index);
        }

        @Override
        public boolean remove(Object o) {
            return value.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return value.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends Action> c) {
            return value.addAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return value.removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return value.retainAll(c);
        }

        @Override
        public void clear() {
            value.clear();
        }

        @Override
        public Stream<Action> stream() {
            return value.stream();
        }

        public void add(Action action, int speed) {
            value.add(action);
            speeds.add(speed);
        }
    }
}