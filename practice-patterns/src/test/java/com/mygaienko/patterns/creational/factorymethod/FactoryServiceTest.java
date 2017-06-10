package com.mygaienko.patterns.creational.factorymethod;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by enda1n on 08.06.2017.
 */
public class FactoryServiceTest {

    @Test
    public void getService() throws Exception {
        Service<Entity1> service = FactoryService.getService(ServiceEnum.ENUM1);
        service.doService(new Entity1());
    }

}