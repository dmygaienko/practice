package com.mygaienko.practice.cassandra;

import com.mygaienko.practice.cassandra.config.CassandraConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by enda1n on 24.01.2016.
 */
@Configuration
@EnableAutoConfiguration
@Import({CassandraConfig.class})
public class Application {
}
