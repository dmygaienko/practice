package com.mygaienko.common.camel;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.rx.ReactiveCamel;
import org.junit.Test;

/**
 * Created by enda1n on 21.05.2017.
 */
public class CamelTest {

    @Test
    public void testReactiveCamel() throws Exception {
        final DefaultCamelContext camelContext = new DefaultCamelContext();

        new ReactiveCamel(camelContext)
                .toObservable("file:C:\\Users\\enda1n\\Downloads")
                .toBlocking()
                .subscribe(str -> System.out.println(str));
    }
}
