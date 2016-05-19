package com.mygaienko.practice.elasticsearch.dao;

import com.mygaienko.practice.elasticsearch.Application;
import com.mygaienko.practice.elasticsearch.model.Book;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        saveBooksBatch();
        repository.findByName("name9999");
    }

    @Test
    public void testFindByNameLike() {
        saveBooksBatch();
        repository.findByNameLike("name9988", new PageRequest(0, 50));
    }

    @Test
    public void testFindByNameAndPriceAndValueLike() {
        saveBooksBatch();
        repository.findByNameAndPriceAndValue("name9988", "price9988", "value9988");

    }
    @Test
    public void testFindByTags() {
        saveBooksBatch();
        List<String> tags = new ArrayList<String>();
        tags.add("tag1");
        tags.add("tag5");
        repository.findByTags(tags, new PageRequest(0, 50));

    }

    private void saveBooksBatch() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 100000; i ++) {
            Book book = new Book();
            book.setTags(generateTags());
            book.setId(String.valueOf(i));
            book.setName("name" + i);
            book.setPrice("price" + i);
            book.setValue("value" + i);
            books.add(book);
        }
        repository.save(books);
    }

    private List<String> generateTags() {
        List<String> tags = new ArrayList<String>();
        for (int i = 0; i < 3; i ++) {
            tags.add("tag" + new Random().nextInt(100));
        }
        return tags;
    }
}