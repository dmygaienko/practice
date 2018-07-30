package com.mygaienko.common.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class JsonCreatorTest {

    @Test
    public void name() throws IOException {
        BeanWithCreator read = new ObjectMapper()
                .readerFor(BeanWithCreator.class)
                .readValue("{\"id\":1,\"theName\":\"My bean\"}");

        System.out.println(read);

    }
}

class BeanWithCreator {
    public int id;
    public String name;

    @JsonCreator
    public BeanWithCreator(
            @JsonProperty("id") int id,
            @JsonProperty("theName") String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "BeanWithCreator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
