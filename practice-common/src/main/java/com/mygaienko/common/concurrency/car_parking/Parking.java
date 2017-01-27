package com.mygaienko.common.concurrency.car_parking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by enda1n on 28.01.2017.
 */
public class Parking {

    private BlockingQueue<Car> queue = new ArrayBlockingQueue<>(4);

    public boolean getPlace(Car car) throws InterruptedException {
        return queue.offer(car, 5, TimeUnit.SECONDS);
    }

}
