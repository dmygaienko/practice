package com.mygaienko.common.service;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

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

    @Test
    public void testIsEnum() {
        assertTrue(ExampleEnum.class.isEnum());
    }

    @Test
    public void testGetEnumConstants() {
        assertTrue(ArrayUtils.isEquals(
                new ExampleEnum[]{ExampleEnum.ONE, ExampleEnum.TWO, ExampleEnum.THREE, ExampleEnum.FOUR},
                ExampleEnum.class.getEnumConstants()));
    }

    enum ExampleEnum {
        ONE, TWO, THREE, FOUR
    }

    @Test
    public void testEnumGetDeclaredFields() {
        Field[] declaredFields = ExampleEnum.class.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field.getName() + " " + field.isEnumConstant());
        }
    }

    @Test
    public void testIsPrimitive() {
        assertTrue(int.class.isPrimitive());
    }

    @Test
    public void testIsPrimitiveOnClass() throws NoSuchFieldException {
        System.out.println(TestClass.class.getDeclaredField("i").getModifiers());
    }

    @Test
    public void testGetDeclaredMethods() throws NoSuchFieldException {
        System.out.println(TestClass.class.getDeclaredMethods());
    }

    @Test
    public void testGetDeclaredMethod() throws NoSuchFieldException, NoSuchMethodException {
        Method testMethod = TestClass.class.getDeclaredMethod("testMethod");
        System.out.println("Name: " + testMethod.getName());
        System.out.println("AnnotatedReturnType: " + testMethod.getAnnotatedReturnType().getType());
        System.out.println("DeclaringClass: " + testMethod.getDeclaringClass());
    }

    @Test
    public void testGetDeclaredMethodWithType() throws NoSuchFieldException, NoSuchMethodException {
        Method testMethod = TestClass.class.getDeclaredMethod("testMethodWithString", String.class);
        System.out.println("Name: " + testMethod.getName());
        System.out.println("AnnotatedReturnType: " + testMethod.getAnnotatedReturnType().getType());
        System.out.println("DeclaringClass: " + testMethod.getDeclaringClass());
    }

    @Test
    public void testGetDeclaredConstructors() throws NoSuchFieldException, NoSuchMethodException {
        System.out.println(TestClass.class.getDeclaredConstructors());
    }

    @Test
    public void testGetDeclaredConstructor() throws NoSuchFieldException, NoSuchMethodException {
        Constructor<TestClass> constructor = TestClass.class.getDeclaredConstructor(ReflectionServiceTest.class, int.class, String.class);
        System.out.println(constructor);
        System.out.println("Is synthetic: " + constructor.getParameters()[0].isSynthetic());
    }

    @Test
    public void testGetDeclaredClasses() {
        System.out.println(ReflectionServiceTest.class.getDeclaredClasses());
    }

    @Test
    public void testGetGenericReturnType() throws NoSuchMethodException {
        Method method = (new ArrayList<String>()).getClass().getDeclaredMethod("get", int.class);
        System.out.println("getGenericExceptionTypes: " + method.getGenericExceptionTypes());
        System.out.println("getGenericReturnType: " +  method.getGenericReturnType());
        System.out.println("getGenericParameterTypes: " + method.getGenericParameterTypes());
    }

    @Test
    public void testClassLoader() throws NoSuchMethodException, ClassNotFoundException {
        ClassLoader.getSystemClassLoader().loadClass("com.mygaienko.common.service.ReflectionServiceTest");
    }


    static class StaticTestClass {

        public Object getLocalClass() {
            return new Object() {
              public String ololo;
            };
        }
    }

    class TestClass {

        private int i;
        private String testString;

        public TestClass() {
        }

        public TestClass(int i) {
            this.i = i;
        }

        public TestClass(String testString) {
            this.testString = testString;
        }

        public TestClass(int i, String testString) {
            this.i = i;
            this.testString = testString;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public void testMethod() {}

        public void testMethodWithString(String testString) {}
    }

}