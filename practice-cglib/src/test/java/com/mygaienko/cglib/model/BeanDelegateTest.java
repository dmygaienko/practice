package com.mygaienko.cglib.model;

import net.sf.cglib.reflect.MethodDelegate;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dmygaenko on 28/01/2016.
 */
public class BeanDelegateTest {

    @Test
    public void testMethodDelegate() throws Exception {
        SampleBean bean = new SampleBean();
        bean.setValue("Hello cglib!");

        BeanDelegate delegate = (BeanDelegate) MethodDelegate.create(bean, "getValue", BeanDelegate.class);

        assertEquals("Hello cglib!", delegate.getValueFromDelegate());
    }

}