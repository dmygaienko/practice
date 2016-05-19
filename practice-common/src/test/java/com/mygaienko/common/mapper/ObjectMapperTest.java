package com.mygaienko.common.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by dmygaenko on 19/05/2016.
 */
public class ObjectMapperTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String aJson = "{\"s1\":\"s1 value\"," +
            "\"stringArray\":[\"sA1\",\"sA2\"]," +
            "\"stringList\":[\"sL1\",\"sL2\"]," +
            "\"stringMap\":" +
                        "{\"key1\":\"value1\"," +
                        "\"key2\":\"value2\"}}";
    private final A a = new A();

    @Before
    public void init() {
        a.setS1("s1 value");
        a.setStringArray(new String[]{"sA1", "sA2"});
        a.setStringList(Arrays.asList("sL1", "sL2"));
        Map<String,String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        a.setStringMap(map);
    }

    @Test
    public void testWriteValue() throws JsonProcessingException {
        String writtenString = objectMapper.writeValueAsString(a);
        System.out.println(writtenString);
        assertEquals(aJson, writtenString);
    }

    @Test
    public void testReadValue() throws IOException {
        A aRead = objectMapper.readValue(aJson, A.class);
        System.out.println(aRead);
        assertEquals(a, aRead);
    }

    private static class A {
        private String s1;
        private String[] stringArray;
        private List<String> stringList;
        private Map<String, String> stringMap;

        public A(String s1, String[] stringArray, List<String> stringList, Map<String, String> stringMap) {
            this.s1 = s1;
            this.stringArray = stringArray;
            this.stringList = stringList;
            this.stringMap = stringMap;
        }

        public A() {
        }

        public String getS1() {
            return s1;
        }

        public void setS1(String s1) {
            this.s1 = s1;
        }

        public String[] getStringArray() {
            return stringArray;
        }

        public void setStringArray(String[] stringArray) {
            this.stringArray = stringArray;
        }

        public List<String> getStringList() {
            return stringList;
        }

        public void setStringList(List<String> stringList) {
            this.stringList = stringList;
        }

        public Map<String, String> getStringMap() {
            return stringMap;
        }

        public void setStringMap(Map<String, String> stringMap) {
            this.stringMap = stringMap;
        }

        @Override
        public String toString() {
            return "A{" +
                    "s1='" + s1 + '\'' +
                    ", stringArray=" + Arrays.toString(stringArray) +
                    ", stringList=" + stringList +
                    ", stringMap=" + stringMap +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            A a = (A) o;

            if (s1 != null ? !s1.equals(a.s1) : a.s1 != null) return false;
            // Probably incorrect - comparing Object[] arrays with Arrays.equals
            if (!Arrays.equals(stringArray, a.stringArray)) return false;
            if (stringList != null ? !stringList.equals(a.stringList) : a.stringList != null) return false;
            return !(stringMap != null ? !stringMap.equals(a.stringMap) : a.stringMap != null);

        }

        @Override
        public int hashCode() {
            int result = s1 != null ? s1.hashCode() : 0;
            result = 31 * result + Arrays.hashCode(stringArray);
            result = 31 * result + (stringList != null ? stringList.hashCode() : 0);
            result = 31 * result + (stringMap != null ? stringMap.hashCode() : 0);
            return result;
        }
    }
}
