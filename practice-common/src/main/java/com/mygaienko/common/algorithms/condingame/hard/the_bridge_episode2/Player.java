package com.mygaienko.common.algorithms.condingame.hard.the_bridge_episode2;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
public class Player {

    private static Point[][] bridge = new Point[4][];

    private static int stepsForward = 5;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int M = 1;
        PlayerUtil.initBridge();
        /*int M = in.nextInt(); // the amount of motorbikes to control
        int V = in.nextInt(); // the minimum amount of motorbikes that must survive

        initBridge(in);*/

        // game loop
        while (true) {
            Map<Integer, List<List<Action>>> bikeActions = new HashMap<>();

            int speed = 4; // the motorbikes' speed
            for (int bikeId = 0; bikeId < M; bikeId++) {
                int x = 10; // x coordinate of the motorbike
                int y = 2; // y coordinate of the motorbike
                int a = 1; // indicates whether the motorbike is activated "1" or destroyed "0"

                if (a != 0) {
                    bikeActions.put(bikeId, generateSafeActions(speed, x, y));
                }
            }

            List<Action> commonActions = findCommonActionsBetweenBikes(bikeActions);

            // A single line containing one of 6 keywords: SPEED, SLOW, JUMP, WAIT, UP, DOWN.
            System.out.println(commonActions.get(0).name());
        }
    }

    private static List<Action> findCommonActionsBetweenBikes(Map<Integer, List<List<Action>>> bikeActions) {
        Map<List<Action>, List<List<Action>>> collect = bikeActions.values().stream()
                .flatMap(Collection::stream)
                .collect(groupingBy(actions -> actions));
        Optional<Map.Entry<List<Action>, List<List<Action>>>> mostCommon = collect.entrySet().stream()
                .reduce((entry1, entry2) -> entry1.getValue().size() > entry2.getValue().size() ? entry1 : entry2);
        return mostCommon.get().getKey();
    }

    private static List<List<Action>> generateSafeActions(int speed, int x, int y) {
        List<List<Action>> actions = new ArrayList<>();
        permuteActions(Action.values(), stepsForward, new ArrayList<>(), actions, new ActionContext(speed, x, y));
        return actions;
    }

    private static void permuteActions(Player.Action[] actionValues, int maxActionLength,
                                       List<Player.Action> currentActions, List<List<Player.Action>> allSafeActions,
                                       ActionContext actionContext) {

        if (currentActions.size() < maxActionLength) {
            for (int i = 0; i < actionValues.length; i++) {
                checkAndPermute(actionValues, i, maxActionLength, new ArrayList<>(currentActions), allSafeActions, actionContext);
            }
        } else {
            allSafeActions.add(currentActions);
        }
    }

    private static void checkAndPermute(Player.Action[] actionValues, int nextAction, int maxActionLength,
                                       List<Player.Action> currentActions, List<List<Player.Action>> allSafeActions,
                                       ActionContext actionContext) {
        Action action = actionValues[nextAction];
        ActionContext nextActionContext = action.doAction(actionContext);
        if (!nextActionContext.result) {
            return;
        }
        currentActions.add(action);

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
            for (int i = x; i < x + speed; i++) {
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

}