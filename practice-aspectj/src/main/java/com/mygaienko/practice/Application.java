package com.mygaienko.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

/**
 * Created by enda1n on 24.07.2017.
 */
@SpringBootApplication
@EnableLoadTimeWeaving
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
