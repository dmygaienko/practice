package com.mygaienko.common.jibx;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IUnmarshallingContext;
import org.junit.Test;

import java.io.FileInputStream;


/**
 * Created by enda1n on 29.10.2016.
 */
public class CustomerTest {

    @Test
    public void test() throws Exception {
        IBindingFactory bfact = BindingDirectory.getFactory(Customer.class);
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        Object obj = uctx.unmarshalDocument(new FileInputStream("jibx/customer.xml"), null);

    }
}