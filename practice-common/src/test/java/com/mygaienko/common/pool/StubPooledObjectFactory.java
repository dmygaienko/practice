package com.mygaienko.common.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by enda1n on 31.08.2017.
 */
public class StubPooledObjectFactory extends BasePooledObjectFactory<StubObject> {

    @Override
    public StubObject create() throws Exception {
        System.out.println("Object is creating at time " + System.currentTimeMillis());
        return new StubObject();
    }

    @Override
    public PooledObject<StubObject> wrap(StubObject obj) {
        System.out.println("Object is wrapping at time " + System.currentTimeMillis());
        return new DefaultPooledObject<>(obj);
    }

}
