package com.mygaienko.common.jackson;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class JacksonUnwrappedTest {

    @Test
    public void name() throws JsonProcessingException {
        UnwrappedUser unwrappedUser = new UnwrappedUser();
        unwrappedUser.id = 123;
        unwrappedUser.name = new UnwrappedUser.Name("F", "L");

        System.out.println(new ObjectMapper().writeValueAsString(unwrappedUser));
    }
}

class UnwrappedUser {
    public int id;

    @JsonUnwrapped
    public Name name;

    public static class Name {
        public String firstName;
        public String lastName;

        public Name(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}