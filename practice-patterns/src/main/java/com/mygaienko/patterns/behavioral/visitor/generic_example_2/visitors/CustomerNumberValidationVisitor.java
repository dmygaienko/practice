package com.mygaienko.patterns.behavioral.visitor.generic_example_2.visitors;

import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.OrderHeader;
import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.ValidationError;

/**
 * Created by dmygaenko on 23/09/2016.
 */
public class CustomerNumberValidationVisitor implements Visitor<OrderHeader>{

    public void visit(OrderHeader header) {
        Long customerNumber = header.getCustomerId();
        if (customerNumber == null || customerNumber == 0L) {
            header.addValidationError(new ValidationError("Invalid Customer Number"));
        }
    }
}
