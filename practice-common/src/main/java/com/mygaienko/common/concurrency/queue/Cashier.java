package com.mygaienko.common.concurrency.queue;


/**
 * Created by enda1n on 28.08.2016.
 */
public class Cashier {

    private final int millis;
    private final String id;
    private final Queue queue;

    public Cashier(Queue queue, int millis, String id) {
        this.queue = queue;
        this.millis = millis;
        this.id = id;
    }

    public void serve() {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Client client = queue.poll();
        if (client != null) {
            System.out.println("Client " + client.getId() + " is served by cashier " + id);
            client.served();
        } else {
            System.out.println("Queue " + queue.getId() + " is empty");
        }
    }
}
