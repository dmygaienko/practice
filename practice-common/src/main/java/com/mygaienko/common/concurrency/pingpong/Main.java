package com.mygaienko.common.concurrency.pingpong;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by enda1n on 27.08.2016.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Player player3 = new Player(3);
        Player player4 = new Player(4);
        Player player5 = new Player(5);
        Player player6 = new Player(6);

        Team team1 = new Team(1, player1, player2, player3);
        Team team2 = new Team(2, player4, player5, player6);

       // Team team1 = new Team(1, player1, player2);
       // Team team2 = new Team(2, player3, player4);

        new Game(1000, team1, team2);

        executorService.execute(() -> player1.play());
        executorService.execute(() -> player2.play());
        executorService.execute(() -> player3.play());
        executorService.execute(() -> player4.play());
        executorService.execute(() -> player5.play());
        executorService.execute(() -> player6.play());

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
    }
}
