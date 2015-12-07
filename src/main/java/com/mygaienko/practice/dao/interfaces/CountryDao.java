package com.mygaienko.practice.dao.interfaces;

import com.mygaienko.practice.model.Country;
import com.mygaienko.practice.model.CountryId;

/**
 * Created by liuda on 12/6/2015.
 */
public interface CountryDao {

    Country get(CountryId id);
}
