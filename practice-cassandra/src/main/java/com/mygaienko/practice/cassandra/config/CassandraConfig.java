package com.mygaienko.practice.cassandra.config;

import com.datastax.driver.core.Session;
import org.springframework.cassandra.config.java.AbstractCqlTemplateConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 * Created by enda1n on 24.01.2016.
 */
@Configuration
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCqlTemplateConfiguration {

    @Override
    public String getKeyspaceName() {
        return "example";
    }

    @Bean
    public CassandraTemplate cassandraTemplate(Session session) {
        return new CassandraTemplate(session);
    }
}
