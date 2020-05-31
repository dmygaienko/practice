package com.mygaienko.practice.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by mygadmy on 03/12/15.
 */
@Configuration
@PropertySource("classpath:persistence.properties")
public class PersistenceConfig {



}
