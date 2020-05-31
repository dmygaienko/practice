package com.mygaienko.patterns.creational.builder;

/**
 * Created by dmygaenko on 23/03/2016.
 */
public class Main {

    public static void main(String[] args) {
        Bean bean = new Bean.BeanBuilder()
                .setStringA("A")
                .setStringB("B")
                .setStringC("C")
                .setStringD("D")
                .setStringE("E")
                .setStringF("F")
                .setStringG("G")
                .setStringH("H")
                .build();
        System.out.println(bean);
    }
}
