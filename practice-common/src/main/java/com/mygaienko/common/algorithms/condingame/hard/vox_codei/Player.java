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

        Set<Node> survNodes = new HashSet<>();
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
                bombResult = getMostProductiveBombLocation(grid);
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

   /* private static BombLocationResult getMostProductiveBombLocation(List<List<Node>> grid) {
        //List<Node> bombLocations = getNearestBombLocations(next);
        List<BombLocationResult> steps = grid.stream()
                .flatMap(line -> line.stream())
                .map(bombLocation -> explode(bombLocation, grid))
                .sorted((a, b) -> a.result() < b.result() ? 1 : (a.result() > b.result()) ? -1 : 0)
                .collect(toList());

        List<BombLocationResult> successSteps = defineStepsToExplodeAll(steps);
        return successSteps.get(0);
    }*/


    private static BombLocationResult getMostProductiveBombLocation(List<List<Node>> grid) {
        //List<Node> bombLocations = getNearestBombLocations(next);
        return grid.stream()
                .flatMap(line -> line.stream())
                .map(bombLocation -> explode(bombLocation, grid))
                .reduce((r1, r2) -> r2.result() > r1.result() ? r2 : r1)
                .get();
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