package com.mygaienko.practice.service;

import com.mygaienko.practice.dao.interfaces.CountryDao;
import com.mygaienko.practice.model.Country;
import com.mygaienko.practice.model.CountryId;
import com.mygaienko.practice.service.interfaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
