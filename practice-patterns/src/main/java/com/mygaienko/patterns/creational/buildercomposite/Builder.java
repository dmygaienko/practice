package com.mygaienko.patterns.creational.buildercomposite;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by dmygaenko on 25/03/2016.
 */
public class Builder {

    private Node root;
    private Node current;

    public Builder(String name) {
        root = new Node(name);
        current = root;
    }

    public Builder addChild(String name) {
        addTo(current, name);
        return this;
    }

    private void addTo(Node parent, String name) {
        Node node = new Node(name, parent);
        parent.addChild(node);
        current = node;
    }

    public Builder addSibling(String name) {
        addTo(current.getParent(), name);
        return this;
    }

    public Node getRoot() {
        return root;
    }

    public Builder moveUp() {
        if (current.getParent() != null) {
            current = current.getParent();
        }
        return this;
    }

    public Builder moveRight() {
        move(false);
        return this;
    }

    public Builder moveLeft() {
        move(true);
        return this;
    }

    private void move(boolean moveLeft) {
        Node parent = current.getParent();
        if (parent != null) {
            List<Node> children = parent.getChildren();
            if (CollectionUtils.isNotEmpty(children) && children.size() > 1) {
                for (int i = 0; i < children.size(); i++) {
                    Node node = children.get(i);
                    if (node.getName().equals(current.getName())) {
                        if (moveLeft && i > 0 ) {
                            current = children.get(i-1);
                        } else if (!moveLeft && i+1 < children.size()) {
                            current = children.get(i+1);
                        }
                        break;
                    }
                }
            }
        }
    }

}
