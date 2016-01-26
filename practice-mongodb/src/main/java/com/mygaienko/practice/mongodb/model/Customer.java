package com.mygaienko.practice.mongodb.model;

import org.springframework.data.annotation.Id;

/**
 * Created by enda1n on 26.01.2016.
 */
public class Customer {

    @Id
    private String id;

    private String firstName;
    private String lastName;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
