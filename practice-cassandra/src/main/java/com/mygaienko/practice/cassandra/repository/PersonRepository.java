package com.mygaienko.practice.cassandra.repository;

import com.mygaienko.practice.cassandra.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by enda1n on 24.01.2016.
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByLastname(String lastname);
}
