package com.mygaienko.practice.jpa.model.inheritance.singletable;

import javax.persistence.*;

/**
 * Created by enda1n on 25.06.2017.
 */
@Table(name = "single_table")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
//@DiscriminatorFormula("case when DTYPE == 'ETYPE' then 'ETYPE' else 'FTYPE'")
public abstract class SingleSuperClass {

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
        return "SingleSuperClass{" +
                "id=" + id +
                ", s1='" + s1 + '\'' +
                '}';
    }
}
