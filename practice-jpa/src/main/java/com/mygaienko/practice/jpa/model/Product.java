package com.mygaienko.practice.jpa.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
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

    @Column
    private String name;

    @Column
    private String code;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "product")
    private List<Detail> details;

    @Fetch(FetchMode.SUBSELECT)
    @ElementCollection
    @CollectionTable(name = "COMPONENT",joinColumns = {
            @JoinColumn(name = "PRODUCT_ID")
    })
    private Set<Component> components;

}
