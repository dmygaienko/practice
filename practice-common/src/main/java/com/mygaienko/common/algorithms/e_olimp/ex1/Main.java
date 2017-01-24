package com.mygaienko.common.algorithms.e_olimp.ex1;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by enda1n on 25.01.2017.
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();

        PrintWriter out = new PrintWriter(System.out);
        out.println(simple(a));
        out.flush();
    }

    public static String simple(int a) {

        if (a < 10 || a > 99) {
            return "";
        }

        int decimal = a / 10;
        return ( (decimal > 0 ? (decimal + " ") : "")  + a % 10);
    }
}