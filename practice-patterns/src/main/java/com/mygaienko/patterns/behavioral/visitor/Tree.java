package com.mygaienko.patterns.behavioral.visitor;

/**
 * Created by dmygaenko on 19/05/2016.
 */
abstract class Tree<E> {

    abstract public String toString();
    abstract public Double sum();


    public static <E> Tree<E> leaf(final E e) {
        return new Tree<E>() {
            public String toString() {
                return e.toString();
            }
            public Double sum() {
                return ((Number)e).doubleValue();
            }
            public <R> R visit(Visitor<E, R> v) {
                return v.leaf(e);
            }
        };
    }

    public static <E> Tree<E> branch(final Tree<E> left, final Tree<E> right) {
        return new Tree<E>() {
            public String toString() {
                return "("+left.toString()+"^"+right.toString()+")";
            }
            public Double sum() {
                return left.sum() + right.sum();
            }
            public <R> R visit(Visitor<E, R> v) {
                return v.branch(left.visit(v), right.visit(v));
            }
        };
    }

    public interface Visitor<E, R> {
        R leaf(E elt);
        R branch(R left, R right);
    }
    public abstract <R> R visit(Visitor<E, R> v);
}
