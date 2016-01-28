package com.mygaienko.cglib.model;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by dmygaenko on 28/01/2016.
 */
public class SampleBeanDynamicTest {

    @Test
    public void testSampleClass() {
        final SampleBean delegate = new SampleBean();

        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Hello from proxy");
                return method.invoke(delegate, args);
            }
        };

        Object proxyInstance = Proxy.newProxyInstance(SampleBean.class.getClassLoader(),
                new Class[]{SampleInterface.class}, invocationHandler);

        System.out.println(((SampleInterface) proxyInstance).test(""));
    }
}
