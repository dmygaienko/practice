package com.mygaienko.common.algorithms.e_olimp.ex4;

import org.junit.Test;

import java.io.PrintWriter;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 *
 * Входные данные

 Шесть чисел x1, y1, r1, x2, y2, r2, где x1, y1, x2, y2 - координаты центров окружностей, а r1, r2 – их радиусы. Все числа - действительные, не превышают по модулю 109, заданы не более чем с тремя знаками после запятой.

 Выходные данные

 Количество точек пересечения. Если точек пересечения бесконечно много, то вывести -1.

 * Created by enda1n on 05.02.2017.
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double x1 = in.nextDouble();
        double y1 = in.nextDouble();
        double r1 = in.nextDouble();
        double x2 = in.nextDouble();
        double y2 = in.nextDouble();
        double r2 = in.nextDouble();

        PrintWriter out = new PrintWriter(System.out);
        out.println(execute(x1, y1, r1, x2, y2, r2));
        out.flush();
    }

    public static int execute(double x1, double y1, double r1, double x2, double y2, double r2) {
        if (x1 == x2 && y1 == y2){
            if (r1 == r2) {
                return -1;
            } else {
                return 0;
            }
        }

        int intersectionPoints;
        double distance = Math.pow(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2), 1/2d);
        if (distance < r1 + r2 &&
                ((r1 < distance + r2 && r2 < r1 + distance) ||
                (r2 < distance + r1 && r1 < r2 + distance))) {
            intersectionPoints = 2;
        } else if (distance == r1 + r2 || r1 == distance + r2 || r2 == distance + r1) {
            intersectionPoints = 1;
        } else {
            intersectionPoints = 0;
        }
        return intersectionPoints;
    }

    @Test
    public void testExecute() {
        assertEquals(2, Main.execute(0, 0, 5, 5, 0, 1));
        assertEquals(0, Main.execute(0, 0, 5, 0, 0, 1));
    }

    @Test
    public void testExecute0WithSameXY() {
        assertEquals(0, Main.execute(0, 0, 5, 0, 0, 1));
    }

    @Test
    public void testExecute0WithDiffXY() {
        assertEquals(0, Main.execute(0, 0, 5, 0, 1, 1));
    }

    @Test
    public void testExecute1WithDiffXY() {
        assertEquals(1, Main.execute(0, 0, 5, 0, 4, 1));
    }

    @Test
    public void testExecute2WithDiffXY() {
        assertEquals(2, Main.execute(0, 0, 5, 0, 4, 1.5d));
    }

    @Test
    public void testExecute0WithDiffXYOut() {
        assertEquals(0, Main.execute(0, 0, 5, 0, 10, 1));
    }

}
