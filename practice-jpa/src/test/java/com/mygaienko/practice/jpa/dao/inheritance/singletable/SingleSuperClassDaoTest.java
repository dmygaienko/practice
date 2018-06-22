package com.mygaienko.practice.jpa.dao.inheritance.singletable;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mygaienko.practice.jpa.dao.AbstractDaoTest;
import com.mygaienko.practice.jpa.model.inheritance.singletable.SingleSuperClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by enda1n on 25.06.2017.
 */
@DatabaseSetup("/com/mygaienko/practice/jpa/dao/inheritance/singletable/EDaoTest.xml")
public class SingleSuperClassDaoTest extends AbstractDaoTest {

    @Autowired
    private SingleSuperClassDao dao;

    @Test
    public void testFindAll() throws Exception {
        List<SingleSuperClass> all = dao.findAll();
        System.out.println(all);
    }
}