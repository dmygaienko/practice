package service;

import advice.Audit;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by dmygaenko on 06/09/2016.
 */
public class BeanB {

    @Autowired
    private AutowiredBean autowiredBean;

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

    public AutowiredBean getAutowiredBean() {
        return autowiredBean;
    }

    @Audit("home")
    public void serve() {
        System.out.println("serve");
    }
}
