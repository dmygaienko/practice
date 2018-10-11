package com.mygaienko.common.algorithms.interv.tree;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BinaryTree {

    private Node root;

    public BinaryTree(Map<String, Long> map) {
        if (MapUtils.isEmpty(map)) {
            throw new IllegalArgumentException("Map should not be empty");
        }
        initTree(map);
    }

    private void initTree(Map<String, Long> map) {
        TreeSet<Node> sortedSet = createSet(map);
        root = buildRoot(sortedSet);
    }

    private Node buildRoot(TreeSet<Node> sortedSet) {
        while (sortedSet.size() > 1) {
            sortedSet.add(new RootNode(sortedSet.pollFirst(), sortedSet.pollFirst()));
        }
        return sortedSet.first();
    }

    private TreeSet<Node> createSet(Map<String, Long> map) {
        return map.entrySet().stream()
                .map(entry -> new LeafNode(entry.getKey(), entry.getValue()))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public String toString() {
        return root != null ? root.toString() : "Binary tree is empty";
    }

}
