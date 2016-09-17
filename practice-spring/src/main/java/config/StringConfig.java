package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import service.BeanWithAutowiredString;

/**
 * Created by dmygaenko on 06/09/2016.
 */
@Configuration
@PropertySource("classpath:practice.properties")
public class StringConfig {

    @Autowired
    Environment environment;

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

    @Bean(initMethod = "init", destroyMethod = "clean")
    @Qualifier("my")
    public BeanWithAutowiredString bean1() {
        BeanWithAutowiredString bean = new BeanWithAutowiredString();
        bean.setEnvPropertiesLine(environment.getProperty("test.practice.props"));
        return bean;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
