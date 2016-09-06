package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.BeanA;
import service.BeanB;

import java.util.List;

/**
 * Created by dmygaenko on 06/09/2016.
 */
@Configuration
public class SpringConfig {

/*    @Autowired
    List<String> autoWiredStrings;*/

    @Bean
    @Qualifier("my")
    public BeanA myBeanA(@Qualifier("myB") BeanB beanB) {
        return new BeanA(beanB);
    }

    @Bean
    @Qualifier("myB")
    public BeanB BeanB() {
        return new BeanB("my");
    }

    @Bean
    @Qualifier("their")
    public BeanB BeanB1(@Qualifier("theirString") List<String> strings) {
        return new BeanB(strings);
    }

    @Bean
    @Qualifier("their")
    public BeanB BeanBAll(List<String> strings) {
        return new BeanB(strings);
    }

    @Bean
    public String string1() {
        return "String1";
    }

    @Bean
    @Qualifier("theirString")
    public String string2() {
        return "String2";
    }

    @Bean
    @Qualifier("theirString")
    public String string3() {
        return "String3";
    }

}
