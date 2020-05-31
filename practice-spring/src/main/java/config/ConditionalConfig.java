package config;

import config.conditional.CaseOneCondition;
import config.conditional.CaseTwoCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by enda1n on 18.09.2016.
 */
@Configuration
public class ConditionalConfig {

    @Bean(name = "stringBean")
    @Conditional(CaseOneCondition.class)
    String stringBean1() {
        return "case1";
    }

    @Bean(name = "stringBean")
    @Conditional(CaseTwoCondition.class)
    String stringBean2() {
        return "case2";
    }
}
