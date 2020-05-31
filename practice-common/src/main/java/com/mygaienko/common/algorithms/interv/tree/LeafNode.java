package com.mygaienko.common.algorithms.interv.tree;


class LeafNode implements Node {

    private final String value;
    private final Long count;

    LeafNode(String value, Long count) {
        this.value = value;
        this.count = count;
    }

    @Override
    public Long getCount() {
        return count;
    }

    @Override
    public Integer getSecondaryAttribute() {
        return value.hashCode();
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "LeafNode{" +
                "value='" + value + '\'' +
                ", count=" + count +
                '}';
    }
}
