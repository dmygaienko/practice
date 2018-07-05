package com.mygaienko.patterns.creational.factorymethod;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FactoryService2Test {

    private FactoryService2 factoryService = new FactoryService2();

    @Test
    public void test() {
        assertEquals("AStrategy", factoryService.get(EnumType.A).doService(new AType()));
    }
}