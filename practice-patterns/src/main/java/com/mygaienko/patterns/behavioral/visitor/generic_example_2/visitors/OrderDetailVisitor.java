package com.mygaienko.patterns.behavioral.visitor.generic_example_2.visitors;

import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.OrderDetail;

/**
 * Created by dmygaenko on 23/09/2016.
 */
public class OrderDetailVisitor implements Visitor<OrderDetail>{

    public void visit(OrderDetail detail) {
        System.out.println(detail);
    }
}
