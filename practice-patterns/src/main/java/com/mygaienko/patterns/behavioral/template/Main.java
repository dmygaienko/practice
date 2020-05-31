package com.mygaienko.patterns.behavioral.template;

/**
 * Created by dmygaenko on 23/03/2016.
 */
public class Main {


    public interface Interface {
        void test(Class<Object> clazz);
    }


    public abstract class BaseClass<T extends Object> implements Interface {

        abstract public void test(Class<Object> clazz);
    }


    public class MyClass extends BaseClass<MyClass> {

        @Override
        public void test(Class<Object> clazz) {
        }
    }


}
