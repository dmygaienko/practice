package com.mygaienko.practice.jpa.service;

import com.mygaienko.practice.jpa.dao.CityRepository;
import com.mygaienko.practice.jpa.service.interfaces.CityService;
import com.mygaienko.practice.jpa.model.City;
import org.springframework.beans.factory.annotation.Autowired;
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
