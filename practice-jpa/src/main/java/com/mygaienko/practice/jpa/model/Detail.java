package com.mygaienko.practice.jpa.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by enda1n on 12.06.2017.
 */
@Entity
@Table(name = "DETAIL")
@Data
public class Detail {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

}
