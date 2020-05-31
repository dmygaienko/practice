package com.mygaienko.common.algorithms;

/**
 * Created by dmygaenko on 30/12/2016.
 */
public class QuickSort {

    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] a, int p, int r) {
        if (p < r) {
            int q = partition(a, p, r);

            sort(a, p, q - 1);
            sort(a, q + 1, r);
        }
    }

    /*
     p.199 Кормен

     a[p.. i] < x (pivot)

     a[j.. r] >= x (pivot)

   */
    private static int partition(int[] a, int p, int r) {

        int x = a[r];
        int i = p - 1;

        for (int j = p; j < r; j++) {
            if (a[j] <= x) {
                i = i + 1;

                int tempI = a[i];
                a[i] = a[j];
                a[j] = tempI;
            }
        }

        //ставим опорный елемент посредине массива и возвращаем его индекс
        int pivotI = i + 1;

        int temp = a[pivotI];
        a[pivotI] = a[r];
        a[r] = temp;

        return pivotI;
    }

}
