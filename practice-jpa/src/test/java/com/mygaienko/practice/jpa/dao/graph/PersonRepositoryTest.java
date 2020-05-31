package com.mygaienko.practice.jpa.dao.graph;

import com.mygaienko.practice.jpa.Application;
import com.mygaienko.practice.jpa.model.graph.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by enda1n on 23.01.2016.
 */
@WebAppConfiguration
@ActiveProfiles(value = "test")
@Transactional(transactionManager = "myNeo4jTransactionManager")
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { Application.class })
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @Test
    public void graphtTest() {
        repository.save(new Person("1"));
        repository.findByName("3");
        repository.findAll();
    }
}