package com.mygaienko.common.concurrency.pingpong;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by enda1n on 27.08.2016.
 */
public class Team {

    private final Object lock = new Object();

    private int no;
    private final List<Player> players;
    private Iterator<Player> iterator;
    private Player nextPlayer;

    public Team(int no, Player... players) {
        this.no = no;
        this.players = Arrays.asList(players);
        iterator = this.players.iterator();
        nextPlayer = iterator.next();

        for (Player player : players) {
            player.setTeam(this);
        }
    }

    public boolean isNextTurn(Player player) {
        return nextPlayer.equals(player);
    }

    public void passTeamTurn() {
        if (!iterator.hasNext()) {
            iterator = players.iterator();
        }
        nextPlayer = iterator.next();
    }

    public Object getLock() {
        return lock;
    }

    List<Player> getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        return no == team.no;
    }

    public int getNo() {
        return no;
    }

    @Override
    public int hashCode() {
        return no;
    }
}
