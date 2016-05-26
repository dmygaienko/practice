package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.concurrent.Phaser;

/**
 * Created by enda1n on 26.05.2016.
 */
//TODO must be implemented
public class PhaserTest {

    private final Phaser phaser = new Phaser();

    @Test
    public void test() throws Exception {
        phaser.register();

        for (int i = 0; i < 3; i++) {
            registerPhaser(i);
        }
        System.out.println("main arrived");
        //phaser.arriveAndDeregister();

        System.out.println("main awaitAdvance 2");
        phaser.awaitAdvance(10);

        System.out.println("main  off");
    }

    private void registerPhaser(int i) {
        new Thread(() -> {
            System.out.println("register: " + i);
            phaser.register();
            for (int j = 0; j < 2; j++) {
                System.out.println("phase: " + j + " arriveAndAwaitAdvance: " + i);
                phaser.arriveAndAwaitAdvance();
                System.out.println("phase: " + j + " after await: " + i);
            }
        }
        ).start();
    }
}
