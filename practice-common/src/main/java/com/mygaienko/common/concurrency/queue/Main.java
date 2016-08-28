package com.mygaienko.common.concurrency.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by enda1n on 28.08.2016.
 */
public class Main {

    public static void main(String[] args) {
        Queue queue1 = new Queue("1");
        Queue queue2 = new Queue("2");

        Restaurant restaurant = new Restaurant();
        restaurant.addQueue(queue1);
        restaurant.addQueue(queue2);

        Cashier cashier1 = new Cashier(queue1, 2000, "1");
        Cashier cashier2 = new Cashier(queue2, 2000, "2");

        Client client1 = new Client("1", restaurant, queue1);
        Client client2 = new Client("2", restaurant, queue2);
        Client client3 = new Client("3", restaurant, queue1);
        Client client4 = new Client("4", restaurant, queue1);
        Client client5 = new Client("5", restaurant, queue1);

        ExecutorService cashiersService = Executors.newFixedThreadPool(2);
        orderCachiers(cashiersService, cashier1, cashier2);

        ExecutorService clientsService = Executors.newFixedThreadPool(2);
        lineClients(clientsService, client1, client2, client3, client4, client5);

        shutDownExecutors(cashiersService, clientsService);
    }

    private static void shutDownExecutors(ExecutorService cashiersService, ExecutorService clientsService) {
        try {
            cashiersService.shutdown();
            cashiersService.awaitTermination(10, TimeUnit.SECONDS);
            clientsService.shutdown();
            clientsService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void lineClients(ExecutorService clientsService, Client... clients) {
        for (Client client : clients) {
            clientsService.execute(() -> {
                while(true) {
                    client.lineUpAnotherQueue();
                }
            });
        }
    }

    private static void orderCachiers(ExecutorService cashiersService, Cashier... cashiers) {
        for (Cashier cashier : cashiers) {
            cashiersService.execute(() -> {
                while(true) {
                    cashier.serve();
                }
            });
        }
    }
}
