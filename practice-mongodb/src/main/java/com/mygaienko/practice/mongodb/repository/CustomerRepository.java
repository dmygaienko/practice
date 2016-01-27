package com.mygaienko.practice.mongodb.repository;

import com.mygaienko.practice.mongodb.model.Customer;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by enda1n on 26.01.2016.
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);

}
