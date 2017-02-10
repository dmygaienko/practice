package com.mygaienko.practice.jpa.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mygaienko.practice.jpa.Application;
import com.mygaienko.practice.jpa.dao.interfaces.CountryDao;
import com.mygaienko.practice.jpa.model.City;
import com.mygaienko.practice.jpa.model.Country;
import org.apache.xmlbeans.impl.piccolo.xml.EntityManager;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by dmygaenko on 07/02/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@ActiveProfiles("local")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
@DatabaseSetup("/com/mygaienko/practice/jpa/dao/CityRepositoryTest.xml")
@Transactional(transactionManager = "jpaTransactionManager")
public class CountryDaoImplTest {

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void testDelete() throws Exception {
        List<Country> countries = countryDao.getCountries();
        countryDao.remove(countries.get(0));
        countryDao.getCountries();
    }

    @Test
    public void testRemoveWithOrphanRemoval() throws Exception {
        List<Country> countries = countryDao.getCountries();
        Country country = countries.get(0);
        country.getCities();
        country.getCities().get(0);
        //countryDao.persist(country);
        //List<City> all = cityRepository.findAll();
    }

}