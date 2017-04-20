package com.mygaienko.common.algorithms.condingame.very_hard.vox_codei2;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int width = in.nextInt(); // width of the firewall grid
        int height = in.nextInt(); // height of the firewall grid

        Grid grid = initGrid(in, width, height);

        // game loop
        while (true) {
            int rounds = in.nextInt(); // number of rounds left before the end of the game
            int bombs = in.nextInt(); // number of bombs left

            Grid nextStepGrid = initGrid(in, width, height);

            List<Supervisor> supervisor = analyzeSupervisors(grid, nextStepGrid);

            System.out.println("3 0");
        }
    }

    private static List<Supervisor> analyzeSupervisors(Grid prevStepGrid, Grid nextStepGrid) {
        return prevStepGrid.getSupervisors()
                .stream()
                .map(prevSupervisor -> {

                    Supervisor analyzed = null;
                    for (MovingDirection movingDirection : MovingDirection.values()) {
                        Supervisor nextStepSupervisor = movingDirection.move(prevSupervisor);
                        if (nextStepGrid.exists(nextStepSupervisor)) {
                            analyzed = nextStepSupervisor;
                        }
                    }
                    return analyzed;
                })
                .collect(toList());
    }

    private static Grid initGrid(Scanner in, int width, int height) {
        if (in.hasNextLine()) {
            in.nextLine();
        }

        Grid grid = new Grid(height, width);
        IntStream.range(0, height)
                .forEach(h -> {
                    String[] splitted = in.nextLine().split("");
                    List<Node> line = initLines(width, h, splitted, grid);
                    grid.addLine(line);
                });

        return grid;
    }

    private static List<Node> initLines(int width, int h, String[] splitted, Grid grid) {
        return IntStream.range(0, width)
                .mapToObj(w -> {
                    Node node = NodeFactory.getNode(splitted[w], w, h);
                    if (node instanceof Supervisor) {
                        grid.addSupervisor((Supervisor) node);
                    }
                    return node;
                })
                .collect(toList());
    }

    static class Node {

        private final int width;
        private final int height;

        public Node(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (width != node.width) return false;
            return height == node.height;

        }

        @Override
        public int hashCode() {
            int result = width;
            result = 31 * result + height;
            return result;
        }
    }

    static class NodeFactory {

        static Node getNode(String s, int width, int height) {
            Node node = null;
            switch (s) {
                case "." : node = new EmptyNode(width, height); break;
                case "#" : node = new PassiveNode(width, height); break;
                case "@" : node = new Supervisor(width, height); break;
            }
            return node;
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

    static class Supervisor extends Node {

        private MovingDirection direction;

        public Supervisor(int width, int height) {
            this(width, height, MovingDirection.NONE);
        }

        public Supervisor(int width, int height, MovingDirection direction) {
            super(width, height);
            this.direction = direction;
        }
    }

    enum MovingDirection {

        UP {
            @Override
            public Supervisor move(Supervisor currentNode) {
                return new Supervisor(currentNode.getWidth(), currentNode.getHeight() - 1, this);
            }
        },
        RIGHT {
            @Override
            public Supervisor move(Supervisor currentNode) {
                return new Supervisor(currentNode.getWidth() - 1, currentNode.getHeight(), this);
            }
        },
        DOWN {
            @Override
            public Supervisor move(Supervisor currentNode) {
                return new Supervisor(currentNode.getWidth(), currentNode.getHeight() + 1, this);
            }
        },
        LEFT {
            @Override
            public Supervisor move(Supervisor currentNode) {
                return new Supervisor(currentNode.getWidth() + 1, currentNode.getHeight(), this);
            }
        },
        NONE {
            @Override
            public Supervisor move(Supervisor currentNode) {
                return new Supervisor(currentNode.getWidth(), currentNode.getHeight(), this);
            }
        };

        public abstract Supervisor move(Supervisor currentNode);
    }

    private static class Grid {

        private final List<List<Node>> lines;
        private final int height;
        private final int width;

        private Set<Supervisor> supervisors = new HashSet<>();

        public Grid(int height, int width) {
            lines = new ArrayList<>(height);
            this.height = height;
            this.width = width;
        }

        public void addLine(List<Node> line) {
            lines.add(line);
        }

        public void addSupervisor(Supervisor supervisor) {
            supervisors.add(supervisor);
        }

        public Set<Supervisor> getSupervisors() {
            return supervisors;
        }

        public boolean exists(Supervisor nextStepSupervisor) {
            int supervisorWidth = nextStepSupervisor.getWidth();
            int supervisorHeight = nextStepSupervisor.getHeight();

            if (supervisorWidth > -1 && supervisorWidth <= width
                    && supervisorHeight > -1 && supervisorHeight <= height
                    && lines.get(supervisorHeight).get(supervisorWidth) instanceof Supervisor) {
                return true;
            }

            return false;
        }
    }
}