package com.mygaienko.common.concurrency.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enda1n on 28.08.2016.
 */
public class Restaurant {

    private List<Queue> queues = new ArrayList<>();

    public List<Queue> getQueues() {
        return queues;
    }

    public void addQueue(Queue queue) {
        queues.add(queue);
    }
}
