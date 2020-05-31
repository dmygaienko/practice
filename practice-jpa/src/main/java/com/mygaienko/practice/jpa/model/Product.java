package com.mygaienko.practice.jpa.model;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by dmygaenko on 15/01/2016.
 */
@Entity
@Table(name = "PRODUCT")
@DynamicUpdate
@Data
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Column(unique = true)
    private String name;

    @Column
    private String code;

    @Formula("NAME || ' | ' || CODE")
    private String shortInfo;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "product")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Detail> details = new ArrayList<>();

    @OrderBy("name")
    @Fetch(FetchMode.SUBSELECT)
    @ElementCollection
    @CollectionTable(name = "COMPONENT",joinColumns = {
            @JoinColumn(name = "PRODUCT_ID")
    })
    private Set<Component> components;

}
