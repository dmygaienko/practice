package com.mygaienko.practice.model;

import javax.persistence.*;

/**
 * Created by dmygaenko on 15/01/2016.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Column
    private String name;

    @Column
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
