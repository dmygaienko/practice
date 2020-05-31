package com.mygaienko.common.algorithms;

import com.mygaienko.common.algorithms.condingame.hard.the_bridge_episode2.Player;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by enda1n on 28.03.2017.
 */
public class PermutationsWithRepetitionTest {

    @Test
    public void testPermute() throws Exception {
        ArrayList<List<Player.Action>> allActions = new ArrayList<>();
        PermutationsWithRepetition.permute(Player.Action.values(), 0, 5, new ArrayList<>(), allActions);

        System.out.println(allActions.size());
        System.out.println(allActions);
    }
}