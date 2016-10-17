package com.mygaienko.common.nio;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by enda1n on 18.10.2016.
 */
public class FlatDatabaseTest {

    private FlatDatabase database;

    @Before
    public void setUp() throws Exception {
        database = new FlatDatabase("testFlatdb.db");
    }

    @Test
    public void testAppendFlatDB() throws Exception {
        database.append("2", "descr2", 2, 200);
        database.append("3", "descr3", 3, 300);
    }

    @Test
    public void testSelectFlatDB() throws Exception {
        System.out.println(database.select(1));
    }

    @Test
    public void testFlatDB() throws Exception {
        System.out.println(database.numRecs());
    }

    @After
    public void tearDown() throws Exception {
        database.close();
    }

}
