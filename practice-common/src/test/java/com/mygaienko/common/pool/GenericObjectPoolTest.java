package com.mygaienko.common.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

/**
 * Created by enda1n on 30.08.2017.
 */
public class GenericObjectPoolTest {

    @Test
    public void name() throws Exception {

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

    private class StubPooledObjectFactory extends BasePooledObjectFactory<StubObject> {

        @Override
        public StubObject create() throws Exception {
            return new StubObject();
        }

        @Override
        public PooledObject<StubObject> wrap(StubObject obj) {
            return new DefaultPooledObject<>(obj);
        }

    }

    private class StubObject {
        private long number;

        public StubObject() {
            this.number = System.currentTimeMillis();
        }

        public long getNumber() {
            return number;
        }
    }
}
