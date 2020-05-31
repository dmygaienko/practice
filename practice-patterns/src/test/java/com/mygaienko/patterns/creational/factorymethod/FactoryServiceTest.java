package com.mygaienko.patterns.creational.factorymethod;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by enda1n on 08.06.2017.
 */
public class FactoryServiceTest {

    @Test
    public void getService() throws Exception {
        Service<Entity1> service1 = FactoryService.getService(ServiceEnum.ENUM1);
        service1.doService(new Entity1());

        Service<Entity2> service2 = FactoryService.getService(ServiceEnum.ENUM2);
        service2.doService(new Entity2());
    }

}