package com.mygaienko.patterns.behavioral.visitor.generic_example_2.visitors;

import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.Part;
import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.ValidationError;


/**
 * Created by dmygaenko on 23/09/2016.
 */
public class PartNumberValidationVisitor implements Visitor<Part> {

    public void visit(Part part) {
        Long partNumber = part.getPartId();
        if (partNumber == null || partNumber == 0) {
            part.getOrderHeader().addValidationError(new ValidationError("Invalid Part Number"));
        }
    }
}
