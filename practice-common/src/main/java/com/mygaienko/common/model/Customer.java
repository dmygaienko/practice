package com.mygaienko.common.model;

import com.mygaienko.common.annotations.MyBeanAnnotation;
import com.mygaienko.common.annotations.MyMethodAnnotation;

/**
 * Created by dmygaenko on 27/01/2016.
 */
@MyBeanAnnotation(years = 4, customNames = {"custom name1", "custom name2"})
public class Customer {

    private Long id;
    private String name;

    @MyMethodAnnotation(methodName = "method name1")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
