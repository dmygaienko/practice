package com.mygaienko.patterns.behavioral.visitor.generic_example_2.service;

import com.mygaienko.patterns.behavioral.command.Result;
import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.OrderDetail;
import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.OrderHeader;
import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.Part;
import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.ValidationError;
import com.mygaienko.patterns.behavioral.visitor.generic_example_2.visitors.*;

import java.util.List;

/**
 * Created by dmygaenko on 23/09/2016.
 */
public class VisitorService {

    public void validate(OrderHeader header) {
        CustomerNumberValidationVisitor visitor1 = new CustomerNumberValidationVisitor();
        OrderHasDetailValidationVisitor visitor2 = new OrderHasDetailValidationVisitor();
        QuantityValidationVisitor visitor3 = new QuantityValidationVisitor();
        DetailHasPartValidationVisitor visitor4 = new DetailHasPartValidationVisitor();
        PartNumberValidationVisitor visitor5 = new PartNumberValidationVisitor();
        PriceValidationVisitor visitor6 = new PriceValidationVisitor();

        header.accept(visitor1);
        header.accept(visitor2);
        List<OrderDetail> details = header.getOrderDetails();

        for (OrderDetail detail: details) {
            detail.accept(visitor3);
            detail.accept(visitor4);
            Part part = detail.getPart();
            part.accept(visitor5);
            part.accept(visitor6);
        }

        for (ValidationError error : header.getValidationErrors()) {
            System.out.println("Error! " + error.getErrorDescription());
        }

    }
}
