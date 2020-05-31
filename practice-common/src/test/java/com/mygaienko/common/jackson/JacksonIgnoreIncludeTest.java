package com.mygaienko.common.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class JacksonIgnoreIncludeTest {

    @Test
    public void testIncludingNonNullProperties() throws JsonProcessingException {
        B b = new B("v1", null, "v3", null);
        String s = new ObjectMapper().writeValueAsString(b);
        System.out.println(s);
    }

    @Test
    public void testIgnoringNullProperties() throws IOException {
        B b = new ObjectMapper().readValue("{\"a1\":\"v1\",\"b3\":\"v3\", \"b5\":\"v5\"}", B.class);
        System.out.println(b);
    }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class A {

    private String a1;
    private String a2;

    public A() {
    }

    public A(String a1, String a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }
}

class B extends A {

    private String b3;
    private String b4;

    public B() {
    }

    public B(String a1, String a2, String b3, String b4) {
        super(a1, a2);
        this.b3 = b3;
        this.b4 = b4;
    }

    public String getB3() {
        return b3;
    }

    public void setB3(String b3) {
        this.b3 = b3;
    }

    public String getB4() {
        return b4;
    }

    public void setB4(String b4) {
        this.b4 = b4;
    }
}

