package com.mygaienko.patterns.behavioral.visitor.generic_example_2.model;


/**
 * Created by dmygaenko on 23/09/2016.
 */
public class OrderDetail implements Visitable<OrderDetail>{

    private OrderHeader orderHeader;

    private Long detailId;
    private Part part;
    private int quantity;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                ", detailId=" + detailId +
                ", part=" + part +
                ", quantity=" + quantity +
                '}';
    }
}
