package com.mygaienko.practice.elasticsearch.dao;

import com.mygaienko.practice.elasticsearch.Application;
import com.mygaienko.practice.elasticsearch.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by enda1n on 24.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    public void test() {
        Book book = new Book();
        book.setName("name");
        book.setPrice("price");
        repository.save(book);
        repository.findByName("name");
    }
}