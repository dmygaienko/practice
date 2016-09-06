package com.mygaienko.common.concurrency.pingpong;

import java.util.concurrent.*;

/**
 * Created by enda1n on 27.08.2016.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Player player3 = new Player(3);
        Player player4 = new Player(4);
        Player player5 = new Player(5);
        Player player6 = new Player(6);

        Team team1 = new Team(1, player1, player2, player3);
        Team team2 = new Team(2, player4, player5, player6);

        new Game(1000, team1, team2);

        Future<String> future1 = executorService.submit(() -> player1.play());
        Future<String> future2 = executorService.submit(() -> player2.play());
        Future<String> future3 = executorService.submit(() -> player3.play());
        Future<String> future4 = executorService.submit(() -> player4.play());
        Future<String> future5 = executorService.submit(() -> player5.play());
        Future<String> future6 = executorService.submit(() -> player6.play());

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
        System.out.println(future4.get());
        System.out.println(future5.get());
        System.out.println(future6.get());
    }
}
