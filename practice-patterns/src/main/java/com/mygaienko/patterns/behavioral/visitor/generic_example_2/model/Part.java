package com.mygaienko.patterns.behavioral.visitor.generic_example_2.model;


import com.mygaienko.patterns.behavioral.visitor.generic_example_2.visitors.Visitor;

/**
 * Created by dmygaenko on 23/09/2016.
 */
public class Part implements Visitable<Part>{

    private Long partId;
    private String description;
    private Double price;

    private OrderDetail detail;

    public void accept(Visitor<Part> visitor) {
        visitor.visit(this);
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderDetail getDetail() {
        return detail;
    }

    public void setDetail(OrderDetail detail) {
        this.detail = detail;
    }

    public OrderHeader getOrderHeader() {
        return detail == null ? null : detail.getOrderHeader();
    }

    @Override
    public String toString() {
        return "Part{" +
                "partId=" + partId +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
