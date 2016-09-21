package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import service.BeanWithAutowiredString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dmygaenko on 06/09/2016.
 */
@Configuration
@PropertySource("classpath:practice.properties")
public class StringConfig {

    @Autowired
    private Environment environment;

    @Bean
    public String string1() {
        return "string1";
    }

    @Bean
    @Qualifier("my")
    public String string2() {
        return "string2";
    }

    @Bean
    public String string3() {
        return "string3";
    }

    @Bean
    public ArrayList<String> autowiredAllStringArray() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("string1");
        list.add("string2");
        list.add("string3");
        return list;
    }

    @Bean
    public List<String> stringsAsListBean() {
        return Arrays.asList("string1", "string2", "string3");
    }

    @Bean
    public List<String> myStringsAsListBean() {
        return Arrays.asList("my1", "my2", "my3");
    }

    @Bean
    public Map<String, String> stringsAsMapBean() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("a", "string1");
        map.put("b", "string2");
        map.put("c", "string3");
        return map;
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
