package com.mygaienko.patterns.structural.filter;

/**
 * Created by dmygaenko on 13/10/2016.
 */
public class Person {
    private String name;
    private Gender gender;
    private MaritalStatus maritalStatus;

    public Person(String name, Gender gender, MaritalStatus maritalStatus) {
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public enum MaritalStatus {
        M, S, D, W
    }

    public enum Gender {
        M, F
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", maritalStatus=" + maritalStatus +
                '}';
    }
}