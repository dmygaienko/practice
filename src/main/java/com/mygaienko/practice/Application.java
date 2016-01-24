package com.mygaienko.practice;

import com.mygaienko.practice.config.MyNeo4jConfig;
import com.mygaienko.practice.config.PersistenceConfig;
import com.mygaienko.practice.config.ServiceConfig;
import com.mygaienko.practice.model.City;
import com.mygaienko.practice.model.graph.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by mygadmy on 03/12/15.
 */
@Configuration
@EnableAutoConfiguration
@Import({PersistenceConfig.class, ServiceConfig.class, MyNeo4jConfig.class})
@EntityScan(basePackages = {"com.mygaienko.practice.model"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
