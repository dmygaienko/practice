package com.mygaienko.practice.web;

import com.mygaienko.practice.model.Country;
import com.mygaienko.practice.service.interfaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mygadmy on 03/12/15.
 */
@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping("/countries")
    Country authors() {
        return countryService.get("1", "1");
    }
}
