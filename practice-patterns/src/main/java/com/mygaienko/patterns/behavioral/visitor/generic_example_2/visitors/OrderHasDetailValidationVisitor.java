package com.mygaienko.patterns.behavioral.visitor.generic_example_2.visitors;

import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.OrderDetail;
import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.OrderHeader;
import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.ValidationError;

import java.util.List;

/**
 * Created by dmygaenko on 23/09/2016.
 */
public class OrderHasDetailValidationVisitor implements Visitor<OrderHeader> {

    public void visit(OrderHeader header) {
        List<OrderDetail> details = header.getOrderDetails();
        if (details == null || details.size() == 0) {
            header.addValidationError(new ValidationError("There are no Order Lines"));
        }
    }
}
