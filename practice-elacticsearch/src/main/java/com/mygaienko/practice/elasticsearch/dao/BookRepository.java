package com.mygaienko.practice.elasticsearch.dao;

import com.mygaienko.practice.elasticsearch.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by enda1n on 24.01.2016.
 */
public interface BookRepository extends ElasticsearchRepository<Book, String> {

    List<Book> findByNameAndPrice(String name, Integer price);

    List<Book> findByNameOrPrice(String name, Integer price);

    List<Book> findByName(String name);

    Page<Book> findByName(String name, Pageable page);

    Page<Book> findByNameNot(String name,Pageable page);

    Page<Book> findByPriceBetween(int price,Pageable page);

    Page<Book> findByNameLike(String name,Pageable page);
}
