package com.mygaienko.practice.jpa.model.inheritance.joined;

import javax.persistence.*;

/**
 * Created by enda1n on 25.06.2017.
 */
@Table(name = "S")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class JoinedSuperClass {

    @Id
    private Long id;

    private String s1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    @Override
    public String toString() {
        return "JoinedSuperClass{" +
                "id=" + id +
                ", s1='" + s1 + '\'' +
                '}';
    }
}
