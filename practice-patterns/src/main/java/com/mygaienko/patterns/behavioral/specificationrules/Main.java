package com.mygaienko.patterns.behavioral.specificationrules;

import java.util.ArrayList;

/**
 * Created by dmygaenko on 23/03/2016.
 */
public class Main {

    public static void main(String[] args) {
        Bean bean = new Bean("a", "b");
        Context context = new Context();

        Specification aSpecification = new BeanASpecification();
        Specification bSpecification = new BeanBSpecification();

        ArrayList<Specification> specifications = new ArrayList<Specification>();
        specifications.add(aSpecification);
        specifications.add(bSpecification);

        for (Specification specification : specifications) {
            if (specification.isSatisfiedBy(bean)) {
                specification.specify(bean, context);
            }
        }

        System.out.println(context);


    }
}
