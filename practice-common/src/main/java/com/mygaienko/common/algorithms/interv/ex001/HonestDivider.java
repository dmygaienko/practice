package com.mygaienko.common.algorithms.interv.ex001;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by dmygaenko on 01/02/2017.
 */
public class HonestDivider {
    
    public static List<Integer> divide(int expense, int qty) {
        int fraction = expense / qty;
        int remainder = expense % qty;

        List<Integer> expenses = new ArrayList<>();
        for (int i = 0; i < qty; i++) {
            if (remainder > 0) {
                expenses.add(fraction + 1);
                remainder--;
            } else {
                expenses.add(fraction);
            }
        }

        return expenses;
    }

    @Test
    public void divide2on3() throws Exception {
        assertEquals(Arrays.asList(1, 1, 0), HonestDivider.divide(2, 3));
    }

    @Test
    public void divide7on5() throws Exception {
        assertEquals(Arrays.asList(2, 2, 1, 1, 1), HonestDivider.divide(7, 5));
    }

    @Test
    public void divide9on5() throws Exception {
        assertEquals(Arrays.asList(2, 2, 2, 2, 1), HonestDivider.divide(9, 5));
    }

    @Test
    public void divide17on5() throws Exception {
        assertEquals(Arrays.asList(4, 4, 3, 3, 3), HonestDivider.divide(17, 5));
    }

}
