package com.mygaienko.common.jackson;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class JacksonViewTest {

    @Test
    public void test() throws JsonProcessingException {
        Item item = new Item(1, "item", "owner");

        System.out.println(
                new ObjectMapper()
                        .writerWithView(Views.Public.class)
                        .writeValueAsString(item));

        System.out.println(
                new ObjectMapper()
                        .writerWithView(Views.Internal.class)
                        .writeValueAsString(item));
    }
}

class Views {
    static class Public {}
    static class Internal extends Public {}
}

class Item {

    @JsonView(Views.Public.class)
    public int id;

    @JsonView(Views.Public.class)
    public String itemName;

    @JsonView(Views.Internal.class)
    public String ownerName;

    public Item(int id, String itemName, String ownerName) {
        this.id = id;
        this.itemName = itemName;
        this.ownerName = ownerName;
    }
}
