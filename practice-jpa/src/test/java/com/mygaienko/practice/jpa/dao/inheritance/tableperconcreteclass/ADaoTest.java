package com.mygaienko.practice.jpa.dao.inheritance.tableperconcreteclass;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.mygaienko.practice.jpa.dao.AbstractDaoTest;
import com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclass.A;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by enda1n on 25.06.2017.
 */
@DatabaseSetup("/com/mygaienko/practice/jpa/dao/inheritance/tableperconcreteclass/ADaoTest.xml")
public class ADaoTest extends AbstractDaoTest {

    @Autowired
    private ADao dao;

    @Test
    public void findAll() throws Exception {
        List<A> all = dao.findAll();
        System.out.println(all);
    }

}