package com.mygaienko.common.class_loader;

import com.mygaienko.common.stream.StreamUtil;
import org.junit.Test;
import org.springframework.instrument.classloading.ShadowingClassLoader;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

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

    @Test
    public void testDynamicClassLoad() throws Exception {
        Class<StreamUtil> aClass = StreamUtil.class;

        ShadowingClassLoader shadowingClassLoader = new ShadowingClassLoader(aClass.getClassLoader());
        Class<?> bClass = shadowingClassLoader.loadClass(aClass.getCanonicalName());

        System.out.println(aClass.getClassLoader());
        System.out.println(bClass.getClassLoader());

        assertThat(aClass, not(sameInstance(bClass)));
    }
}
