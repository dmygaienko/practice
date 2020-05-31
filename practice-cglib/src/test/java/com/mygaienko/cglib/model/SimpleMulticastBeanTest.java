package com.mygaienko.cglib.model;

import org.junit.Test;
import org.mockito.cglib.reflect.MulticastDelegate;

import static org.junit.Assert.*;

/**
 * Created by dmygaenko on 28/01/2016.
 */
public class SimpleMulticastBeanTest {

    @Test
    public void testMulticastDelegate() throws Exception {
        MulticastDelegate multicastDelegate = MulticastDelegate.create(DelegationProvider.class);

        SimpleMulticastBean first = new SimpleMulticastBean();
        SimpleMulticastBean second = new SimpleMulticastBean();

        multicastDelegate = multicastDelegate.add(first);
        multicastDelegate = multicastDelegate.add(second);

        DelegationProvider provider = (DelegationProvider) multicastDelegate;
        provider.setValue("Hello world!");

        assertEquals("Hello world!", first.getValue());
        assertEquals("Hello world!", second.getValue());
    }

}