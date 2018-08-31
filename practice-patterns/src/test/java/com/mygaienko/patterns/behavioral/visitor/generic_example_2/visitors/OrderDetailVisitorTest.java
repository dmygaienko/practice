package com.mygaienko.patterns.behavioral.visitor.generic_example_2.visitors;

import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.OrderDetail;
import org.junit.Test;

public class OrderDetailVisitorTest {

    @Test
    public void test() {
        OrderDetail orderDetail = new OrderDetail();
        new OrderDetailVisitor().visit(orderDetail);
    }
}