package com.mygaienko.cglib.model;

import org.junit.Test;
import org.mockito.cglib.core.KeyFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by dmygaenko on 28/01/2016.
 */
public class SampleKeyFactoryTest {

    @Test
    public void testKeyFactory() throws Exception {
        SampleKeyFactory keyFactory = (SampleKeyFactory) KeyFactory.create(SampleKeyFactory.class);
        Object key = keyFactory.newInstance("foo", 42);
        Map<Object, String> map = new HashMap<Object, String>();
        map.put(key, "Hello cglib!");
        assertEquals("Hello cglib!", map.get(keyFactory.newInstance("foo", 42)));
    }

}