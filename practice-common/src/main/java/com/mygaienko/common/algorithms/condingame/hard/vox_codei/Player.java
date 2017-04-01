package com.mygaienko.common.algorithms.condingame.hard.vox_codei;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    private static int SIDE_EXPLOSION = 3;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        HashSet<Node> survNodes = new HashSet<>();
        List<List<Node>> grid = initGrid(in, survNodes);
        Map<Integer, Set<Node>> toExplode = new HashMap<>();

        // game loop

        for (int round = 0; true; round++) {
            int rounds = in.nextInt(); // number of rounds left before the end of the game
            int bombs = in.nextInt(); // number of bombs left

            processExplosion(toExplode, round);

            String result = "WAIT";
            BombLocationResult bombResult;
            if (bombs > 0 && !survNodes.isEmpty()) {
                bombResult = getMostProductiveBombLocation(grid, survNodes, bombs);
                if (NodeType.EMPTY.equals(bombResult.location.type)) {
                    result = bombResult.location.width + " " + bombResult.location.height;
                    mineNodes(survNodes, toExplode, bombResult.exploded, round + 3);
                }
            }
            System.out.println(result);
        }
    }

    private static void processExplosion(Map<Integer, Set<Node>> toExplode, int round) {
        Set<Node> nodes = toExplode.get(round);
        if (nodes != null) {
            for (Node node : nodes) {
                node.type = NodeType.EMPTY;
            }
        }
    }

    private static void mineNodes(Set<Node> survNodes, Map<Integer, Set<Node>> toExplode, Set<Node> exploded, int round) {
        for (Node node : exploded) {
            node.type = NodeType.MINED;
        }
        survNodes.removeAll(exploded);
        toExplode.put(round, exploded);
    }

    private static BombLocationResult getMostProductiveBombLocation(List<List<Node>> grid, HashSet<Node> survNodes, int bombs) {
        List<BombLocationResult> steps = grid.stream()
                .flatMap(line -> line.stream())
                .map(bombLocation -> explode(bombLocation, grid))
                .sorted((a, b) -> a.result() < b.result() ? 1 : (a.result() > b.result()) ? -1 : 0)
                .collect(toList());

        List<BombLocationResult> successSteps = defineStepsToExplodeAll(steps, (HashSet<Node>) survNodes.clone(), bombs);

        BombLocationResult result = null;
        if (successSteps != null) {
            result = successSteps.get(0);
        } else if (bombs > 1) {
            result = steps.get(0);
        }

        return result;
    }

    private static List<BombLocationResult> defineStepsToExplodeAll(List<BombLocationResult> steps, HashSet<Node> survNodes, int bombs) {
        List<BombLocationResult> successSteps = getNextStepToExplode(0, steps, new ArrayList<>(), survNodes, new HashSet<>(), bombs);
        return successSteps;
    }

    private static List<BombLocationResult> getNextStepToExplode(int i, List<BombLocationResult> steps, ArrayList<BombLocationResult> currentSteps,
                                                                 HashSet<Node> survNodes, HashSet<Object> exploded, int bombs) {
        List<BombLocationResult> bombLocationResults = countExplosions(i, steps, (ArrayList<BombLocationResult>) currentSteps.clone(), (HashSet<Node>) survNodes.clone(), (HashSet<Object>) exploded.clone(), bombs);
        if (bombLocationResults != null) {
            return bombLocationResults;
        } else if (i + 1 < steps.size() && steps.get(i).result() > 0) {
            return getNextStepToExplode(i + 1, steps, (ArrayList<BombLocationResult>) currentSteps.clone(), (HashSet<Node>) survNodes.clone(), (HashSet<Object>) exploded.clone(), bombs);
        } else {
            return null;
        }
    }

    private static List<BombLocationResult> countExplosions(int i, List<BombLocationResult> steps,
                                                            ArrayList<BombLocationResult> currentSteps, HashSet<Node> survNodes,
                                                            HashSet<Object> exploded, int bombs) {
        BombLocationResult step = steps.get(i);

        currentSteps.add(step);
        survNodes.removeAll(step.exploded);

        if (survNodes.isEmpty()) {
            return currentSteps;
        } else if (bombs == 1) {
            return null;
        } else {
            return getNextStepToExplode(i + 1, steps, currentSteps, survNodes, exploded, bombs - 1);
        }
    }

    private static BombLocationResult explode(Node bombLocation, List<List<Node>> grid) {
        BombLocationResult result = new BombLocationResult(bombLocation);
        NodeType type = bombLocation.type;
        if (NodeType.EMPTY.equals(type) || NodeType.MINED.equals(type)) {
            result.exploded.addAll(explodeUp(bombLocation, grid));
            result.exploded.addAll(explodeDown(bombLocation, grid));
            result.exploded.addAll(explodeRight(bombLocation, grid));
            result.exploded.addAll(explodeLeft(bombLocation, grid));
        }
        return result;
    }

    private static List<Node> explodeUp(Node bombLocation, List<List<Node>> grid) {
        ArrayList<Node> result = new ArrayList<>();
        for (int i = bombLocation.height - 1, l = 0; i > -1 && l < SIDE_EXPLOSION; i--, l++) {
            if (isNotExplosiveNode(grid, result, i, bombLocation.width)) break;
        }
        return result;
    }
    private static List<Node> explodeDown(Node bombLocation, List<List<Node>> grid) {
        ArrayList<Node> result = new ArrayList<>();
        for (int i = bombLocation.height + 1, l = 0; i < grid.size() && l < SIDE_EXPLOSION; i++, l++) {
            if (isNotExplosiveNode(grid, result, i, bombLocation.width)) break;
        }
        return result;
    }
    private static List<Node> explodeRight(Node bombLocation, List<List<Node>> grid) {
        ArrayList<Node> result = new ArrayList<>();
        List<Node> line = grid.get(bombLocation.height);
        for (int i = bombLocation.width + 1, l = 0; i < line.size() && l < SIDE_EXPLOSION; i++, l++) {
            if (isNotExplosiveNode(grid, result, bombLocation.height, i)) break;
        }
        return result;
    }
    private static List<Node> explodeLeft(Node bombLocation, List<List<Node>> grid) {
        ArrayList<Node> result = new ArrayList<>();
        for (int i = bombLocation.width - 1, l = 0; i > -1 && l < SIDE_EXPLOSION; i--, l++) {
            if (isNotExplosiveNode(grid, result, bombLocation.height, i)) break;
        }
        return result;
    }

    private static boolean isNotExplosiveNode(List<List<Node>> grid, ArrayList<Node> result, int height, int width) {
        Node node = grid.get(height).get(width);
        if (NodeType.PASSIVE.equals(node.type)){
            return true;
        } else if (NodeType.SURV.equals(node.type)) {
            result.add(node);
        }
        return false;
    }

    private static List<List<Node>> initGrid(Scanner in, Set<Node> survNodes) {
        int width = in.nextInt(); // width of the firewall grid
        int height = in.nextInt(); // height of the firewall grid
        if (in.hasNextLine()) {
            in.nextLine();
        }

        List<List<Node>> grid = new ArrayList<>(height);

        IntStream.range(0, height)
                .forEach(h -> {
                    String[] splitted = in.nextLine().split("");
                    List<Node> line = initLines(width, h, splitted, survNodes);
                    grid.add(line);
                });

        return grid;
    }

    private static List<Node> initLines(int width, int h, String[] splitted, Set<Node> survNodes) {
        return IntStream.range(0, width)
                                .mapToObj(w -> {
                                    Node node = new Node(splitted[w], w, h);
                                    if (NodeType.SURV.equals(node.type)) {
                                        survNodes.add(node);
                                    }
                                    return node;
                                })
                                .collect(toList());
    }

    private static class Node {
        private NodeType type;
        private int width;
        private int height;

        public Node(String type, int width, int height) {
            this.type = NodeType.getNode(type);
            this.width = width;
            this.height = height;
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

        @Override
        public String toString() {
            return "Node{" +
                    "type=" + type +
                    ", width=" + width +
                    ", height=" + height +
                    '}';
        }
    }

    enum  NodeType {
        EMPTY, SURV, PASSIVE, MINED;

        static NodeType getNode(String s) {
            NodeType result = null;
            switch (s) {
                case "." : result = EMPTY; break;
                case "#" : result = PASSIVE; break;
                case "@" : result = SURV; break;
            }
            return result;
        }

    }

    private static class BombLocationResult {
        private Node location;
        private Set<Node> exploded = new HashSet<>();

        public BombLocationResult(Node bombLocation) {
            location = bombLocation;
        }

        public int result() {
            return exploded.size();
        }
    }
}