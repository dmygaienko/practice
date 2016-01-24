package com.mygaienko.practice.jpa.dao.interfaces;

import com.mygaienko.practice.jpa.model.Country;
import com.mygaienko.practice.jpa.model.CountryId;

import java.util.List;

/**
 * Created by liuda on 12/6/2015.
 */
public interface CountryDao {

    Country get(CountryId id);

    List<Country> getCountries();
}
