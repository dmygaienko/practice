package com.mygaienko.common.collection;

import org.junit.Test;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Modifier;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Created by enda1n on 14.05.2017.
 */
public class EnumMapTest {

    @Test
    //asd
    public void testConstructor() throws Exception {
        EnumMap<Status, String> enumMap = getEnumMap();
        System.out.println(enumMap);
    }

    @Test
    public void testConstructor1() throws Exception {
        EnumMap<Status, String> initialMap = getEnumMap();
        EnumMap<Status, String> enumMap = new EnumMap<>(initialMap);
        enumMap.remove(Status.LATEST);
        System.out.println(initialMap);
        System.out.println(enumMap);
    }

    @Test
    public void testConstructor2() throws Exception {
        HashMap<Status, String> initialMap = getHashMap();
        EnumMap<Status, String> enumMap = new EnumMap<>(initialMap);
        enumMap.remove(Status.LATEST);
        System.out.println(initialMap);
        System.out.println(enumMap);
    }

    @Test
    public void testConstructor3() throws Exception {
        HashMap<Status, String> initialMap = getHashMap();
        EnumMap<Status, String> enumMap = getEnumMap();
        System.out.println("equals: " + enumMap.equals(initialMap));
    }

    @Test
    public void printDeclaredMethods() throws Exception {
        Stream.of(EnumMap.class.getDeclaredMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers()))
                .forEach(System.out::println);
    }

    private EnumMap<Status, String> getEnumMap() {
        EnumMap<Status, String> enumMap = new EnumMap<>(Status.class);
        enumMap.put(Status.LATEST, "1");
        enumMap.put(Status.SUPERSEDED, "2");
        enumMap.put(Status.PENDING, "3");
        return enumMap;
    }

    private HashMap<Status, String> getHashMap() {
        HashMap<Status, String> map = new HashMap<>();
        map.put(Status.LATEST, "1");
        map.put(Status.SUPERSEDED, "2");
        map.put(Status.PENDING, "3");
        return map;
    }

}

enum Status {
    LATEST, SUPERSEDED, PENDING
}
