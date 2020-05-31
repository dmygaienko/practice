package com.mygaienko.practice.jpa.dao.inheritance.joined;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mygaienko.practice.jpa.dao.AbstractDaoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by enda1n on 25.06.2017.
 */
@DatabaseSetup("/com/mygaienko/practice/jpa/dao/inheritance/joined/GDaoTest.xml")
public class GDaoTest extends AbstractDaoTest {

    @Autowired
    private GDao dao;

    @Test
    public void testFindAll() throws Exception {
        System.out.println(dao.findAll());
    }
}