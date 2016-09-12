package config;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.BeanB;

import static org.junit.Assert.assertTrue;

/**
 * Created by dmygaenko on 09/09/2016.
 */
@RunWith(JUnit4.class)
public class SpringConfigTest {

    private  AnnotationConfigApplicationContext context;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext();

        context.register(SpringConfig.class);
        context.refresh();
    }

    @Test
    public void testBeanB2() throws Exception {
        BeanB beanB2 = context.getBean("BeanB2", BeanB.class);
        assertTrue(beanB2.getStrings().contains("String4 by my CustomQualifier"));
    }

    @Test
    public void testBeanB3() throws Exception {
        BeanB beanB3 = context.getBean("BeanB3", BeanB.class);
        assertTrue(beanB3.getStrings().contains("String5 by their CustomQualifier"));
    }

    @Test
    public void testBeanBWithAspect() throws Exception {
        BeanB beanB2 = context.getBean("BeanB2", BeanB.class);
        beanB2.serve();
    }


}