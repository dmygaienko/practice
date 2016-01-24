package com.mygaienko.practice.dao.graph;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.mygaienko.practice.Application;
import com.mygaienko.practice.model.graph.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by enda1n on 23.01.2016.
 */
@WebAppConfiguration
@ActiveProfiles(value = "test")
@Transactional()
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