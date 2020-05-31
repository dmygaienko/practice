package config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by enda1n on 18.09.2016.
 */
public class ConditionalConfigTest {

    @Test
    public void testCaseOneCondition() {
        System.setProperty("test.condition", "case1");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                ConditionalConfig.class);

        assertEquals("case1", context.getBean("stringBean", String.class));
    }

    @Test
    public void testCaseTwoCondition() {
        System.setProperty("test.condition", "case2");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                ConditionalConfig.class);

        assertEquals("case2", context.getBean("stringBean", String.class));
    }
}
