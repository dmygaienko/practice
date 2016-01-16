package com.mygaienko.practice.web;

import com.mygaienko.practice.model.Country;
import com.mygaienko.practice.service.interfaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by mygadmy on 03/12/15.
 */
@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping("/firstCountry")
    Country countries() {
        return countryService.get("1", "1");
    }

    @RequestMapping("/allCountries")
    List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }
}
