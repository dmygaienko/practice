package com.mygaienko.common.algorithms.e_olimp.ex7;

import org.junit.Test;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/*  Римские числа

        Посчитать сумму двух натуральных чисел A и B, записанных в римской системе счисления. Ответ также записать в римской системе счисления.

        M = 1000, D = 500, C = 100, L = 50, X = 10, V = 5, I = 1. Все числа – не превышают 2000.
*/

public class Main {

    private static LinkedHashMap<Character, Integer> weights = new LinkedHashMap<>();

    static {
        weights.put('M', 1000);
        weights.put('D', 500);
        weights.put('C', 100);
        weights.put('L', 50);
        weights.put('X', 10);
        weights.put('V', 5);
        weights.put('I', 1);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();

        PrintWriter out = new PrintWriter(System.out);
        out.println(execute(s));
        out.flush();
    }

    public static String execute(String s) {
        String[] splitted = s.split("\\+");
        int sum = getInt(splitted[0]) + getInt(splitted[1]);
        StringBuilder stringBuilder = new StringBuilder();
        toRomanNumerals(sum, stringBuilder, weights.entrySet().iterator(), null, null);
        return stringBuilder.toString();
    }

    private static void toRomanNumerals(int sum, StringBuilder builder, Iterator<Map.Entry<Character, Integer>> iterator,
                                        Map.Entry<Character, Integer> prevEntry, Map.Entry<Character, Integer> prevPrevEntry) {
        if (!iterator.hasNext()) return;

        Map.Entry<Character, Integer> next = iterator.next();

        int fraction = sum / next.getValue();
        int remainder = sum % next.getValue();

        if (fraction == 4 && prevEntry != null) {
            Character key = prevEntry.getKey();
            int lastIndex = builder.length() - 1;
            if (lastIndex >= 0 && key == builder.charAt(lastIndex)) {
                key = prevPrevEntry.getKey();
                builder.deleteCharAt(lastIndex);
            }
            builder.append(next.getKey());
            builder.append(key);

        } else if (fraction > 0) {
            for (int i = 0; i < fraction; i++) {
                builder.append(next.getKey());
            }
        }

        if (remainder > 0) {
            toRomanNumerals(remainder, builder, iterator, next, prevEntry);
        }

    }

    public static int getInt(String s) {
        int result = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int weight = weights.get(chars[i]);
            int nextWeight = i < chars.length - 1 ? weights.get(chars[i + 1]) : 0;

            if (weight >= nextWeight) {
                result += weight;
            } else {
                result -= weight;
            }
        }
        return result;
    }

    @Test
    public void testGetInt() {
        assertEquals(3, Main.getInt("III"));
        assertEquals(1600, Main.getInt("MDC"));
    }

    @Test
    public void testExecute7() {
        assertEquals("VII", Main.execute("III+IV"));
    }

    @Test
    public void testExecute19() {
        assertEquals("XVIIII", Main.execute("III+XVI"));
    }

    @Test
    public void testExecute4() {
        assertEquals("IV", Main.execute("II+II"));
    }
}
