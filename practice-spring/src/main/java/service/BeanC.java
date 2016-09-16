package service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * Created by dmygaenko on 06/09/2016.
 */
@Component
public class BeanC {

    @Lookup
    public BeanPrototype getPrototype() {
        return null;
    };

}
