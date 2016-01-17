package com.mygaienko.practice.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mygaienko.practice.Application;
import com.mygaienko.practice.dao.interfaces.AuthorDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * Created by enda1n on 16.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
@DatabaseSetup("/com/mygaienko/practice/dao/AuthorDaoImplTest.xml")
public class AuthorDaoImplTest {

    @Autowired
    private AuthorDao dao;

    @Test
    public void testGet() {
        dao.get(1L);
    }

    @Test
    public void testGetByBeanANameAndBeanBCode() {
        dao.getByBeanANameAndBeanBCode("Author1", "beanAName1", "beanBName1");
    }

    @Test
    public void getByNameLike() {
        dao.getByNameLike("Author");
    }

}
