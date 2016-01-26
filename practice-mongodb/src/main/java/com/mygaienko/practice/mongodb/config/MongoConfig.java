package com.mygaienko.practice.mongodb.config;

import com.mygaienko.practice.mongodb.repository.CustomerRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by enda1n on 27.01.2016.
 */
@Configuration
/*@EnableMongoAuditing*/
@EnableMongoRepositories(basePackageClasses = CustomerRepository.class)
@EnableAutoConfiguration
public class MongoConfig extends EmbeddedMongoAutoConfiguration {
}
