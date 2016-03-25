package com.mygaienko.patterns.creational.buildercomposite;

/**
 * Created by dmygaenko on 25/03/2016.
 */
public class Main {

    public static void main(String[] args) {
        Node root = new Builder("root")
                .addChild("child1.1")
                .addSibling("child1.2")
                .addSibling("child1.3")
                .moveLeft().moveLeft()
                .addChild("child2.1")
                .moveUp()
                .moveRight()
                .addChild("child2.2")
                .getRoot();
        System.out.println(root);
    }
}
