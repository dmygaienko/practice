package com.mygaienko.common.jibx;

import org.jibx.runtime.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by enda1n on 30.10.2016.
 */
public class CustomerService {

    public Object unmarshal() throws JiBXException, FileNotFoundException {
        IBindingFactory bfact = BindingDirectory.getFactory(Customer.class);
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        return uctx.unmarshalDocument(new FileInputStream("src/main/resource/jibx/customer.xml"), null);
      //  return uctx.unmarshalDocument(new FileInputStream("C:\\dev\\workspaces\\java\\practice\\practice-common\\src\\main\\resource\\jibx\\customer.xml"), null);
    }

    public void marshal(Customer customer) throws JiBXException, FileNotFoundException {
        IBindingFactory bfact = BindingDirectory.getFactory(Customer.class);
        IMarshallingContext mctx = bfact.createMarshallingContext();
        mctx.marshalDocument(customer, "UTF-8", null,
                new FileOutputStream("src/main/resource/jibx/customer1.xml"));
    }
}
