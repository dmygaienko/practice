package com.mygaienko.common.algorithms;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by dmygaenko on 28/09/2016.
 */
public class CollectionTests {

    @Test
    public void testArrayVsSetOnContains() throws Exception {
        List<String> list = new ArrayList<>();
        list.addAll(generateStrings(1000000));

        Set<String> set = new HashSet<>();
        set.addAll(generateStrings(1000000));

        long start, end;

        start = System.nanoTime();
        list.contains("String100000");
        end = System.nanoTime();
        System.out.println("list.contains(\"String100000\") takes : " + (end - start));

        start = System.nanoTime();
        list.contains("String100000");
        end = System.nanoTime();
        System.out.println("list.contains(\"String100000\") takes: " + (end - start));

        start = System.nanoTime();
        set.contains("String100000");
        end = System.nanoTime();
        System.out.println("set.contains(\"String100000\") takes: " + (end - start));

        start = System.nanoTime();
        set.contains("String100000");
        end = System.nanoTime();
        System.out.println("set.contains(\"String100000\") takes: " + (end - start));
    }

    @Test
    public void testArrayVsLinkedListOnRemoveAll() throws Exception {
        List<String> arrayList = new ArrayList<>();
        arrayList.addAll(generateStrings(1000000));

        List<String> linkedList = new LinkedList<>();
        linkedList.addAll(generateStrings(1000000));

        long start, end;

        start = System.nanoTime();
        arrayList.removeAll(Arrays.asList("String1000", "String1001"));
        end = System.nanoTime();
        System.out.println("arrayList.removeAll takes : " + (end - start));

        start = System.nanoTime();
        arrayList.removeAll(Arrays.asList("String1002", "String1003"));
        end = System.nanoTime();
        System.out.println("arrayList.removeAll takes: " + (end - start));

        start = System.nanoTime();
        linkedList.removeAll(Arrays.asList("String1000", "String1001"));
        end = System.nanoTime();
        System.out.println("linkedList.removeAll takes: " + (end - start));

        start = System.nanoTime();
        linkedList.removeAll(Arrays.asList("String1002", "String1003"));
        end = System.nanoTime();
        System.out.println("linkedList.removeAll takes: " + (end - start));
    }

    @Test
    public void testArrayVsLinkedListOnRemove() throws Exception {
        List<String> arrayList = new ArrayList<>();
        arrayList.addAll(generateStrings(1000000));

        List<String> linkedList = new LinkedList<>();
        linkedList.addAll(generateStrings(1000000));

        long start, end;

        start = System.nanoTime();
        arrayList.remove("String1000");
        end = System.nanoTime();
        System.out.println("arrayList.remove(\"String1000\") takes : " + (end - start));

        start = System.nanoTime();
        arrayList.remove("String1001");
        end = System.nanoTime();
        System.out.println("arrayList.remove(\"String1001\") takes: " + (end - start));

        start = System.nanoTime();
        linkedList.remove("String1000");
        end = System.nanoTime();
        System.out.println("linkedList.remove(\"String1000\") takes: " + (end - start));

        start = System.nanoTime();
        linkedList.remove("String1001");
        end = System.nanoTime();
        System.out.println("linkedList.remove(\"String1001\") takes: " + (end - start));
    }

    @Test
    public void testArrayVsLinkedListOnSort() throws Exception {
        List<String> arrayList = new ArrayList<>();
        arrayList.addAll(generateStrings(10000));

        List<String> linkedList = new LinkedList<>();
        linkedList.addAll(generateStrings(10000));

        long start, end, arrayListSortTime, linkedListSortTime;
        start = System.nanoTime();
        arrayList.sort(String::compareTo);
        end = System.nanoTime();
        arrayListSortTime = (end - start);
        System.out.println("arrayList.sort takes : " + arrayListSortTime + "\n" + arrayList);

        start = System.nanoTime();
        linkedList.sort(String::compareTo);
        end = System.nanoTime();
        linkedListSortTime = (end - start);
        System.out.println("linkedList.sort takes: " + linkedListSortTime + "\n" + linkedList);
        assertTrue(linkedListSortTime < arrayListSortTime);
    }

