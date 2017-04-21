package com.mygaienko.common.stream;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by dmygaenko on 21/04/2017.
 */
public class StreamUtil {


    public static <A, B, C> Stream<C>  zip(
            Stream<? extends A> aStream, Stream<? extends B> bStream,
            BiFunction<? super A, ? super B, C> zipper) {

        Spliterator<? extends A> aSpliterator = aStream.spliterator();
        Spliterator<? extends B> bSpliterator = bStream.spliterator();

        // Zipping looses DISTINCT and SORTED characteristics
        int characteristics = aSpliterator.characteristics() & bSpliterator.characteristics() &
                ~(Spliterator.DISTINCT | Spliterator.SORTED);

        long zipSize = ((characteristics & Spliterator.SIZED) != 0)
                ? Math.min(aSpliterator.getExactSizeIfKnown(), bSpliterator.getExactSizeIfKnown())
                : -1;

        Iterator<A> aIterator = Spliterators.iterator(aSpliterator);
        Iterator<B> bIterator = Spliterators.iterator(bSpliterator);

        Iterator<C> iterator = new Iterator<C>() {
            @Override
            public boolean hasNext() {
                return aIterator.hasNext() && bIterator.hasNext();
            }

            @Override
            public C next() {
                return zipper.apply(aIterator.next(), bIterator.next());
            }
        };
        Spliterator<C> spliterator = Spliterators.spliterator(iterator, zipSize, characteristics);

        return StreamSupport.stream(spliterator, aStream.isParallel() || bStream.isParallel());
    }
}
