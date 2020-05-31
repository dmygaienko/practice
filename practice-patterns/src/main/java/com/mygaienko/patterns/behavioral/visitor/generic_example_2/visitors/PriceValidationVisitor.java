package com.mygaienko.patterns.behavioral.visitor.generic_example_2.visitors;

import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.Part;
import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.ValidationError;

/**
 * Created by dmygaenko on 23/09/2016.
 */
public class PriceValidationVisitor implements Visitor<Part>{

    public void visit(Part part) {
        Double price = part.getPrice();
        if (price == null || price == 0) {
            part.getOrderHeader().addValidationError(
                    new ValidationError("Price for part: " + part.getPartId() + " is invalid"));
        }
    }
}
