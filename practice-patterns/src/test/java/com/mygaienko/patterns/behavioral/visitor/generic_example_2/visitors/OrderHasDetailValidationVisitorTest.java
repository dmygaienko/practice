package com.mygaienko.patterns.behavioral.visitor.generic_example_2.visitors;

import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.OrderHeader;
import org.junit.Test;

public class OrderHasDetailValidationVisitorTest {

    @Test
    public void test() {
        new OrderHasDetailValidationVisitor().visit(new OrderHeader());
    }
}