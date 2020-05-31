package com.mygaienko.common.stream;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by enda1n on 05.06.2017.
 */
public class OptionalTest {

    @Test
    public void flatMapOptional() throws Exception {

        final Optional<Optional<Object>> emptyOfEmpty = Optional.of(Optional.empty());
        System.out.println("empty of empty: " + emptyOfEmpty);

        final Optional<String> empty = emptyOfEmpty
                //.filter(Optional::isPresent)
                .flatMap(optional -> Optional.of(optional.orElse("") + "modified"));

        assertThat(emptyOfEmpty, is(Optional.of(Optional.empty())));
        assertThat(empty, is(Optional.of("modified")));

        System.out.println(empty);
    }

    @Test
    public void mapOptional() throws Exception {

        Optional<Object> empty = Optional.empty();

        final Optional<String> modified = empty.map(value -> value + "modified");

        assertThat(modified, is(Optional.empty()));

        System.out.println(modified);
    }
}
