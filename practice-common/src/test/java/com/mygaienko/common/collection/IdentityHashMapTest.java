package com.mygaienko.common.collection;

import org.junit.Test;

import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityHashMapTest {

    @Test
    public void test() {
        Map<TestClass, String> map = new IdentityHashMap<>();
        TestClass key = new TestClass(1L);

        map.put(key, "ololo");

        System.out.println(map.get(key));
        System.out.println(map.get(new TestClass(1L)));
    }
}

class TestClass {

    private Long id;

    public TestClass(Long id) {
        this.id = id;
    }

}
