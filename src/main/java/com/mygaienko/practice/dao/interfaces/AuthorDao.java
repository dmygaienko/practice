package com.mygaienko.practice.dao.interfaces;

import com.mygaienko.practice.model.Author;

import java.util.List;

/**
 * Created by enda1n on 05.12.2015.
 */
public interface AuthorDao {

    Author get(Long id);

    Author getByBeanANameAndBeanBCode(String name, String beanAName, String beanBCode);

    List<Author> getByBeanANameLike(String name);

    List<Author> getByNameLike(String name);

    List<Author> getByBeanAId(long l);
}
