package com.mygaienko.practice.jpa.config;

import com.mygaienko.practice.jpa.service.SimpleService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dmygaenko on 10/12/2015.
 */
@Configuration
public class TestDatabaseConfig {

/*    @Bean
    PlatformTransactionManager transactionManager() {
        //return new JpaTransactionManager();
        return new JtaTransactionManager();
    }*/

    @Bean
    SimpleService simpleService() {
        return Mockito.mock(SimpleService.class);
    }



}
