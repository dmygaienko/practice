package com.mygaienko.patterns.behavioral.command;

import java.util.Map;

/**
 * Created by dmygaenko on 24/03/2016.
 */
public class Engine {

    private Map<String, Processor> processors;

    public void process(Context context) {
        processors.get(context.getName()).process(context);
    }

    public void registerProcessor(String name, Processor processor) {
        processors.put(name, processor);
    }
}
