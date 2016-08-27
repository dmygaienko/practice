package com.mygaienko.common.model.catdog_generics;

import org.junit.Test;

/**
 * Created by dmygaenko on 31/05/2016.
 */
public class ClientTest {

    @Test
    public void testName() throws Exception {
        Client client = new Client();
        client.use(new Animal());
    }
}