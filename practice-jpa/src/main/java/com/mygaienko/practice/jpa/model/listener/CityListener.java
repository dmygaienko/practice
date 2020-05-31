package com.mygaienko.practice.jpa.model.listener;

import com.mygaienko.practice.jpa.model.City;

import javax.persistence.*;

/**
 * Created by dmygaenko on 09/12/2015.
 */
public class CityListener {

    @PrePersist
    public void prePersist(City city) {
        System.out.println("PrePersist in City: id - " + city.getId() + ", name - " + city.getName());
    }

    @PostPersist
    public void postPersist(City city) {
        System.out.println("PostPersist in City: id - " + city.getId() + ", name - " + city.getName());
    }

    @PreUpdate
    public void preUpdate(City city) {
        System.out.println("PreUpdate in City: id - " + city.getId() + ", name - " + city.getName());
    }

    @PostUpdate
    public void postUpdate(City city) {
        System.out.println("PostUpdate in City: id - " + city.getId() + ", name - " + city.getName());
    }

    @PostLoad
    public void postLoad(City city) {
        System.out.println("PostLoad in City: id - " + city.getId() + ", name - " + city.getName());
    }

    @PreRemove
    public void preRemove(City city) {
        System.out.println("PreRemove in City: id - " + city.getId() + ", name - " + city.getName());
    }

    @PostRemove
    public void postRemove(City city) {
        System.out.println("PostRemove in City: id - " + city.getId() + ", name - " + city.getName());
    }

}
