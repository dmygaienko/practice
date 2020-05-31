package com.mygaienko.common.jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JacksonAnyGetterSetterTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void test() throws JsonProcessingException {
        E e = new E();
        e.setE1("v1");
        e.addEntry("e2", "v2");
        e.addEntry("e3", "v3");
        e.addEntry("e4", "v4");

        System.out.println(objectMapper.writeValueAsString(e));
    }

    @Test
    public void testAnySetter() throws IOException {
        E e = objectMapper.readValue("{\"e11111\":\"v1\",\"e2\":\"v2\",\"e3\":\"v3\",\"e4\":\"v4\"}", E.class);
        System.out.println(e);
    }
}

class E {

    private String e1;

    private Map<String, String> map = new HashMap<>();

    @JsonGetter("e11111")
    public String getE1() {
        return e1;
    }

    @JsonSetter("e11111")
    public void setE1(String e1) {
        this.e1 = e1;
    }

    @JsonAnyGetter
    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @JsonAnySetter
    public void addEntry(String key, String value) {
        map.put(key, value);
    }

    @Override
    public String toString() {
        return "E{" +
                "e1='" + e1 + '\'' +
                ", map=" + map +
                '}';
    }
}
