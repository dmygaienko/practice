package com.mygaienko.common.service;

import org.junit.Test;

/**
 * Created by dmygaenko on 29/03/2016.
 */
public class ReflectionServiceTest {

    @Test
    public void testInvokeDynamicStatic() throws Throwable {
        ReflectionService service = new ReflectionService();
        service.invokeDynamicStatic();
    }

    @Test
    public void testInvokeDynamicVirtual() throws Throwable {
        ReflectionService service = new ReflectionService();
        service.invokeDynamicVirtual(service);
    }

    @Test
    public void testInvokeDynamicGetter() throws Throwable {
        ReflectionService service = new ReflectionService();
        service.setStringA("string A");
        System.out.println(service.invokeDynamicGetter(service, "stringA", String.class));
    }

}