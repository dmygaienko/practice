package com.mygaienko.patterns.behavioral.visitor.generic_example_2.visitors;

import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.OrderDetail;
import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.Part;
import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.ValidationError;

/**
 * Created by dmygaenko on 23/09/2016.
 */
public class DetailHasPartValidationVisitor implements Visitor<OrderDetail>{

    public void visit(OrderDetail detail) {
        Part part = detail.getPart();
        if (part == null) {
            detail.getOrderHeader().addValidationError(
                    new ValidationError("Detail line detail.getDetailId() has no part"));
        }
    }
}