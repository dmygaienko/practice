package com.mygaienko.patterns.behavioral.visitor.generic_example_2.model;

/**
 * Created by dmygaenko on 23/09/2016.
 */
public class ValidationError {

    private String errorDescription;

    public ValidationError(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    @Override
    public String toString() {
        return "ValidationError{" +
                "errorDescription='" + errorDescription + '\'' +
                '}';
    }
}
