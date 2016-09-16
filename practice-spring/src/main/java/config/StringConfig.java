package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.BeanWithAutowiredString;

/**
 * Created by dmygaenko on 06/09/2016.
 */
@Configuration
public class StringConfig {

    @Bean
   /* @Qualifier("my")*/
    public String string1() {
        return "string1";
    }

    @Bean
    @Qualifier("my")
    public String string2() {
        return "string2";
    }

    @Bean
   /* @Qualifier("my")*/
    public String string3() {
        return "string3";
    }

    @Bean
    @Qualifier("my")
    public BeanWithAutowiredString bean1() {
        return new BeanWithAutowiredString();
    }

}
