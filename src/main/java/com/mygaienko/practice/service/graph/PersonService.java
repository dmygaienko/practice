package com.mygaienko.practice.service.graph;

import com.mygaienko.practice.dao.graph.PersonRepository;
import com.mygaienko.practice.model.graph.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by enda1n on 23.01.2016.
 */
@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @PostConstruct
    public void init() {
        personRepository.save(new Person("1"));
        personRepository.save(new Person("2"));
        personRepository.save(new Person("3"));
    }

}
