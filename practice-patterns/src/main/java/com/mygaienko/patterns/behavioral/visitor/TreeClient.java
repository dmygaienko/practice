package com.mygaienko.patterns.behavioral.visitor;

/**
 * Created by dmygaenko on 19/05/2016.
 */
class TreeClient {

    public static void main(String[] args) {
        Tree<Integer> t =
                Tree.branch(Tree.branch(Tree.leaf(1),
                        Tree.leaf(2)),
                        Tree.leaf(3));
        assert t.toString().equals("((1^2)^3)");
        assert t.sum() == 6;
    }

}
