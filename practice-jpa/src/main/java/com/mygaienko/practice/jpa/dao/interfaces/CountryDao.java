package com.mygaienko.practice.jpa.dao.interfaces;

import com.mygaienko.practice.jpa.model.Country;
import com.mygaienko.practice.jpa.model.CountryId;

import java.util.List;

/**
 * Created by liuda on 12/6/2015.
 */
public interface CountryDao {

    Country get(CountryId id);

    Country getAndIncrement(CountryId id);

    void remove(Country country);

    List<Country> getCountries();

    void merge(Country country);

    void persist(Country country);
}
