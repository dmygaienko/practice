package com.mygaienko.common.algorithms;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by dmygaenko on 28/09/2016.
 */
public class CollectionTests {

    private Map<String, String> generatedMap;
    private List<String> generatedList;

    @Before
    public void setUp() throws Exception {
        generatedMap = generateStringMap(1000000);
        generatedList = generateStrings(1000000);
    }

    @Test
    public void testArrayVsSetOnContains() throws Exception {
        List<String> list = new ArrayList<>();
        list.addAll(generatedList);

        Set<String> set = new HashSet<>();
        set.addAll(generatedList);

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
        arrayList.addAll(generatedList);

        List<String> linkedList = new LinkedList<>();
        linkedList.addAll(generatedList);

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
        arrayList.addAll(generatedList);

        List<String> linkedList = new LinkedList<>();
        linkedList.addAll(generatedList);

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

        long arrayListSortTime, linkedListSortTime;
        arrayListSortTime = executeTestCommand(() -> arrayList.sort(String::compareTo), "arrayList.sort takes: ");

        linkedListSortTime = executeTestCommand(() -> linkedList.sort(String::compareTo), "linkedList.sort takes: ");
        assertTrue(linkedListSortTime < arrayListSortTime);
    }

    @Test
    public void testHashMapVsTreeMapOnRemove() throws Exception {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.putAll(generatedMap);

        Map<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(generatedMap);

        executeTestCommand(() -> hashMap.remove("String1000"), "hashMap.remove(\"String1000\") takes: ");
        executeTestCommand(() -> hashMap.remove("String1001"), "hashMap.remove(\"String1001\") takes: ");
        executeTestCommand(() -> hashMap.remove("String1002"), "hashMap.remove(\"String1002\") takes: ");
        executeTestCommand(() -> hashMap.remove("String1002"), "hashMap.remove(\"String1002\") which is already removed takes: ");

        executeTestCommand(() -> treeMap.remove("String1000"), "treeMap.remove(\"String1000\") takes: ");
        executeTestCommand(() -> treeMap.remove("String1001"), "treeMap.remove(\"String1001\") takes: ");
        executeTestCommand(() -> treeMap.remove("String1002"), "treeMap.remove(\"String1002\") takes: ");
        executeTestCommand(() -> treeMap.remove("String1002"), "treeMap.remove(\"String1002\") which is already removed takes: ");
    }

    @Test
    public void testHashMapVsTreeMapOnGet() throws Exception {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.putAll(generatedMap);

        Map<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(generatedMap);

        executeTestCommand(() ->    hashMap.get("String1000"), "treeMap.get(\"String1000\") takes: ");
        executeTestCommand(() ->    hashMap.get("String1001"), "treeMap.get(\"String1001\") takes: ");
        executeTestCommand(() ->    hashMap.get("String1002"), "treeMap.get(\"String1002\") takes: ");

        executeTestCommand(() ->    treeMap.get("String1000"), "treeMap.get(\"String1000\") takes: ");
        executeTestCommand(() ->    treeMap.get("String1001"), "treeMap.get(\"String1001\") takes: ");
        executeTestCommand(() ->    treeMap.get("String1002"), "treeMap.get(\"String1002\") takes: ");
    }

    @Test
    public void testHashMapVsTreeMapOnContainsValue() throws Exception {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.putAll(generatedMap);

        Map<String, String> treeMap = new TreeMap<>();
        treeMap.putAll(generatedMap);

        executeTestCommand(() ->   hashMap.containsValue("String100001"), "hashMap.containsValue(\"String1001\") takes: ");
        executeTestCommand(() ->   treeMap.containsValue("String100001"), "treeMap.containsValue(\"String1001\") takes: ");
    }


    @Test
    public void testHashMapVsTreeMapOnPutAll() throws Exception {

        executeTestCommand(() -> new HashMap<String, String>().putAll(generatedMap), "hashMap.putAll takes : " );

        executeTestCommand(() -> new TreeMap<String, String>().putAll(generatedMap), "treeMap.putAll takes : " );

    }

    private long executeTestCommand(TestCommand command, String message) {
        long end, start, commandTime;
        start= System.nanoTime();

        command.execute();

        end = System.nanoTime();
        commandTime = end - start;
        System.out.println(message + commandTime);
        return commandTime;
    }

   interface TestCommand {
       void execute();
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

    private Map<String,String> generateStringMap(int count) {
        Map<String, String> map = new HashMap<>();
        for (String s : generateStrings(count)) {
            map.put(s, s);
        }
        return map;
    }

    private List<String> generateStrings(int count) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add("String" + i);
        }
        return list;
    }
}
