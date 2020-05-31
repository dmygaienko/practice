package com.mygaienko.common.algorithms.interv.commonrelatives;

import org.junit.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

public class RelativeUtilTest {

    @Test
    public void isRelative() {

        Human first = Human.builder()
                .name("f1")
                .parents(asList(
                        Human.builder()
                                .name("f12")
                                .parents(singletonList(Human.builder()
                                        .name("f121")
                                        .build()))
                                .build(),
                        Human.builder()
                                .name("f13")
                                .build()
                ))
                .build();

        Human second = Human.builder()
                .name("f2")
                .parents(asList(
                        Human.builder()
                                .name("f22")
                                .parents(singletonList(Human.builder()
                                        .name("f221")
                                        .parents(singletonList(Human.builder()
                                                .name("f13")
                                                .build()))
                                        .build()))
                                .build(),
                        Human.builder()
                                .name("f23")
                                .build()
                ))
                .build();

        System.out.println(RelativeUtil.isRelative(first, second));
    }
}