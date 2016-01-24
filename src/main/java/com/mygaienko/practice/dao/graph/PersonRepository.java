package com.mygaienko.practice.dao.graph;

import com.mygaienko.practice.model.graph.Person;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by enda1n on 23.01.2016.
 */
@Repository
public interface PersonRepository extends GraphRepository<Person> {

    Person findByName(String name);

    Iterable<Person> findByTeammatesName(String name);
}
