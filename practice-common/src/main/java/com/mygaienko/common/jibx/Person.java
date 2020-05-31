package com.mygaienko.common.jibx;

import java.util.List;
import java.util.Map;

/**
 * Created by enda1n on 29.10.2016.
 */
public class Person {

    private List<Address> addresses;
    private Map<String, Address> keyAddresses;

    private int customerNumber;
    private String firstName;
    private String lastName;

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Map<String, Address> getKeyAddresses() {
        return keyAddresses;
    }

    public void setKeyAddresses(Map<String, Address> keyAddresses) {
        this.keyAddresses = keyAddresses;
    }
}
