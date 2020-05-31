package com.mygaienko.common.class_loader;

public class TestClass {

    static {
        System.out.println("Hello world from the loaded class static block !!!");
    }

    public void sayHello() {
        System.out.println("Hello world from the loaded class method !!!");
    }

}