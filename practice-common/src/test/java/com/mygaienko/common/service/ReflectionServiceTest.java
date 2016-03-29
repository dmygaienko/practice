package com.mygaienko.common.service;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by dmygaenko on 29/03/2016.
 */
public class ReflectionServiceTest {

    private ReflectionService service;

    @Before
    public void init() {
        service = new ReflectionService();
    }


    @Test
    public void testInvokeDynamicStatic() throws Throwable {
        service.invokeDynamicStatic();
    }

    @Test
    public void testInvokeDynamicVirtual() throws Throwable {
        service.invokeDynamicVirtual(service);
    }

    @Test
    public void testInvokeDynamicGetter() throws Throwable {
        service.setStringA("string A");
        System.out.println(service.invokeDynamicGetter(service, "stringA", String.class));
    }

    @Test
    public void testInvokeDynamicSetter() throws Throwable {
        service.invokeDynamicSetter(service, "stringA", String.class, "stringB");
        System.out.println(service.getStringA());
    }

    @Test
    public void testInvokeDynamicConstructor() throws Throwable {
        System.out.println(service.invokeDynamicConstructor(ReflectionService.class, "constrA", "constrB"));
    }

}