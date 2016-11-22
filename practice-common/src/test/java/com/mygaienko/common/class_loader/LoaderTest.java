package com.mygaienko.common.class_loader;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by dmygaenko on 22/11/2016.
 */
public class LoaderTest {

    @Test
    public void testHello() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassLoader classLoader = this.getClass().getClassLoader();

        Class<?> loadClass = classLoader.loadClass("com.mygaienko.common.class_loader.TestClass");
        loadClass.getConstructor().newInstance();
    }

}
