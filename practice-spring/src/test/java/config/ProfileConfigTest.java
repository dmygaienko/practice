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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DevProfileConfig.class, LiveProfileConfig.class, DefaultProfileConfig.class})
@ActiveProfiles("dev")
public class ProfileConfigTest {

    @Autowired
    String stringBean;

    @Test
    public void test() {
        assertEquals("dev", stringBean);
    }

    @Test
    public void testWithInnerConfig() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("live");
        context.register(DevProfileConfig.class, LiveProfileConfig.class, DefaultProfileConfig.class);
        context.refresh();

        assertEquals("live", context.getBean("stringBean", String.class));
    }

    @Test
    public void testWithInnerConfigAndAbstractEnv() {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "live");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                DevProfileConfig.class, LiveProfileConfig.class, DefaultProfileConfig.class);

        assertEquals("live", context.getBean("stringBean", String.class));
    }

    @Test
    public void testDefaultConfig() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                DevProfileConfig.class, LiveProfileConfig.class, DefaultProfileConfig.class);

        assertEquals("default", context.getBean("stringBean", String.class));
    }
}
