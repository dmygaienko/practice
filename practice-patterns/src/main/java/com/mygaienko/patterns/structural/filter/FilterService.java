package com.mygaienko.patterns.structural.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmygaenko on 13/10/2016.
 */
public class FilterService<T> {

    private List<Filter<List<T>>> filters = new ArrayList<>();

    public List<T> filtrate(List<T> t){
        List<T> result = t;
        for (Filter<List<T>> filter : filters) {
            result = filter.filtrate(result);
        }
        return result;
    }

    public void addFilter(Filter<List<T>> filter) {
        filters.add(filter);
    }
}
