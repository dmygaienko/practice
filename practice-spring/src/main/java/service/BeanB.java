package service;

import java.util.List;

/**
 * Created by dmygaenko on 06/09/2016.
 */
public class BeanB {

    private String name;

    private List<String> strings;

    public BeanB(String name) {
        this.name = name;
    }

    public BeanB(List<String> strings) {
        this.strings = strings;
    }

    public String getName() {
        return name;
    }

    public List<String> getStrings() {
        return strings;
    }
}
