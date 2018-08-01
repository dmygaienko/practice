package com.mygaienko.common.collection.commons;

import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HashSetValuedHashMapTest {

    @Test
    public void test() {
        HashSetValuedHashMap<String, String> map = new HashSetValuedHashMap<>();
        map.put("1", "1");
        map.put("1", "2");

        assertThat(map.get("1"), hasItems("1", "2"));

        Collection<Map.Entry<String, String>> entries = map.entries();
        System.out.println(entries);
    }
}
