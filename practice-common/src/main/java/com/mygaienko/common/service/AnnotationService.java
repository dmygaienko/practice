package com.mygaienko.common.service;

import com.mygaienko.common.annotations.MyBeanAnnotation;
import org.reflections.Reflections;

import java.util.Set;

/**
 * Created by dmygaenko on 27/01/2016.
 */
public class AnnotationService {

    public Set<Class<?>> searchMyBeanAnnotatedClasses() {
        Reflections reflections = new Reflections("com.mygaienko.common");
        return reflections.getTypesAnnotatedWith(MyBeanAnnotation.class);
    }
}
