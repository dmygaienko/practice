package com.mygaienko.practice.jpa.dao;

import com.mygaienko.practice.jpa.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by mygadmy on 03/12/15.
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    City findByNameAndCountryNameAllIgnoringCase(String name, String countryName);
}
