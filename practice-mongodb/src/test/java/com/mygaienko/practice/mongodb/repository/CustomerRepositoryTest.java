package com.mygaienko.practice.mongodb.repository;

import com.mygaienko.practice.mongodb.Application;
import com.mygaienko.practice.mongodb.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by enda1n on 27.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSave() {
        Customer customer = new Customer("firstName", "lastName");
        customerRepository.save(customer);

        customerRepository.findAll();
    }

}