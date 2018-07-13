package com.mygaienko.patterns.creational.factorymethod;

public class FactoryService2 {

    public <T> Strategy<T> get(EnumType<T> type) {
        if (EnumType.A.equals(type)) {
            return (Strategy<T>) new AStrategy();
        } else if (EnumType.B.equals(type)) {
            return (Strategy<T>) new BStrategy();
        } else {
            throw new IllegalArgumentException();
        }
    }
}


interface Strategy<T> {

    String doService(T t);
}

class AStrategy implements Strategy<AType> {

    @Override
    public String doService(AType aType) {
        return "AStrategy";
    }
}

class BStrategy implements Strategy<BType> {

    @Override
    public String doService(BType bType) {
        return "BStrategy";
    }
}

class EnumType<T> {

    public static final EnumType<AType> A = new EnumType<>(AType.class);
    public static final EnumType<BType> B = new EnumType<>(BType.class);

    private EnumType(Class<T> clazz){

    }
}

class AType { }

class BType { }

//first simple step
//second simple step
//third simple step