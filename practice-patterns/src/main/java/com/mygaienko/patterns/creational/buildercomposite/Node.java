package com.mygaienko.patterns.creational.buildercomposite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmygaenko on 25/03/2016.
 */
public class Node {

    private String name;

    private Node parent;
    private List<Node> children = new ArrayList<Node>();

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public Node getParent() {
        return parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
