package com.mygaienko.practice.jpa.dao.inheritance.tableperconcreteclass;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mygaienko.practice.jpa.dao.AbstractDaoTest;
import com.mygaienko.practice.jpa.dao.inheritance.tableperconcreteclasswithunions.SSuperClassDao;
import com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclass.SuperClass;
import com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclasswithunions.SSuperClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by enda1n on 25.06.2017.
 */
@DatabaseSetup("/com/mygaienko/practice/jpa/dao/inheritance/tableperconcreteclass/ADaoTest.xml")
public class SuperClassDaoTest extends AbstractDaoTest {

    @Autowired
    private SuperClassDao dao;

    @Test(expected = IllegalArgumentException.class) //SuperClass not an entity
    public void findAll() throws Exception {
        List<SuperClass> all = dao.findAll();
        System.out.println(all);
    }

}