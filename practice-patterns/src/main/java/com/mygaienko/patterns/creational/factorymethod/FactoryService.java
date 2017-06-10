package com.mygaienko.patterns.creational.factorymethod;

/**
 * Created by enda1n on 08.06.2017.
 */
public class FactoryService {

    public static Service getService(ServiceEnum serviceEnum) {
        Service service = null;
        switch (serviceEnum) {
           case ENUM1: service = new Service1(); break;
           case ENUM2: service = new Service2(); break;
        }
        return service;
    }
}

enum ServiceEnum {
    ENUM1, ENUM2
}

interface Service<T extends AbstractEntity> {
    void doService(T t);
}

class Service1 implements Service<Entity1> {
    @Override
    public void doService(Entity1 entity1) {
        System.out.println(entity1);
    }
}

class Service2 implements Service<Entity2> {
    @Override
    public void doService(Entity2 entity2) {
        System.out.println(entity2);
    }
}

class AbstractEntity {
}

class Entity1 extends AbstractEntity {
}

class Entity2 extends AbstractEntity {
}