package com.mygaienko.practice.jpa.dao.inheritance.tableperconcreteclasswithunions;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mygaienko.practice.jpa.dao.AbstractDaoTest;
import com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclasswithunions.SSuperClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by enda1n on 25.06.2017.
 */
@DatabaseSetup("/com/mygaienko/practice/jpa/dao/inheritance/tableperconcreteclasswithunions/SuperClassDaoTest.xml")
public class SuperClassDaoTest extends AbstractDaoTest {

    @Autowired
    private SSuperClassDao dao;

    @Test
    public void findAll() throws Exception {
        List<SSuperClass> all = dao.findAll();
        System.out.println(all);
    }

}