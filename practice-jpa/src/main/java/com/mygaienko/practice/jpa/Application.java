package com.mygaienko.practice.jpa;

import com.mygaienko.practice.jpa.config.MyNeo4jConfig;
import com.mygaienko.practice.jpa.config.PersistenceConfig;
import com.mygaienko.practice.jpa.config.ServiceConfig;
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
@Import({PersistenceConfig.class, ServiceConfig.class/*, MyNeo4jConfig.class*/})
@EntityScan(basePackages = {"com.mygaienko.practice.jpa.model"})
@ComponentScan(basePackages = {"com.mygaienko.practice.jpa"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
