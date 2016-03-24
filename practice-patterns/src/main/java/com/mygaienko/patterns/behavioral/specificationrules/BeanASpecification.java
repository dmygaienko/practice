package com.mygaienko.patterns.behavioral.specificationrules;

import java.util.Random;

/**
 * Created by dmygaenko on 23/03/2016.
 */
public class BeanASpecification implements Specification<Bean> {

    @Override
    public boolean isSatisfiedBy(Bean bean) {
        return new Random(1).nextBoolean();
    }

    @Override
    public void specify(Bean bean, Context context){
        context.setaAttribute(bean.getaAttribute());
    }
}
