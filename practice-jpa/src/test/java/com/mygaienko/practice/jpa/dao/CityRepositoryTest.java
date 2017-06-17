package com.mygaienko.practice.jpa.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.mygaienko.practice.jpa.model.City;
import com.mygaienko.practice.jpa.model.CityType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;


@DatabaseSetup("/com/mygaienko/practice/jpa/dao/CityRepositoryTest.xml")
public class CityRepositoryTest extends AbstractDaoTest {

    @Autowired
    private  CityRepository cityRepository;

    @Test
    @DatabaseSetup("/com/mygaienko/practice/jpa/dao/CityRepositoryTest.xml")
    @ExpectedDatabase(value = "/com/mygaienko/practice/jpa/dao/CityRepositoryTest.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testFindAll() {
        cityRepository.findAll();
    }

    @Test
    @DatabaseSetup("/com/mygaienko/practice/jpa/dao/CityRepositoryTest.xml")
    public void testFindByNameAndCountryNameAllIgnoringCaseExactCase() {
        City actual = cityRepository.findByNameAndCountryNameAllIgnoringCase("Zaporizhiya", "Ukraine");
        assertEquals(5, (long) actual.getId());
    }

    @Test
    @DatabaseSetup("/com/mygaienko/practice/jpa/dao/CityRepositoryTest.xml")
    public void testFindByNameAndCountryNameAllIgnoringCaseUpperCase() {
        City actual = cityRepository.findByNameAndCountryNameAllIgnoringCase("zaporizhiya", "UKRAINE");
        assertEquals(5, (long) actual.getId());
    }

    @Test
    @DatabaseSetup("/com/mygaienko/practice/jpa/dao/CityRepositoryTest.xml")
    @ExpectedDatabase(value = "/com/mygaienko/practice/jpa/dao/CityRepositoryTest_testFindAndChangeCityType.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testFindAndChangeCityType() {
        City city = cityRepository.findByNameAndCountryNameAllIgnoringCase("zaporizhiya", "UKRAINE");
        city.setCityType(CityType.CITY_TYPE3);
        cityRepository.save(city);
    }

    @DatabaseSetup("/com/mygaienko/practice/jpa/dao/CityRepositoryTest.xml")
    @ExpectedDatabase(value = "/com/mygaienko/practice/jpa/dao/CityRepositoryTest_testDelete.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    @Test
    public void testDeleteVinnutsiya() {
        cityRepository.delete((long) 5);
    }

}