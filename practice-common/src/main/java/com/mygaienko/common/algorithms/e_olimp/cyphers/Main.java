package com.mygaienko.common.algorithms.e_olimp.cyphers;

/**
 * Created by enda1n on 30.01.2017.
 */
public class Main {
    
    public static String getCyphers(long l) {
        return getCyphers(new StringBuilder(), l).toString().trim();
    }

    private static StringBuilder getCyphers(StringBuilder stringBuilder, long l) {
        long quotient = l/10;
        long remainder = l % 10;

        stringBuilder.append(remainder);
        stringBuilder.append(" ");

        if (quotient > 0) {
            return getCyphers(stringBuilder, quotient);
        } else {
            return stringBuilder.reverse();
        }
    }


}
