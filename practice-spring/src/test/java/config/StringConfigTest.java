package config;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.BeanB;
import service.BeanWithAutowiredString;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by dmygaenko on 09/09/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StringConfig.class)
public class StringConfigTest {

    @Autowired
    private BeanWithAutowiredString bean;

    @Test
    public void testStrings() throws Exception {
        List<String> autowiredStrings = bean.getAutowiredStrings();
        assertEquals(1, autowiredStrings.size());
        assertTrue(autowiredStrings.contains("string2"));
        System.out.println(autowiredStrings);

        List<String> resourceStrings = bean.getResourceStrings();
        assertEquals(1, resourceStrings.size());
        assertTrue(resourceStrings.contains("string2"));
        System.out.println(resourceStrings);

        assertEquals("string2", bean.getResourceString());
        assertEquals("string2", bean.getAutowiredString());
    }

}