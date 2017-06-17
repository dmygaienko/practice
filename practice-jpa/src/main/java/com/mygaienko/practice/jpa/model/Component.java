package com.mygaienko.practice.jpa.model;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Created by enda1n on 17.06.2017.
 */
@Embeddable
@Data
public class Component {

    private String name;

}
