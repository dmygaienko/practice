package com.mygaienko.practice.jpa.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by enda1n on 17.06.2017.
 */
@Embeddable
@Data
@NoArgsConstructor
public class Component {

    private String name;

    public Component(String name) {
        this.name = name;
    }
}
