package com.mygaienko.common.algorithms.e_olimp.ex5;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by dmygaenko on 07/02/2017.
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();

        PrintWriter out = new PrintWriter(System.out);
        out.println(execute(a));
        out.flush();
    }

    public static int execute(int n) {
        if (n < 1 || n > 50) {
            return -1;
        }

        Map<Integer, Integer> ways = new HashMap<>();

        int k = n;
        int a = 1;
        int b = 1;

        if (k / a == b && k % a == 0) {
            ways.putIfAbsent(a < b ? a : b, a > b ? b : a);
        }

        return 0;
    }
}
