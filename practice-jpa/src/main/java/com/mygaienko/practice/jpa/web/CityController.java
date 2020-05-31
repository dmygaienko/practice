package com.mygaienko.practice.jpa.web;

import com.mygaienko.practice.jpa.service.interfaces.CityService;
import com.mygaienko.practice.jpa.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by mygadmy on 03/12/15.
 */
@RestController
public class CityController {

    @Autowired
    @Qualifier("cityService")
    private CityService cityService;

    @RequestMapping("/cities")
    List<City> cities() {
        return cityService.getAllCities();
    }
}
