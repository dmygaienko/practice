package com.mygaienko.practice.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by enda1n on 24.01.2016.
 */
@Document(indexName = "book", type = "book", shards = 1, replicas = 0, refreshInterval = "-1")
public class Book {

    @Id
    private Long id;
    private String name;
    private String price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
