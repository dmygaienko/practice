package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import service.BeanC;
import service.BeanPrototype;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;


/**
 * Created by dmygaenko on 06/09/2016.
 */
@Configuration
@ComponentScan(basePackages = "service")
public class PrototypeConfig {

    public BeanC myBeanC() {
        return new BeanC();
    }

    @Bean
    @Scope(scopeName = SCOPE_PROTOTYPE)
    public BeanPrototype myBeanPrototype() {
        return new BeanPrototype();
    }
}
