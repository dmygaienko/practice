package com.mygaienko.practice.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.mygaienko.practice.Application;
import com.mygaienko.practice.model.City;
import com.mygaienko.practice.model.CityType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("local")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
@DatabaseSetup("/com/mygaienko/practice/dao/CityRepositoryTest.xml")
public class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    @ExpectedDatabase(value = "/com/mygaienko/practice/dao/CityRepositoryTest.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testFindAll() {
        cityRepository.findAll();
    }

    @Test
    public void testFindByNameAndCountryNameAllIgnoringCaseExactCase() {
        City actual = cityRepository.findByNameAndCountryNameAllIgnoringCase("Zaporizhiya", "Ukraine");
        assertEquals(5, (long) actual.getId());
    }

    @Test
    public void testFindByNameAndCountryNameAllIgnoringCaseUpperCase() {
        City actual = cityRepository.findByNameAndCountryNameAllIgnoringCase("zaporizhiya", "UKRAINE");
        assertEquals(5, (long) actual.getId());
    }

    @Test
    @ExpectedDatabase(value = "/com/mygaienko/practice/dao/CityRepositoryTest_testFindAndChangeCityType.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testFindAndChangeCityType() {
        City city = cityRepository.findByNameAndCountryNameAllIgnoringCase("zaporizhiya", "UKRAINE");
        city.setCityType(CityType.CITY_TYPE3);
        cityRepository.save(city);
    }

    @ExpectedDatabase(value = "/com/mygaienko/practice/dao/CityRepositoryTest_testDelete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @Test
    public void testDeleteVinnutsiya() {
        cityRepository.deleteById((long) 5);
    }

    @Test
    public void testSaveNewCity() {
        City city = new City();
        city.setCityType(CityType.CITY_TYPE3);
        city.setName("testName");
        city.setCountryName("testCountryName");
        cityRepository.save(city);
    }

}