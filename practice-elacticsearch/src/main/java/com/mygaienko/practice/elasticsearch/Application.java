package com.mygaienko.practice.elasticsearch;

import com.mygaienko.practice.elasticsearch.config.ElasticConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({ElasticConfig.class})
@EnableAutoConfiguration
public class Application {
}
