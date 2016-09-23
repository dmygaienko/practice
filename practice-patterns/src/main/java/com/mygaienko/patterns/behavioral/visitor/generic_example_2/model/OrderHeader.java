package com.mygaienko.patterns.behavioral.visitor.generic_example_2.model;


import com.mygaienko.patterns.behavioral.visitor.generic_example_2.visitors.Visitor;
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dmygaenko on 23/09/2016.
 */
public class OrderHeader implements Visitable<OrderHeader>{

    private Long orderId;
    private Long customerId;

    private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
    private List<ValidationError> errors = new ArrayList<ValidationError>();

    @Override
    public void accept(Visitor<OrderHeader> visitor) {
        visitor.visit(this);
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }

    public List<ValidationError> getValidationErrors() {
        return errors;
    }

    public void addValidationError(ValidationError validationError) {
        errors.add(validationError);
    }

    @Override
    public String toString() {
        return "OrderHeader{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", orderDetails=" + orderDetails +
                ", errors=" + errors +
                '}';
    }
}
