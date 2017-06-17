package com.mygaienko.practice.jpa.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mygaienko.practice.jpa.dao.interfaces.CountryDao;
import com.mygaienko.practice.jpa.model.City;
import com.mygaienko.practice.jpa.model.Country;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;


/**
 * Created by dmygaenko on 07/02/2017.
 */
@DatabaseSetup("/com/mygaienko/practice/jpa/dao/CityRepositoryTest.xml")
public class CountryDaoImplTest extends AbstractDaoTest {

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void testDelete() throws Exception {
        List<Country> countries = countryDao.getCountries();
        System.out.println("====================");
        countries.get(0).getCities().get(0).getName();
    }

    @Test
    public void testRemoveWithOrphanRemovalAndMerge() throws Exception {
        List<Country> countries = countryDao.getCountries();
        Country country = countries.get(0);
        List<City> cities = country.getCities();

        assertThat(cities, hasSize(3));

        cities.remove(0);
        countryDao.merge(country);
        List<City> all = cityRepository.findAll();

        assertThat(all, hasSize(2));
    }

    @Test
    public void testRemoveWithOrphanRemoval() throws Exception {
        List<Country> countries = countryDao.getCountries();
        Country country = countries.get(0);
        List<City> cities = country.getCities();

        assertThat(cities, hasSize(3));

        cities.remove(0);
//        countryDao.merge(country);
        List<City> all = cityRepository.findAll();

        assertThat(all, hasSize(2));
    }

}