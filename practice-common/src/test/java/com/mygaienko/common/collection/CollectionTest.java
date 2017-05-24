package com.mygaienko.common.collection;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.mygaienko.common.util.TestUtils.*;
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
//        list2.add(null);
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

    @Test
    public void testSetAndListEquality() {
        Set<String> set1 = new HashSet<>();
        fillSet(set1);

        List<String> list = new ArrayList<>();
        fillList(list);

        assertEquals(new HashSet<>(list), set1);
    }

    private void fillSet(Set<String> set1) {
        set1.add("string1");
//        set1.add(null);
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

    @Test
    public void testGetArrayList() {
        assertEquals(Arrays.asList("1", "2", "3"), getArrayList(1, 3));
    }

    @Test
    public void subList() {
        List<String> strings = getArrayList(1, 10).subList(0, 5);
        strings.set(0, "00");
        System.out.println(strings);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void modifyArrayAsList() {
        List<String> strings = Arrays.asList("1", "2", "3");
        strings.add("4");
        System.out.println(strings);
    }

    @Test
    public void mergeMaps() {
        TreeMap<String, String> m1 = getTreeMap(0, 5);
        TreeMap<String, String> m2 = getTreeMap(4, 7);

        TreeMap<String, String> merged = Stream.of(m1, m2)
                .map(Map::entrySet)          // converts each map into an entry set
                .flatMap(Collection::stream) // converts each set into an entry stream, then
                // "concatenates" it in place of the original set
                .collect(
                        Collectors.toMap(        // collects into a map
                                Map.Entry::getKey,   // where each entry is based
                                Map.Entry::getValue, // on the entries in the stream
                                (v1, v2) -> v1 + v2,
                                TreeMap::new
                        ));
        System.out.println(merged);
    }

    @Test
    public void testGetTreeMap() {
        TreeMap<String, String> treeMap = getTreeMap(0, 5);
        treeMap.put("1", "111");
        System.out.println(treeMap);
    }

    @Test
    public void subLists(){

        List<String> l = new ArrayList<>();
        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");

        List<String> sl = l.subList(1, 3);
        sl.add("FF");
        sl.set(0, "");

        System.out.println("subList " + sl);
        System.out.println("original " + l);
    }


    @Test
    public void testGetHashMapAndCompute() {
        HashMap<String, String> hashMap = getHashMap(0, 5);
        hashMap.compute("6", (key, value) -> value == null ? "default" : value + value);
        hashMap.put("1", "111");
        System.out.println(hashMap);
    }

    @Test
    public void testGetLinkedHashMap() {
        LinkedHashMap<String, String> hashMap = getLinkedHashMap(0, 5);
        System.out.println("keySet() :" + hashMap.keySet());
        System.out.println("values() :" + hashMap.values());

        hashMap.put("1", "111");
        System.out.println(hashMap);
    }

    @Test
    public void testLinkedHashSetRetain() {
        LinkedHashSet<String> hashSet1 = getLinkedHashSet(0, 5);
        LinkedHashSet<String> hashSet2 = getLinkedHashSet(1, 3);
        System.out.println(hashSet1.retainAll(hashSet2));
        System.out.println(hashSet1);
    }

    @Test
    public void testLinkedHashMapWithNewValues() {
        LinkedHashMap<String, String> map = getLinkedHashMap(0, 100);
        for (int i = 0; i < 100; i++) {
            map.put(Integer.toString(new Random().nextInt(100)), "NEW");
        }
        System.out.println(map);
    }

    @Test
    public void testTreeMapWithNewValues() {
        TreeMap<String, String> map = getTreeMap(0, 100);
        for (int i = 0; i < 100; i++) {
            map.put(Integer.toString(new Random().nextInt(100)), "NEW");
        }
        System.out.println(map);
    }

    @Test
    public void testGetHashMapAndComputeIfPresentOrAbsent() {
        HashMap<String, String> hashMap = getHashMap(0, 5);
        hashMap.computeIfPresent("1", (key, value) -> value + value);
        hashMap.computeIfAbsent("6", (key) -> "default");
        System.out.println(hashMap);
    }

    @Test
    public void remove() {
        HashMap<String, String> hashMap = getHashMap(0, 2);
        System.out.println(hashMap);
        boolean removeResult1 = hashMap.remove("0", "1");
        System.out.println("result: " + removeResult1 + "\n hashMap: \n" + hashMap);
        boolean removeResult2 = hashMap.remove("0", "0");
        System.out.println("result: " + removeResult2 + "\n hashMap: \n" + hashMap);
    }

}
