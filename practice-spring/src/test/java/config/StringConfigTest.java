package config;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.BeanWithAutowiredString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
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
    public void testAutowiredStrings() throws Exception {
        List<String> autowiredStrings = bean.getAutowiredStrings();
        assertEquals(1, autowiredStrings.size());
        assertTrue(autowiredStrings.contains("string2"));
        System.out.println(autowiredStrings);

        assertEquals("string2", bean.getAutowiredString());
    }

    @Test
    public void testAutowiredAllStrings() throws Exception {
        List<String> autowiredStrings = bean.getAutowiredAllStrings();
        assertEquals(3, autowiredStrings.size());
        assertTrue(autowiredStrings.contains("string1"));
        assertTrue(autowiredStrings.contains("string2"));
        assertTrue(autowiredStrings.contains("string3"));
        System.out.println(autowiredStrings);

        assertEquals("string2", bean.getAutowiredString());
    }

    @Test
    public void testAutowiredAllStringsArray() throws Exception {
        ArrayList<String> autowiredStrings = bean.getAutowiredAllStringArray();
        assertEquals(3, autowiredStrings.size());
        assertTrue(autowiredStrings.contains("string111"));
        assertTrue(autowiredStrings.contains("string222"));
        assertTrue(autowiredStrings.contains("string333"));
        System.out.println(autowiredStrings);
    }

    @Test
    public void testStringsAsListBean() throws Exception {
        List<String> stringsAsListBean = bean.getStringsAsListBean();
        assertEquals(3, stringsAsListBean.size());
        assertTrue(stringsAsListBean.contains("string1"));
        assertTrue(stringsAsListBean.contains("string2"));
        assertTrue(stringsAsListBean.contains("string3"));
    }

    @Test
    public void testMyStringsAsListBean() throws Exception {
        List<String> stringsAsListBean = bean.getMyStringsAsListBean();
        assertEquals(3, stringsAsListBean.size());
        assertTrue(stringsAsListBean.contains("my1"));
        assertTrue(stringsAsListBean.contains("my2"));
        assertTrue(stringsAsListBean.contains("my3"));
    }

    @Test
    public void testAutowiredStringsAsMapBean() throws Exception {
        Map<String, String> map = bean.getAutowiredStringsAsMapBean();
        assertEquals(3, map.size());
        assertTrue(map.containsKey("string1") && "string1".equals(map.get("string1")));
        assertTrue(map.containsKey("string2") && "string2".equals(map.get("string2")));
        assertTrue(map.containsKey("string3") && "string3".equals(map.get("string3")));
    }
    @Test
    public void testResourceStringsAsMapBean() throws Exception {
        Map<String, String> map = bean.getResourceStringsAsMapBean();
        assertEquals(3, map.size());
        assertTrue(map.containsKey("a") && "string1".equals(map.get("a")));
        assertTrue(map.containsKey("b") && "string2".equals(map.get("b")));
        assertTrue(map.containsKey("c") && "string3".equals(map.get("c")));
    }

    @Test
    public void testResourceStrings() throws Exception {
        List<String> resourceStrings = bean.getResourceStrings();
        assertEquals(1, resourceStrings.size());
        assertTrue(resourceStrings.contains("string2"));
        System.out.println(resourceStrings);

        assertEquals("string2", bean.getResourceString());

    }

    @Test
    public void testProperties() throws Exception {
        assertEquals("property1,property2,property3,property4", bean.getEnvPropertiesLine());
        assertEquals("property1,property2,property3,property4", bean.getPropertiesLine());

        Set<String> properties = bean.getProperties();
        assertEquals(4, properties.size());
        assertTrue(properties.contains("property1"));
        assertTrue(properties.contains("property2"));
        assertTrue(properties.contains("property3"));
        assertTrue(properties.contains("property4"));
    }
}