package com.mygaienko.practice.jpa.config;

import com.mygaienko.practice.jpa.service.interfaces.ServiceA;
import com.mygaienko.practice.jpa.service.ServiceA1Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by mygadmy on 03/12/15.
 */
@Configuration
@Import(DaoConfig.class)
public class ServiceConfig {

    @Autowired
    private DaoConfig daoConfig;

    @Bean
    public ServiceA serviceA1() {
        return new ServiceA1Impl(daoConfig.daoA1());
    }

}
