package com.mygaienko.common.algorithms.interv.tree;

import java.util.Random;

class RootNode implements Node {

    private final Node right;
    private final Node left;

    private final Integer secondaryAttribute;

    RootNode(Node right, Node left) {
        this.right = right;
        this.left = left;
        secondaryAttribute = new Random().nextInt();
    }

    @Override
    public Long getCount() {
        Long count = right.getCount();
        count += left == null ? 0 : left.getCount();
        return count;
    }

    @Override
    public Integer getSecondaryAttribute() {
        return secondaryAttribute;
    }

    @Override
    public String toString() {
        return "RootNode{" +
                "value=" + getCount() +
                ", right=" + System.lineSeparator() + " " + right +
                ", left=" + System.lineSeparator() + " " + left +
                '}';
    }

}
