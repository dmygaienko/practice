package com.mygaienko.common.algorithms.condingame.hard.surface;

import java.util.Arrays;
import java.util.List;

/**
 * Created by enda1n on 26.03.2017.
 */
public class SurfaceUtil {

    public static void initRaws(List<String> surfaceRaw, List<String> coordinatesRaw) {
        //ex1(surfaceRaw, coordinatesRaw);
        ex5(surfaceRaw, coordinatesRaw);
    }

    private static void ex1(List<String> surfaceRaw, List<String> coordinatesRaw) {
        surfaceRaw.addAll(Arrays.asList(
                "####",
                "##O#",
                "#OO#",
                "####"
        ));

        coordinatesRaw.addAll(Arrays.asList(
                "0 0", "1 2", "2 1" ));
    } 
    
    private static void ex5(List<String> surfaceRaw, List<String> coordinatesRaw) {
        surfaceRaw.addAll(Arrays.asList(
                "OO###O",
                "#O###O",
                "#OO##O",
                "####O#",
                "##OO##",
                "##OO##"
        ));

        coordinatesRaw.addAll(Arrays.asList(
                "1 0", "3 3", "5 1", "0 5", "2 5"
        ));
    }
}
