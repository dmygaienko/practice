package com.mygaienko.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by enda1n on 16.01.2016.
 */
@Entity
public class BeanB {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
