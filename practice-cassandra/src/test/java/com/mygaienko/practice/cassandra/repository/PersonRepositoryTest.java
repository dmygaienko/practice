package com.mygaienko.practice.cassandra.repository;

import com.mygaienko.practice.cassandra.Application;
import com.mygaienko.practice.cassandra.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by enda1n on 24.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    private Person person;

    @Before
    public void setUp() {

        person = new Person();
        person.setId(42L);
        person.setFirstName("firstname");
        person.setLastName("lastname");
    }

    @Test
    public void findSavedUserById() {

        person = repository.save(person);

        assertThat(repository.findOne(person.getId()), is(person));
    }

}