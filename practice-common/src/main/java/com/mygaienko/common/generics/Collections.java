package com.mygaienko.common.generics;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by dmygaenko on 21/09/2016.
 */
public class Collections {

    public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {
        Iterator<? extends T> i = coll.iterator();
        T candidate = i.next();

        while (i.hasNext()) {
            T next = i.next();
            if (next.compareTo(candidate) > 0)
                candidate = next;
        }
        return candidate;
    }


}


