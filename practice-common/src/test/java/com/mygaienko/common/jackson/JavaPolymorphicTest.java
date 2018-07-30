package com.mygaienko.common.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

public class JavaPolymorphicTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void test() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(new Dog("lord", 0.23d)));
    }

    @Test
    public void testDeserialize() throws IOException {
        Animal animal = objectMapper.readValue("{\"type\":\"dog\",\"name\":\"lord\",\"barkVolume\":0.23}", Animal.class);
        assertTrue(animal instanceof Dog);
    }


}


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Dog.class, name = "dog"),
        @JsonSubTypes.Type(value = Cat.class, name = "cat")
})
class Animal {
    public String name;

    public Animal(String name) {
        this.name = name;
    }

    public Animal() {
    }
}

@JsonTypeName("dog")
class Dog extends Animal {
    public double barkVolume;

    public Dog() {
    }

    public Dog(String name, double barkVolume) {
        super(name);
        this.barkVolume = barkVolume;
    }
}

@JsonTypeName("cat")
class Cat extends Animal {
    boolean likesCream;
    public int lives;

    public Cat() {
    }

    public Cat(String name, boolean likesCream, int lives) {
        super(name);
        this.likesCream = likesCream;
        this.lives = lives;
    }
}
