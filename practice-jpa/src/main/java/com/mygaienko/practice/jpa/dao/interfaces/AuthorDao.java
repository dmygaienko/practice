package com.mygaienko.practice.jpa.dao.interfaces;

import com.mygaienko.practice.jpa.model.Author;
import com.mygaienko.practice.jpa.model.BeanA;

import java.util.List;

/**
 * Created by enda1n on 05.12.2015.
 */
public interface AuthorDao {

    Author get(Long id);

    Author getByBeanANameAndBeanBCode(String name, String beanAName, String beanBCode);

    List<BeanA> getJoinOnNonPrimaryColumn(String name);

    List<Author> getByBeanANameLike(String name);

    List<Author> getByNameLike(String name);

    List<Author> getByBeanAId(long l);

    List<Author> getSubQuery(String name);
}
