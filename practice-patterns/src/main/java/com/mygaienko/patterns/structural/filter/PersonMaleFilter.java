package com.mygaienko.patterns.structural.filter;

import java.util.ArrayList;
import java.util.List;

import static com.mygaienko.patterns.structural.filter.Person.MaritalStatus.M;

/**
 * Created by dmygaenko on 13/10/2016.
 */
public class PersonMaleFilter implements Filter<List<Person>> {

    @Override
    public List<Person> filtrate(List<Person> persons) {
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (M.equals(person.getMaritalStatus())) {
                result.add(person);
            }
        }
        return result;
    }
}
