package com.mygaienko.common.pool;

import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

/**
 * Comment
 *
 * Created by enda1n on 30.08.2017.
 */
public class GenericObjectPoolTest {

    @Test
    public void simpleTest() throws Exception {

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(2);
        GenericObjectPool<StubObject> pool = new GenericObjectPool<>(new StubPooledObjectFactory(), config);

        StubObject stubObject = pool.borrowObject();
        System.out.println(stubObject.getNumber());

        StubObject stubObject1 = pool.borrowObject();
        System.out.println(stubObject1.getNumber());

        pool.returnObject(stubObject1);

        StubObject stubObject2 = pool.borrowObject();
        System.out.println(stubObject2.getNumber());
    }

    @Test
    public void testEviction() throws Exception {

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(3);
        config.setMinIdle(1);
        config.setTimeBetweenEvictionRunsMillis(50);
        config.setMinEvictableIdleTimeMillis(50);
        GenericObjectPool<StubObject> pool = new GenericObjectPool<>(new StubPooledObjectFactory(), config);

        printMetrics(pool, "Before");

        System.out.println("Object is borrowing 1");
        StubObject stubObject1 = pool.borrowObject();
        System.out.println("Number is " + stubObject1.getNumber());

        System.out.println("Object is borrowing 2");
        StubObject stubObject2 = pool.borrowObject();
        System.out.println("Number is " + stubObject2.getNumber());

        System.out.println("Object is borrowing 3");
        StubObject stubObject3 = pool.borrowObject();
        System.out.println("Number is " + stubObject3.getNumber());

        printMetrics(pool, "Between");

        pool.returnObject(stubObject1);
        pool.returnObject(stubObject2);
        pool.returnObject(stubObject3);

        System.out.println("Object is borrowing 4");
        StubObject stubObject4 = pool.borrowObject();
        System.out.println("Number is " + stubObject4.getNumber());

        printMetrics(pool, "After");

        Thread.sleep(100);

        printMetrics(pool, "After eviction");
    }

    private void printMetrics(GenericObjectPool<StubObject> pool, String stage) {
        System.out.println(stage + ": Number of idle objects: " + pool.getNumIdle());
        System.out.println(stage + ": Number of active objects: " + pool.getNumActive());
    }

    @Test
    public void testAbandoned() throws Exception {

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(3);
        config.setMinIdle(1);
        AbandonedConfig abandonedConfig = new AbandonedConfig();
        abandonedConfig.setRemoveAbandonedTimeout(2);
        abandonedConfig.setRemoveAbandonedOnBorrow(true);
        abandonedConfig.setRemoveAbandonedOnMaintenance(true);
        GenericObjectPool<StubObject> pool = new GenericObjectPool<>(new StubPooledObjectFactory(), config, abandonedConfig);

        printMetrics(pool, "Before");

        System.out.println("Object is borrowing 1");
        StubObject stubObject1 = pool.borrowObject();
        System.out.println("Number is " + stubObject1.getNumber());

        System.out.println("Object is borrowing 2");
        StubObject stubObject2 = pool.borrowObject();
        System.out.println("Number is " + stubObject2.getNumber());

        printMetrics(pool, "After");

        Thread.sleep(4000);
        pool.borrowObject();

        System.out.println(pool.listAllObjects());

        printMetrics(pool, "After sleep");
    }

}
