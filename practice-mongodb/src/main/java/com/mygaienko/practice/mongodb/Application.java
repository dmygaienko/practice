package com.mygaienko.practice.mongodb;

import com.mygaienko.practice.mongodb.config.MongoConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by enda1n on 27.01.2016.
 */

@Configuration
@Import(MongoConfig.class)
public class Application {
}
