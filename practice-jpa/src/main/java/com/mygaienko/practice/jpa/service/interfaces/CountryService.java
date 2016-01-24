package com.mygaienko.practice.jpa.service.interfaces;

import com.mygaienko.practice.jpa.model.Country;

import java.util.List;

/**
 * Created by liuda on 12/6/2015.
 */
public interface CountryService {

    Country get(String id1, String id2);

    List<Country> getAllCountries();
}
