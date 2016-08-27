package com.mygaienko.common.concurrency.pingpong;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by enda1n on 27.08.2016.
 */
public class Game {

    private final Object lock = new Object();

    private final List<Team> teams;
    private Iterator<Team> iterator;
    private Team nextTeam;

    private final long maxIterations;
    private volatile long i = 0;

    public Game(long maxIterations, Team... teams) {
        this.teams = Arrays.asList(teams);
        this.iterator = this.teams.iterator();
        this.maxIterations = maxIterations;
        nextTeam = iterator.next();

        for (Team team : teams) {
            for (Player player : team.getPlayers()) {
                player.setGame(this);
            }
        }

    }

    public boolean isOver() {
        return maxIterations <= i;
    }

    private void iterate() {
        i++;
    }

    public Object getLock() {
        return lock;
    }

    public boolean isNextTurn(Team team) {
        return nextTeam.equals(team);
    }

    public void passGameTurn() {
        if (!iterator.hasNext()) {
            iterator = teams.iterator();
        }
        nextTeam = iterator.next();

        iterate();
    }
}
