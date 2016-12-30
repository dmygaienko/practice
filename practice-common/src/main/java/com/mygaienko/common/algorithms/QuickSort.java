package com.mygaienko.common.algorithms;

/**
 * Created by dmygaenko on 30/12/2016.
 */
public class QuickSort {

    public void sort(int[] array, int p, int r) {

        if (p < r) {
            int q = partition(array, p, r);

            sort(array, p, q - 1);
            sort(array, p, q + 1);
        }
    }

    /*  Partition (А, р, г)
     1 х = А[г]
     2 i = р — 1
     3 for j = р to г — 1
     4 if A[j] < х
     5 i = i + 1
     6 Обменять А [г] и A[j]
     7 Обменять А [г + 1] и А [г]
     8 return г + 1*/
    private int partition(int[] array, int p, int r) {
        return 0;
    };
}
