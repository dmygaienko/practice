package com.mygaienko.common.algorithms;

import org.junit.Test;

import java.util.*;

/**
 * Created by dmygaenko on 28/09/2016.
 */
public class CollectionTests {

    @Test
    public void testArrayVsSetOnContains() throws Exception {
        List<String> list = new ArrayList<>();
        list.addAll(generateStrings());

        Set<String> set = new HashSet<>();
        set.addAll(generateStrings());

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
        arrayList.addAll(generateStrings());

        List<String> linkedList = new LinkedList<>();
        linkedList.addAll(generateStrings());

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
        arrayList.addAll(generateStrings());

        List<String> linkedList = new LinkedList<>();
        linkedList.addAll(generateStrings());

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
    public void testHashMapVsTreeMapOnRemove() throws Exception {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.putAll(generateStringMap());

        Map<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(generateStringMap());

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
        hashMap.putAll(generateStringMap());

        Map<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(generateStringMap());

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

    private Map<? extends String, ? extends String> generateStringMap() {
        HashMap<String, String> map = new HashMap<>();
        for (String s : generateStrings()) {
            map.put(s, s);
        }
        return map;
    }

    private Collection<? extends String> generateStrings() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add("String" + i);
        }
        return list;
    }
}
