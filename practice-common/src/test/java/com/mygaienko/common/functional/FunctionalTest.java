package com.mygaienko.common.functional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


/**
 * Created by enda1n on 11.10.2016.
 */
public class FunctionalTest {

    public List<String> findHeadings(Reader input) {
        return withLinesOf(
                input,
                lines -> lines.filter(line -> line.endsWith(":"))
                        .map(line -> line.substring(0, line.length() - 1))
                        .collect(toList()),
                HeadingLookupException::new);
    }

    static class HeadingLookupException extends RuntimeException {
        public HeadingLookupException(IOException e) {
        }
    }

    private <T> T withLinesOf(
            Reader input,
            Function<Stream<String>, T> handler,
            Function<IOException, RuntimeException> error) {

        try (BufferedReader reader = new BufferedReader(input)) {
            return handler.apply(reader.lines());
        } catch (IOException e) {
            throw error.apply(e);
        }
    }
}
