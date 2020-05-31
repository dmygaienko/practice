package com.mygaienko.common.algorithms.e_olimp.ex2;

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
        out.println(execute(a));
        out.flush();
    }

    public static String execute(int a) {
        double max = 2 * Math.pow(10, 9);
        if (a < 0 || a > max) {
            return "";
        }

        return divide(a, 0);
    }

    private static String divide(int a, int length) {
        int fraction = a / 10;
        if (fraction == 0)
        {
            return "" + ++length;
        }
        else {
            return divide(fraction, ++length);
        }
    }
}