    @Test
    public void testHashMapVsTreeMapOnRemove() throws Exception {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.putAll(generateStringMap(1000000));

        Map<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(generateStringMap(1000000));

        long start, end;

        start = System.nanoTime();
        hashMap.remove("String1000");
        end = System.nanoTime();
        System.out.println("hashMap.remove(\"String1000\") takes : " + (end - start));

        start = System.nanoTime();
        hashMap.remove("String1001");
        end = System.nanoTime();
        System.out.println("hashMap.remove(\"String1001\") takes: " + (end - start));

        start = System.nanoTime();
        hashMap.remove("String1002");
        end = System.nanoTime();
        System.out.println("hashMap.remove(\"String1002\") takes: " + (end - start));

        start = System.nanoTime();
        treeMap.remove("String1000");
        end = System.nanoTime();
        System.out.println("treeMap.remove(\"String1000\") takes: " + (end - start));

        start = System.nanoTime();
        treeMap.remove("String1001");
        end = System.nanoTime();
        System.out.println("treeMap.remove(\"String1001\") takes: " + (end - start));

        start = System.nanoTime();
        treeMap.remove("String1002");
        end = System.nanoTime();
        System.out.println("treeMap.remove(\"String1002\") takes: " + (end - start));
    }

    @Test
    public void testHashMapVsTreeMapOnGet() throws Exception {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.putAll(generateStringMap(1000000));

        Map<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(generateStringMap(1000000));

        long start, end;

        start = System.nanoTime();
        hashMap.get("String1000");
        end = System.nanoTime();
        System.out.println("hashMap.get(\"String1000\") takes : " + (end - start));

        start = System.nanoTime();
        hashMap.get("String1001");
        end = System.nanoTime();
        System.out.println("hashMap.get(\"String1001\") takes: " + (end - start));

        start = System.nanoTime();
        hashMap.get("String1002");
        end = System.nanoTime();
        System.out.println("hashMap.get(\"String1002\") takes: " + (end - start));

        start = System.nanoTime();
        treeMap.get("String1000");
        end = System.nanoTime();
        System.out.println("treeMap.get(\"String1000\") takes: " + (end - start));

        start = System.nanoTime();
        treeMap.get("String1001");
        end = System.nanoTime();
        System.out.println("treeMap.get(\"String1001\") takes: " + (end - start));

        start = System.nanoTime();
        treeMap.get("String1002");
        end = System.nanoTime();
        System.out.println("treeMap.get(\"String1002\") takes: " + (end - start));
    }

    @Test
    public void testHashMapVsTreeMapOnContainsValue() throws Exception {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.putAll(generateStringMap(1000000));

        Map<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(generateStringMap(1000000));

        long start, end, hashMapTime, treeMapTime;

        start = System.nanoTime();
        hashMap.containsValue("String100000");
        end = System.nanoTime();
        hashMapTime = end - start;
        System.out.println("hashMap.containsValue(\"String1000\") takes : " + hashMapTime);

        start = System.nanoTime();
        hashMap.containsValue("String100001");
        end = System.nanoTime();
        treeMapTime = end - start;
        System.out.println("hashMap.containsValue(\"String1001\") takes: " + treeMapTime);
    }

    @Test
    public void testHashMapVsTreeMapOnToString() throws Exception {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.putAll(generateStringMap(1000));
        System.out.println("HashMap: " + hashMap.toString());

        Map<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(generateStringMap(1000));
        System.out.println("TreeMap: " + treeMap.toString());
    }

    private Map<? extends String, ? extends String> generateStringMap(int count) {
        HashMap<String, String> map = new HashMap<>();
        for (String s : generateStrings(count)) {
            map.put(s, s);
        }
        return map;
    }

    private Collection<? extends String> generateStrings(int count) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add("String" + i);
        }
        return list;
    }
}
