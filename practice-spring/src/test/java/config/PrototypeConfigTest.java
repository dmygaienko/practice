package config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by dmygaenko on 16/09/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PrototypeConfig.class)
public class PrototypeConfigTest {

    @Autowired
    private service.BeanC beanC;


    @Test
    public void testPrototype() throws Exception {
        System.out.println("testPrototype");
        HashSet<String> ids = new HashSet<String>();
        assertTrue(ids.add(String.valueOf(beanC.getPrototype().getId())));
        assertTrue(ids.add(String.valueOf(beanC.getPrototype().getId())));
        assertTrue(ids.add(String.valueOf(beanC.getPrototype().getId())));
        assertTrue(ids.add(String.valueOf(beanC.getPrototype().getId())));
        assertTrue(ids.add(String.valueOf(beanC.getPrototype().getId())));
        assertEquals(ids.size(), 5);
        System.out.println(ids);
    }
}