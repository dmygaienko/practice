package com.mygaienko.common.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class JackonIgnoreTypeTest {

    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(new User(1, 7777, new User.Name("F", "L"))));
    }
}

class User {
    public int id;
    
    @JsonIgnore
    public int id2;
    
    public Name name;

    public User(int id, int id2, Name name) {
        this.id = id;
        this.id2 = id2;
        this.name = name;
    }

    @JsonIgnoreType
    public static class Name {
        public String firstName;
        public String lastName;

        public Name(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}
