package com.mygaienko.practice.model;

import javax.persistence.*;
import java.util.Date;
import java.util.logging.StreamHandler;

/**
 * Created by enda1n on 05.12.2015.
 */
@Entity
@Table(name = "author")
@SecondaryTable(name = "author_address",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "author_id"))
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Temporal(value = TemporalType.DATE)
    private Date dateOfBirth;

    @Column(table = "author_address")
    private String street;

    @Column(table = "author_address")
    private Long number;

    @Column(table = "author_address")
    private Long flat;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getStreet() {
        return street;
    }

    public Long getNumber() {
        return number;
    }

    public Long getFlat() {
        return flat;
    }
}

