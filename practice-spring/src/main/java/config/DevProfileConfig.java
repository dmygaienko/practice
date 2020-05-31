package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by dmygaenko on 06/09/2016.
 */
@Configuration
@Profile("dev")
public class DevProfileConfig {

    @Bean
    public String stringBean() {
        return "dev";
    }

}
