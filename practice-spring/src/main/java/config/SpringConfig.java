package config;

import advice.AuditAdvice;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import service.BeanA;
import service.BeanB;

import java.util.List;

/**
 * Created by dmygaenko on 06/09/2016.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "service")
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
    @CustomQualifier(ValueEnum.MY)
    public BeanB BeanB2(@CustomQualifier(ValueEnum.MY) List<String> strings) {
        return new BeanB(strings);
    }


    @Bean
    @CustomQualifier(ValueEnum.THEIR)
    public BeanB BeanB3(@CustomQualifier(ValueEnum.THEIR) List<String> strings) {
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
        return "String3 by  @Qualifier(\"theirString\")";
    }

    @Bean
    @CustomQualifier(ValueEnum.MY)
    public String string4() {
        return "String4 by my CustomQualifier";
    }

    @Bean
    @CustomQualifier(ValueEnum.THEIR)
    public String string5() {
        return "String5 by their CustomQualifier";
    }

    @Bean
    public AuditAdvice myAspect() {
        return new AuditAdvice();
    }

}
