package com.mygaienko.common.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by dmygaenko on 22/03/2017.
 */
public class TestClass {


    public static <T extends List> T getSome(){
        return null;
    }

    public static <T extends ArrayList> T getSome4(){
        return null;
    }


    public static <T extends String> T getSome3(){
        return null;
    }

    public static List getSome2(){
        return null;
    }

    public static void main(String[] args) {
        //TestClass.<String>getSome();

        String s1 = getSome();
        //String s11 = getSome4();
        //String s111 = new Object();
        //String s11 = getSome3();

        //String s2 = (String) getSome();
        //String s3 = new Object();
        Number n1 = getSome();
        //String s2 = getSome2();

    }
}
