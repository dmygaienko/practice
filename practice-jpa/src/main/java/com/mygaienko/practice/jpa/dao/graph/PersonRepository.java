package com.mygaienko.practice.jpa.dao.graph;

import com.mygaienko.practice.jpa.model.graph.Person;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by enda1n on 23.01.2016.
 */
@Repository
public interface PersonRepository extends GraphRepository<Person> {

    Person findByName(String name);

    Iterable<Person> findByTeammatesName(String name);
}
