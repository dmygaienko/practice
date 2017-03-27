package com.mygaienko.common.algorithms;

import com.mygaienko.common.algorithms.condingame.hard.the_bridge_episode2.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enda1n on 28.03.2017.
 */
public class PermutationsWithRepetition {

    public static void permute(Player.Action[] values, int nextValue, int maxLength,
                               List<Player.Action> currentActions, List<List<Player.Action>> allActions) {

        currentActions.add(values[nextValue]);
        if (currentActions.size() < maxLength) {
            for (int i = 0; i < values.length; i++) {
                permute(values, i, maxLength, new ArrayList<>(currentActions), allActions);
            }
        } else {
            allActions.add(currentActions);
        }
    }
}
