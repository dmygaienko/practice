package com.mygaienko.practice.service.interfaces;

import com.mygaienko.practice.model.Country;
import com.mygaienko.practice.model.CountryId;

import java.util.List;

/**
 * Created by liuda on 12/6/2015.
 */
public interface CountryService {

    Country get(String id1, String id2);

    List<Country> getAllCountries();
}
