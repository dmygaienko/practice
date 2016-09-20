package com.mygaienko.common.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by dmygaenko on 20/09/2016.
 */
public class CollectionTest {

    @Test
    public void testListEquality() {
        ArrayList<String> list1 = new ArrayList<>();
        fillList(list1);

        LinkedList<String> list2 = new LinkedList<>();
        fillList(list2);

        assertEquals(list1, list2);
    }

    private void fillList(List<String> list2) {
        list2.add("string1");
        list2.add(null);
        list2.add("string2");
        list2.add("string3");
    }

    @Test
    public void testSetEquality() {
        Set<String> set1 = new HashSet<>();
        fillSet(set1);

        Set<String> set2 = new LinkedHashSet<>();
        fillSet(set2);

        assertEquals(set1, set2);
    }

    private void fillSet(Set<String> set1) {
        set1.add("string1");
        set1.add(null);
        set1.add("string2");
        set1.add("string3");
    }

    @Test
    public void testMapEquality() {
        Map<String, String> map1 = new HashMap<>();
        fillMap(map1);

        Map<String, String> map2 = new TreeMap<>();
        fillMap(map2);

        assertEquals(map1, map2);
    }

    private void fillMap(Map<String, String> map1) {
        map1.put("key1", "string1");
        map1.put("key2", "string2");
        map1.put("key4", null);
        map1.put("key3", "string3");
    }

}
