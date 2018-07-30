package com.mygaienko.common.jackson;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class JsonValueTest {

    @Test
    public void name() throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(En.E2));
    }
}

enum En {

    E1("v11", "v12"), E2("v21", "v22");

    private final String s1;
    private final String s2;

    En(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @JsonValue
    public String getS1() {
        return s1;
    }

    public String getS2() {
        return s2;
    }
}
