package com.mygaienko.common.algorithms.interv.tree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BinaryTreeTest {

    @Test
    public void testSingletonMap() {
        System.out.println(new BinaryTree(createSingletonMap()).toString());
    }

    @Test
    public void testDoubleMap() {
        System.out.println(new BinaryTree(createDoubleMap()).toString());
    }

    private Map<String, Long> createDoubleMap() {
        HashMap<String, Long> map = new HashMap<>();
        map.put("key1", 1L);
        map.put("key2", 3L);
        return map;
    }

    @Test
    public void testTripleMap() {
        BinaryTree binaryTree = new BinaryTree(createTripleMap());
        System.out.println(binaryTree.toString());
    }

    private Map<String, Long> createTripleMap() {
        HashMap<String, Long> map = new HashMap<>();
        map.put("key1", 1L);
        map.put("key2", 2L);
        map.put("key3", 3L);
        return map;
    }

    @Test
    public void testMultiMap() {
        System.out.println(new BinaryTree(createMultiMap()).toString());
    }

    private Map<String, Long> createMultiMap() {
        HashMap<String, Long> map = new HashMap<>();
        map.put("key1", 1L);
        map.put("key2", 2L);
        map.put("key3", 3L);
        map.put("key4", 4L);
        map.put("key5", 5L);
        return map;
    }

    private Map<String, Long> createSingletonMap() {
        HashMap<String, Long> map = new HashMap<>();
        map.put("key", 1L);
        return map;
    }
}