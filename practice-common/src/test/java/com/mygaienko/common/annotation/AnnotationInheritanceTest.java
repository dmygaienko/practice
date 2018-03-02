package com.mygaienko.common.annotation;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AnnotationInheritanceTest {

    @Test
    public void testInheritedAnnotationOnMethod() {
        List<Method> methodsListWithAnnotation = MethodUtils.getMethodsListWithAnnotation(C.class, InheritedMethodAnnotation.class);
        assertTrue(
                methodsListWithAnnotation.stream()
                        .map(method -> method.getName())
                        .noneMatch(name -> name.equalsIgnoreCase("method"))
        );
    }

    @Test
    public void testInheritedAnnotationOnInterface() {
        assertNull(C.class.getAnnotation(InheritedTypeAnnotation.class));
    }

    @Test
    public void testInheritedAnnotationOnClass() {
        assertNotNull(E.class.getAnnotation(InheritedTypeAnnotation.class));
    }
}



@InheritedTypeAnnotation
interface B {

    @InheritedMethodAnnotation
    void method();
}

interface C extends B{

    void method();

}

@InheritedTypeAnnotation
abstract class D implements C {

}

abstract class E extends D {

}

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface InheritedMethodAnnotation {}


@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface InheritedTypeAnnotation {}


