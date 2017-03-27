package com.mygaienko.common.algorithms.condingame.hard.the_bridge_episode2;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
public class Player {

    private static Point[][] bridge = new Point[4][];

    private static Map<Integer, List<List<Action>>> bikeActions = new HashMap<>();
    private static int stepsForward = 5;
    private static List<List<Action>> allActions = generateAllPossibleActions();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt(); // the amount of motorbikes to control
        int V = in.nextInt(); // the minimum amount of motorbikes that must survive

        initBridge(in);

        // game loop
        while (true) {
            int speed = in.nextInt(); // the motorbikes' speed
            for (int bikeId = 0; bikeId < M; bikeId++) {
                int x = in.nextInt(); // x coordinate of the motorbike
                int y = in.nextInt(); // y coordinate of the motorbike
                int a = in.nextInt(); // indicates whether the motorbike is activated "1" or destroyed "0"

                if (a != 0) {
                    filterSafeActionForBike(bikeId, speed, x, y);
                }
            }

            findCommonActionsBetweenBikes();

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            // A single line containing one of 6 keywords: SPEED, SLOW, JUMP, WAIT, UP, DOWN.
            System.out.println("SPEED");
        }
    }

    private static void findCommonActionsBetweenBikes() {
        //TODO
    }

    private static void filterSafeActionForBike(int bikeId, int speed, int x, int y) {
        List<List<Action>> safeActions = allActions.stream()
                .filter(actions -> isSafe(actions, speed, x, y))
                .collect(toList());
        bikeActions.put(bikeId, safeActions);
    }

    private static boolean isSafe(List<Action> actions, int speed, int x, int y) {
        ActionContext currentActionContext = new ActionContext(speed, x, y); 
        
        for (Action action : actions) {
            currentActionContext = action.doAction(currentActionContext);
            if (!currentActionContext.result) {
                return false;
            }
        }

        return true;
    }

    private static List<List<Action>> generateAllPossibleActions() {
        List<List<Action>> actions = new ArrayList<>();
        Action[] values = Action.values();
        permute(values, 0, 5, new ArrayList<>(), actions);
        return Collections.EMPTY_LIST;
    }

    private static void permute(Player.Action[] values, int nextValue, int maxLength,
                               List<Player.Action> currentActions, List<List<Player.Action>> allActions) {

        currentActions.add(values[nextValue]);
        if (currentActions.size() < maxLength) {
            for (int i = 0; i < maxLength; i++) {
                permute(values, i, maxLength, new ArrayList<>(currentActions), allActions);
            }
        } else {
            allActions.add(currentActions);
        }
    }

    private static void initBridge(Scanner in) {
        String safeWay = "..........";
        initLine(0, in.next() + safeWay);
        initLine(1, in.next() + safeWay);
        initLine(2, in.next() + safeWay);
        initLine(3, in.next() + safeWay);
    }

    private static void initLine(int y, String s) {
        String[] way = s.split("");
        for (int x = 0, wayLength = way.length; x < wayLength; x++) {
            String step = way[x];
            boolean safe = ".".equals(step);
            bridge[y][x] = new Point(safe);
        }
    }

    private static class Point {
        private final boolean safe;

        public Point(boolean safe) {
            this.safe = safe;
        }
    }

    //SPEED, SLOW, JUMP, WAIT, UP, DOWN
    public enum Action {
        SPEED {
            ActionContext doAction(ActionContext context) {
                context.speed++;
                context.countNextX();
                context.result = checkStraightLine(bridge[context.y], context.x, context.speed);        
                return context;
            }
        },
        SLOW {
            ActionContext doAction(ActionContext context) {
                context.speed = context.speed > 0 ? context.speed - 1 : 0;
                context.countNextX();
                context.result = checkStraightLine(bridge[context.y], context.x, context.speed);
                return context;
            }
        },
        JUMP {
            ActionContext doAction(ActionContext context) {
                context.countNextX();
                context.result = checkNextPoint(bridge[context.y], context.x, context.speed);
                return context;
            }
        },
        WAIT {
            ActionContext doAction(ActionContext context) {
                context.countNextX();
                context.result = checkStraightLine(bridge[context.y], context.x, context.speed);
                return context;
            }
        },
        UP {
            ActionContext doAction(ActionContext context) {
                context.y--;
                context.countNextX();
                context.result = checkVerticalMoving(context);
                return context;
            }


        },
        DOWN {
            ActionContext doAction(ActionContext context) {
                context.y++;
                context.countNextX();
                context.result = checkVerticalMoving(context);
                return context;
            }
        };

        private static boolean checkVerticalMoving(ActionContext context) {
            return checkVerticalBounds(context.y)
                    && checkNextPoint(bridge[context.y], context.x, context.speed);
        }

        private static boolean checkVerticalBounds(int y) {
            return y > -1 && y < bridge.length;
        }

        private static boolean checkNextPoint(Point[] points, int x, int speed) {
            return points[x + speed].safe;
        }

        private static boolean checkStraightLine(Point[] points, int x, int speed) {
            for (int i = x; i < x + speed; i++) {
                if (!points[x].safe) {
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

        void countNextX() {
            this.x += this.speed;
        }
    }

}