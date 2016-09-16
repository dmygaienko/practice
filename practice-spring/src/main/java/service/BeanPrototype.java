package service;

import java.util.Random;

/**
 * Created by dmygaenko on 06/09/2016.
 */
public class BeanPrototype {

    private int id = new Random().nextInt();

    public int getId() {
        return id;
    }
}
