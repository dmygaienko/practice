package com.mygaienko.common.algorithms.condingame.tan_network;

import java.util.Arrays;
import java.util.List;

/**
 * Created by enda1n on 25.03.2017.
 */
public class TanUtil {

    public static void initRaw(List<String> rawStops, List<String> rawRoutes) {
        rawStops.addAll(Arrays.asList(
                "StopArea:ABDU,\"Abel Durand\",,47.22019661,-1.60337553,,,1,",
                "StopArea:ABLA,\"Avenue Blanche\",,47.22973509,-1.58937990,,,1,",
                "StopArea:ACHA,\"Angle Chaillou\",,47.26979248,-1.57206627,,,1,"
        ));

        rawRoutes.addAll(Arrays.asList(
                "StopArea:ABDU StopArea:ABLA",
                "StopArea:ABLA StopArea:ACHA"
        ));
    }
}
