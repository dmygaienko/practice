package com.mygaienko.common.concurrency.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by enda1n on 28.08.2016.
 */
public class Queue {

    private String id;

    public Queue(String id) {
        this.id = id;
    }

    private BlockingQueue<Client> q = new ArrayBlockingQueue<>(10);

    public boolean isEmpty() {
        return q.isEmpty();
    }

    public void remove(Client client) {
        q.remove(client);
    }

    public void offer(Client client) {
        q.offer(client);
    }

    public Client poll() {
        return q.poll();
    }

    public String getId() {
        return id;
    }
}
