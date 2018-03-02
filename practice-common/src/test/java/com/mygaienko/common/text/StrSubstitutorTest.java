package com.mygaienko.common.text;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StrSubstitutorTest {

    @Test
    public void test() {
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("greeting", "Good morning");
        valueMap.put("name", "Dmytro");

        String expected = "Good morning, Dmytro. My pleasure to see you.";
        assertEquals(expected, StrSubstitutor.replace("${greeting}, ${name}. My pleasure to see you.", valueMap));
    }

    @Test
    public void testWithOneValue() {
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("greeting", "Good morning");

        String expected = "Good morning, ${name}. My pleasure to see you.";
        assertEquals(expected, StrSubstitutor.replace("${greeting}, ${name}. My pleasure to see you.", valueMap));
    }

    @Test
    public void testWithNull() {
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("greeting", "Good morning");
        valueMap.put("name", null);

        String expected = "Good morning, ${name}. My pleasure to see you.";
        assertEquals(expected, StrSubstitutor.replace("${greeting}, ${name}. My pleasure to see you.", valueMap));
    }
}
