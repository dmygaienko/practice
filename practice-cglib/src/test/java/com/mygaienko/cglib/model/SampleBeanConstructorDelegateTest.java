package com.mygaienko.cglib.model;

import org.junit.Test;
import org.mockito.cglib.reflect.ConstructorDelegate;

import static org.junit.Assert.*;

/**
 * Created by dmygaenko on 28/01/2016.
 */
public class SampleBeanConstructorDelegateTest {

    @Test
    public void testConstructorDelegate() throws Exception {
        SampleBeanConstructorDelegate constructorDelegate = (SampleBeanConstructorDelegate) ConstructorDelegate.create(
                SampleBean.class, SampleBeanConstructorDelegate.class);

        SampleBean bean = (SampleBean) constructorDelegate.newInstance();

        assertTrue(SampleBean.class.isAssignableFrom(bean.getClass()));
    }

}