package com.mygaienko.practice.jpa.config;

import com.mygaienko.practice.jpa.dao.interfaces.DaoA;
import com.mygaienko.practice.jpa.dao.DaoA1Impl;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Created by mygadmy on 03/12/15.
 */
@Configuration
@PropertySource("classpath:persistence.properties")
@EnableJpaRepositories(value = "com.mygaienko.practice.jpa.dao", transactionManagerRef = "jpaTransactionManager")
public class DaoConfig {

    @Autowired
    private Environment env;

    @Value("${property1}")
    private String property;

    @Bean
    public DaoA daoA1(){
        DaoA1Impl dao = new DaoA1Impl();
        dao.setProperty(property);
        return  dao;
    }

    @Bean
    @Scope("Prototype")
    public DaoA daoA2(){
        String property1  = env.getProperty("property1");

        DaoA1Impl dao = new DaoA1Impl();
        dao.setProperty(property1);
        return dao;
    }

    @Bean(name = "jpaTransactionManager")
    public PlatformTransactionManager jpaTransactionManager() {
        return new JpaTransactionManager();
    }
/*
    @Bean(name = "datasourceBeanFactory")
    public FactoryBean<DataSource> beanFactory() {
        AbstractFactoryBean<DataSource> factoryBean = new AbstractFactoryBean<DataSource>() {

            @Override
            public Class<?> getObjectType() {
                return DataSource.class;
            }

            @Override
            protected DataSource createInstance() throws Exception {
                return new JdbcDataSource();
            }
        };
        factoryBean.setSingleton(true);
        return factoryBean;
    }*/

}
