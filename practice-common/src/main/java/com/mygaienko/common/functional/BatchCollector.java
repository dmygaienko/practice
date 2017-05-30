package com.mygaienko.common.functional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * Created by enda1n on 30.05.2017.
 */
public class BatchCollector<T> implements Collector<T, List<T>, List<T>> {

    private Consumer<List<T>> consumer;
    private int batchSize;

    public BatchCollector(Consumer<List<T>> consumer, int batchSize) {
        this.consumer = consumer;
        this.batchSize = batchSize;
    }

    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return (list, element) -> {
            list.add(element);
            if (list.size() == batchSize) {
                consumer.accept(list);
                list.clear();
            }
        };
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (listA, listB) -> {
            if (listA.size() == batchSize) {
                consumer.accept(listA);
                listA.clear();
            } else {
                listA.addAll(listB);

                if (listA.size() >= batchSize) {
                    int fromIndex = listA.size() - batchSize;
                    consumer.accept(listA.subList(fromIndex, listA.size()));

                    if (fromIndex > 0) {
                        listA = new ArrayList<>(listA.subList(0, fromIndex));
                    } else {
                        listA.clear();
                    }
                }
            }

            return listA;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return list -> Collections.EMPTY_LIST;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
