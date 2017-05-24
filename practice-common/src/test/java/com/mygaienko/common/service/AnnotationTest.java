package com.mygaienko.common.service;

import com.mygaienko.common.annotations.MyAttributeAnnotation;
import com.mygaienko.common.annotations.MyBeanAnnotation;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;

/**
 * Created by dmygaenko on 24/05/2017.
 */
public class AnnotationTest {

    @Test
    public void testAnnotations() throws Exception {
        MyBeanAnnotation annotation = AnnotationUtils.findAnnotation(B.class, MyBeanAnnotation.class);
    }

    @Test
    public void testDeclaringAnnotations() throws Exception {
        Field[] annotatedFields = FieldUtils.getFieldsWithAnnotation(B.class, MyAttributeAnnotation.class);
        for (Field annotatedField : annotatedFields) {
            MyAttributeAnnotation annotation = annotatedField.getAnnotation(MyAttributeAnnotation.class);
            System.out.println(annotation.name());
        }
    }

}


class A {

    @MyAttributeAnnotation(name = "a1Annotation")
    private String a1;

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

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }
}
