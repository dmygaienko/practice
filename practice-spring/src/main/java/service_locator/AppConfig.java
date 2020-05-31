package service_locator;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dmygaenko on 13/09/2016.
 */
@Configuration
@ComponentScan(basePackages = {"service_locator"})
public class AppConfig {

    @Bean
    public FactoryBean serviceLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ParserFactory.class);
        return factoryBean;
    }

    @Bean(name = "jsonParser")
    public JsonParser jsonParser() {
        return new JsonParser();
    }

    @Bean(name = "xmlParser")
    public XmlParser xmlParser() {
        return new XmlParser();
    }
}
