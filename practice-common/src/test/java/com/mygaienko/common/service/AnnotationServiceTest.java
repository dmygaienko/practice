package com.mygaienko.common.service;

import com.mygaienko.common.annotations.MyBeanAnnotation;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created by dmygaenko on 27/01/2016.
 */
public class AnnotationServiceTest {

    private AnnotationService annotationService = new AnnotationService();

    @Test
    public void test() {
        Set<Class<?>> annotatedClasses = annotationService.searchMyBeanAnnotatedClasses();
        for (Class<?> annotatedClass : annotatedClasses) {
            Annotation[] annotations = annotatedClass.getAnnotations();
            Annotation annotation = annotations[0];
            if (annotation instanceof MyBeanAnnotation) {
                System.out.println(((MyBeanAnnotation) annotation).customName());
                System.out.println(((MyBeanAnnotation) annotation).customNames());
                System.out.println(((MyBeanAnnotation) annotation).years());
            }
        }
    }
}