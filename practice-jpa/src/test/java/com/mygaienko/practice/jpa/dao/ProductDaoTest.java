package com.mygaienko.practice.jpa.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.mygaienko.practice.jpa.model.Component;
import com.mygaienko.practice.jpa.model.Detail;
import com.mygaienko.practice.jpa.model.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by dmygaenko on 15/01/2016.
 */
@DatabaseSetup("/com/mygaienko/practice/jpa/dao/ProductDaoTest.xml")
public class ProductDaoTest extends AbstractDaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    @DatabaseSetup("/com/mygaienko/practice/jpa/dao/ProductDaoTest.xml")
    @ExpectedDatabase(value = "/com/mygaienko/practice/jpa/dao/ProductDaoTest.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testFindAll() {
        List<Product> all = productDao.getByIds(Arrays.asList("5", "6"));
        System.out.println("==============");
        List<Detail> details = all.get(0).getDetails();
        assertThat(all.get(0).getComponents(), contains(
                new Component("Apple 1 component 1"),
                new Component("Apple 1 component 2"),
                new Component("Apple 1 component 3")));
        System.out.println("==============");
    }

    @Test
    public void merge() {
        Product product = new Product();
        product.setId(5L);
        product.setVersion(0L);
        product.setName("Apple 1 (new)");
        product.setCode("1 new");

        Product actual = productDao.merge(product);
        assertEquals(Long.valueOf(0), actual.getVersion());
        assertEquals("Apple 1 (new)", actual.getName());
    }

    @Test(expected = javax.persistence.OptimisticLockException.class)
    public void mergeWithWrongVersion() {
        Product product = new Product();
        product.setId(5L);
        product.setVersion(1L);
        product.setName("Apple 1 (new)");
        product.setCode("1 new");

        productDao.merge(product);
    }

    @Test(expected = javax.persistence.PersistenceException.class)
    public void removeAndPersistThrowsException() {
        Product product = new Product();
        product.setId(5L);
        product.setVersion(0L);
        product.setName("Apple 1");
        product.setCode("1 new");

        productDao.remove(product);

        product.setId(null);

        productDao.persist(product);
    }

    @Test
    public void removeAndPersistWithFlush() {
        Product product = new Product();
        product.setId(5L);
        product.setVersion(0L);
        product.setName("Apple 1");
        product.setCode("1");

        productDao.remove(product);
        productDao.flush();

        product.setId(null);

        productDao.persist(product);
        productDao.flush();
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
    public void dynamicUpdate() {
        Product product = productDao.get(5L);
        product.setName("updated name3");

        Product actual = productDao.merge(product);
        assertEquals(Long.valueOf(0), actual.getVersion());
        assertEquals("updated name3", actual.getName());
    }

    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW, transactionManager = "jpaTransactionManager")
    public void refresh() {
        Product product = productDao.get(5L);
        product.setName("new name");
        productDao.refresh(product);
        assertEquals("Apple 1", product.getName());
    }

}