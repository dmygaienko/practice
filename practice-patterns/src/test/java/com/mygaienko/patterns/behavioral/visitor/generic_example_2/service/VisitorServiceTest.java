package com.mygaienko.patterns.behavioral.visitor.generic_example_2.service;

import com.mygaienko.patterns.behavioral.visitor.generic_example_2.model.OrderHeader;
import org.junit.Test;


/**
 * Created by dmygaenko on 23/09/2016.
 */
public class VisitorServiceTest {

    @Test
    public void test() throws Exception {
        OrderHeader header = new OrderHeader();
        new VisitorService().validate(header);
        System.out.println(header);
    }
}