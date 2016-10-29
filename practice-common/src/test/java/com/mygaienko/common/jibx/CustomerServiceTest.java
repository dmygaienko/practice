package com.mygaienko.common.jibx;

import org.junit.Test;

/**
 * Created by enda1n on 30.10.2016.
 */
public class CustomerServiceTest {

    @Test
    public void test() throws Exception {
        new CustomerService().unmarshal();
    }

    @Test
    public void testMarshal() throws Exception {
        Customer customer = new Customer();
        customer.setCity("city");
        new CustomerService().marshal(customer);

    }
}