package com.mygaienko.common.service;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Created by dmygaenko on 29/03/2016.
 */
public class ReflectionService {

    private String stringA;
    private String stringB;

    public void invokeDynamicStatic() throws Throwable {

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle helloMethod = lookup.findStatic(ReflectionService.class, "hello", MethodType.methodType(void.class));

        helloMethod.invokeExact();
    }

    public void invokeDynamicVirtual(ReflectionService service) throws Throwable {

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle helloMethod = lookup.findVirtual(ReflectionService.class, "virtual", MethodType.methodType(void.class));
        helloMethod.invoke(service);
    }

    public <T> T invokeDynamicGetter(ReflectionService service, String name, Class<T> type) throws Throwable {

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle helloMethod = lookup.findGetter(ReflectionService.class, name, type);
        return (T) helloMethod.invoke(service);
    }

    public void virtual() throws Throwable {
        System.out.println("virtual");
    }

    static void hello() {
        System.out.println("hello");
    }

    public String getStringA() {
        return stringA;
    }

    public void setStringA(String stringA) {
        this.stringA = stringA;
    }

    public String getStringB() {
        return stringB;
    }

    public void setStringB(String stringB) {
        this.stringB = stringB;
    }
}
