package com.mygaienko.patterns.structural.filter;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * Created by dmygaenko on 13/10/2016.
 */
public class FilterServiceTest {

    @Test
    public void testFilters() throws Exception {
        FilterService<Person> personFilterService = new FilterService<>();

        personFilterService.addFilter(new PersonMaleFilter());
        personFilterService.addFilter(new PersonMaritalStatusFilter());

        List<Person> persons = Arrays.asList(
            new Person("Ivan", Person.Gender.M, Person.MaritalStatus.M),
            new Person("Taras", Person.Gender.M, Person.MaritalStatus.M),
            new Person("Opanas", Person.Gender.M, Person.MaritalStatus.D),
            new Person("Teodor", Person.Gender.M, Person.MaritalStatus.S)
        );

        List<Person> filtrated = personFilterService.filtrate(persons);
        System.out.println(filtrated);
        assertThat(filtrated.size(), is(2));

        assertThat(filtrated.get(0).getName(), is("Ivan"));
        assertThat(filtrated.get(0).getGender(), is(Person.Gender.M));
        assertThat(filtrated.get(0).getMaritalStatus(), is(Person.MaritalStatus.M));

        assertThat(filtrated.get(1).getName(), is("Taras"));
        assertThat(filtrated.get(1).getGender(), is(Person.Gender.M));
        assertThat(filtrated.get(1).getMaritalStatus(), is(Person.MaritalStatus.M));

    }
}