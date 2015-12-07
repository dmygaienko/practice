package com.mygaienko.practice.web;

import com.mygaienko.practice.model.City;
import com.mygaienko.practice.service.interfaces.CityService;
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
