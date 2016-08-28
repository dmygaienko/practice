package com.mygaienko.common.concurrency.queue;

/**
 * Created by enda1n on 28.08.2016.
 */
public class Client {

    private final String id;

    private final Restaurant restaurant;
    private Queue queue;
    private volatile boolean served = false;

    public Client(String id, Restaurant restaurant, Queue queue) {
        this.id = id;
        this.restaurant = restaurant;
        this.queue = queue;
        queue.offer(this);
    }

    public String getId() {
        return id;
    }

    public Queue getEmptyQueue() {
        for (Queue queue : restaurant.getQueues()) {
            if (queue.isEmpty()) {
                return queue;
            }
        }
        return null;
    }

    public void lineUpAnotherQueue() {
        if (served) return;

        Queue emptyQueue = getEmptyQueue();
        if (emptyQueue != null) {
            queue.remove(this);
            emptyQueue.offer(this);
            System.out.println("Client " + id + " removed from queue " + queue.getId() +
                    " enqueued to " + emptyQueue.getId());
        }
    }

    public void setBlockingQueue(Queue queue) {
        this.queue = queue;
    }

    public void served() {
        served = true;
    }
}
