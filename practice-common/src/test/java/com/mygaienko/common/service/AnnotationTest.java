package com.mygaienko.common.service;

import com.mygaienko.common.annotations.MyAttributeAnnotation;
import com.mygaienko.common.annotations.MyBeanAnnotation;
import com.mygaienko.common.annotations.MyMethodAnnotation;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertThat;

/**
 * Created by dmygaenko on 24/05/2017.
 */
public class AnnotationTest {

    @Test
    public void testAnnotations() throws Exception {
        MyBeanAnnotation annotation = AnnotationUtils.findAnnotation(B.class, MyBeanAnnotation.class);
        assertThat(annotation.customName(), is("beanCustomName"));
    }

    @Test
    public void testGetFieldsWithAnnotation() throws Exception {
        Field[] annotatedFields = FieldUtils.getFieldsWithAnnotation(B.class, MyAttributeAnnotation.class);
        for (Field annotatedField : annotatedFields) {
            MyAttributeAnnotation annotation = annotatedField.getAnnotation(MyAttributeAnnotation.class);
            assertThat(annotation.name(), isIn(asList("b1Annotation", "a1Annotation")));
        }
    }

    @Test
    public void testGetMethodsListWithAnnotation() throws Exception {
        MethodUtils.getMethodsListWithAnnotation(B.class, MyMethodAnnotation.class)
                .stream()
                .map(field -> field.getAnnotation(MyMethodAnnotation.class))
                .forEach(annotation -> assertThat(annotation.methodName(), isIn(asList("methodA1Annotation", "methodB1Annotation"))));
    }

}


class A {

    @MyAttributeAnnotation(name = "a1Annotation")
    private String a1;

    @MyMethodAnnotation(methodName = "methodA1Annotation")
    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }
}

@MyBeanAnnotation(customName = "beanCustomName", years = 1, customNames = {"customNames1", "customNames2"})
class B extends A {

    @MyAttributeAnnotation(name = "b1Annotation")
    private String b1;

    @MyMethodAnnotation(methodName = "methodB1Annotation")
    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }
}
