package com.mygaienko.common.concurrency.pingpong;

/**
 * Created by enda1n on 27.08.2016.
 */
public class Player {

    private int no;
    private Team team;
    private Game game;

    public Player(int no) {
        this.no = no;
    }

    public String play()  {
        while (!game.isOver()) {
            try {
                takeTurn();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "done";
    }

    private void takeTurn() throws InterruptedException {
        synchronized (team.getLock()) {
            while (!team.isNextTurn(this)) {
                System.out.println("player " + no + " waited for team lock");
                team.getLock().wait();
            }

            synchronized (game.getLock()) {
                while (!game.isNextTurn(team)) {
                    System.out.println("player " + no + " waited for game lock");
                    team.getLock().notifyAll();
                    game.getLock().wait();
                }

                System.out.println("team " + team.getNo() + " player " + no + " kicked the ball");

                passTurn();

                team.getLock().notifyAll();
                game.getLock().notifyAll();
            }
        }
    }

    private void passTurn() {
        game.passGameTurn();
        team.passTeamTurn();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return no == player.no;
    }

    @Override
    public int hashCode() {
        return no;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
