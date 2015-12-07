package com.mygaienko.practice;

import com.mygaienko.practice.config.PersistenceConfig;
import com.mygaienko.practice.config.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

/**
 * Created by mygadmy on 03/12/15.
 */
@Configuration
@EnableAutoConfiguration
@Import({PersistenceConfig.class, ServiceConfig.class})
@ComponentScan(basePackages = {"com.mygaienko.*"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
