package com.mygaienko.practice.jpa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by liuda on 12/6/2015.
 */
@Embeddable
public class ZipCode implements Serializable {

    @Column
    private String code1;

    @Column
    private String code2;

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }
}
