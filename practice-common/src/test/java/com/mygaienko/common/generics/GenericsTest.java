package com.mygaienko.common.generics;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by dmygaenko on 19/09/2016.
 */
public class GenericsTest {

    public List<String> names;

    @Test
    public void test() throws NoSuchFieldException {
        Field field = GenericsTest.class.getDeclaredField("names");

        ParameterizedType type = (ParameterizedType) field.getGenericType();

        // List
        System.out.println(type.getRawType());

        // Just String in this case
        for (Type typeArgument : type.getActualTypeArguments()) {
            System.out.println("  " + typeArgument);
        }


    }


}
