package service;

/**
 * Created by dmygaenko on 06/09/2016.
 */
public class BeanA {

    private BeanB beanB;

    public BeanA(BeanB beanB) {
        this.beanB = beanB;
    }

    public BeanB getBeanB() {
        return beanB;
    }

    public String getBeanBName() {
        return beanB.getName();
    }

}
