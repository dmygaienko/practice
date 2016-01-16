package com.mygaienko.practice.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.mygaienko.practice.Application;
import com.mygaienko.practice.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created by dmygaenko on 15/01/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@ActiveProfiles("local")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
@DatabaseSetup("/com/mygaienko/practice/dao/ProductDaoTest.xml")
public class ProductDaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    @DatabaseSetup("/com/mygaienko/practice/dao/ProductDaoTest.xml")
    @ExpectedDatabase(value = "/com/mygaienko/practice/dao/ProductDaoTest.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testFindAll() {
        productDao.findAll();
    }

    @Test
    public void merge() {
        Product product = new Product();
        product.setId(5L);
        product.setVersion(1L);
        product.setName("Apple 1 (new)");
        product.setCode("1 new");

        productDao.merge(product);
    }

    @Test
    public void pessimisticMerge() {
        Product product = new Product();
        product.setId(5L);
        product.setVersion(1L);
        product.setName("Apple 1 (new)");
        product.setCode("1 new");

        productDao.pessimisticMerge(product);
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void refresh() {
        Product product = productDao.get(5L);
        product.setName("new name");
        productDao.refresh(product);
        assertEquals("Apple 1", product.getName());
    }

}