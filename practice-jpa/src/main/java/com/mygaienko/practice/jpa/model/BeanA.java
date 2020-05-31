package com.mygaienko.practice.jpa.model;

import javax.persistence.*;

/**
 * Created by mygadmy on 03/12/15.
 */
@Entity(name = "bean_a")
public class BeanA {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "name", referencedColumnName = "code", insertable=false, updatable=false)
    private BeanB beanB;

    public BeanA(){}

    public BeanA(long id) {
        this.id = id;
    }

    public BeanA(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BeanB getBeanB() {
        return beanB;
    }

    public void setBeanB(BeanB beanB) {
        this.beanB = beanB;
    }
}
