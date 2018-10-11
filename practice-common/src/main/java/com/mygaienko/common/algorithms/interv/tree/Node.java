package com.mygaienko.common.algorithms.interv.tree;

import java.util.Comparator;

interface Node extends Comparable<Node> {

    Long getCount();

    Integer getSecondaryAttribute();

    @Override
    default int compareTo(Node that) {
        return Comparator
                .comparing(Node::getCount)
                .thenComparing(Node::getSecondaryAttribute)
                .compare(this, that);
    }

}
