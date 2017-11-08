package com.mygaienko.patterns.behavioral.observer;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by enda1n on 08.11.2017.
 */
public class ServiceTest {
    @Test
    public void execute() throws Exception {
        Context context = new Context();
        Service service = new Service(Arrays.asList(new AObserver(), new BObserver()));
        service.execute(context);

        assertTrue(context.contains("A"));
        assertTrue(context.contains("B"));
    }

}