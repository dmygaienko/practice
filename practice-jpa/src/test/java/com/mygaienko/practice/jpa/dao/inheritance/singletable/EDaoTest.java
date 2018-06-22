package com.mygaienko.practice.jpa.dao.inheritance.singletable;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mygaienko.practice.jpa.dao.AbstractDaoTest;
import com.mygaienko.practice.jpa.model.inheritance.singletable.E;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by enda1n on 25.06.2017.
 */
@DatabaseSetup("/com/mygaienko/practice/jpa/dao/inheritance/singletable/EDaoTest.xml")
public class EDaoTest extends AbstractDaoTest {

    @Autowired
    private EDao dao;

    @Test
    public void testFindAll() throws Exception {
        List<E> all = dao.findAll();
        System.out.println(all);
    }
}