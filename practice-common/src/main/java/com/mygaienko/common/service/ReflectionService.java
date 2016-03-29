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

    public ReflectionService(){}

    public ReflectionService(String stringA, String stringB) {
        this.stringA = stringA;
        this.stringB = stringB;
    }

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

    public <T> void invokeDynamicSetter(ReflectionService service, String name, Class<T> type, T value) throws Throwable {

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle helloMethod = lookup.findSetter(ReflectionService.class, name, type);
        helloMethod.invokeWithArguments(service, value);
    }

    public <T> T invokeDynamicConstructor(Class<T> clazz, String... args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle constructor = lookup.findConstructor(clazz, MethodType.methodType(void.class, String.class, String.class));
        return (T) constructor.invokeWithArguments(args);
    }

    public void virtual() throws Throwable {
        System.out.println("virtual");
    }

    static void hello() {
        System.out.println("hello");
    }

    public String getStringA() {
        System.out.println("getStringA invoked ");
        return stringA;
    }

    public void setStringA(String stringA) {
        System.out.println("setStringA invoked ");
        this.stringA = stringA;
    }

    public String getStringB() {
        return stringB;
    }

    public void setStringB(String stringB) {
        this.stringB = stringB;
    }

    @Override
    public String toString() {
        return "ReflectionService{" +
                "stringA='" + stringA + '\'' +
                ", stringB='" + stringB + '\'' +
                '}';
    }
}
