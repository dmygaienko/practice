package com.mygaienko.practice.jpa.service;

import com.mygaienko.practice.jpa.dao.interfaces.CountryDao;
import com.mygaienko.practice.jpa.model.Country;
import com.mygaienko.practice.jpa.model.CountryId;
import com.mygaienko.practice.jpa.service.interfaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuda on 12/6/2015.
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;

    @Override
    public Country get(String id1, String id2) {
        return countryDao.get(new CountryId(id1, id2));
    }

    @Override
    public List<Country> getAllCountries() {
        return countryDao.getCountries();
    }

}
