package com.mygaienko.practice.service;

import com.mygaienko.practice.dao.CityRepository;
import com.mygaienko.practice.model.City;
import com.mygaienko.practice.service.interfaces.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mygadmy on 03/12/15.
 */
@Service("cityService")
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository repository;

    @Override
    public List<City> getAllCities() {
        return repository.findAll();
    }
}
