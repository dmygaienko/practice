package com.mygaienko.common.matcher;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by enda1n on 22.05.2017.
 */
public class MatcherTest {

    @Test
    public void testIs() throws Exception {
        assertThat("test", is("test"));
    }

    @Test
    public void testNotIs() throws Exception {
        assertThat("test", not(is("test1")));
    }

    @Test
    public void testStartWith() throws Exception {
        assertThat("test", startsWith("te"));
    }

    @Test
    public void testEndWith() throws Exception {
        assertThat("test", endsWith("st"));
    }

    @Test
    public void testContainsString() throws Exception {
        assertThat("test", containsString("es"));
    }

    @Test
    public void testEqualToIgnoringWhiteSpace() throws Exception {
        assertThat(" te st", equalToIgnoringWhiteSpace("te st "));
    }

    @Test
    public void testEqualToIgnoringCase() throws Exception {
        assertThat("test", equalToIgnoringCase("TEST"));
    }

    @Test
    public void testAllOf() throws Exception {
        assertThat("test", allOf(is("test"), containsString("te")));
    }

    @Test
    public void testAnyOf() throws Exception {
        assertThat("test", anyOf(is("test"), is("test1")));
    }

    @Test
    public void testIsEmptyOrNullString() throws Exception {
        assertThat(null, isEmptyOrNullString());
        assertThat("", isEmptyOrNullString());
    }

    @Test
    public void testLessThanOrEqualTo() throws Exception {
        assertThat(9, lessThanOrEqualTo(10));
    }

    @Test
    public void testIsCloseTo() throws Exception {
        assertThat(new BigDecimal(1.49),
                closeTo(new BigDecimal(1.0), new BigDecimal(0.5)));
    }

    @Test
    public void testTypeCompatibleWith() throws Exception {
        assertThat(Long.class, typeCompatibleWith(Number.class));
    }

    @Test
    public void testHasToString() throws Exception {
        assertThat(true, hasToString("true"));
    }

    @Test
    public void testIsIn() throws Exception {
        assertThat(1, isIn(asList(1, 2)));
    }

    @Test
    public void testEveryItemIsIn() throws Exception {
        assertThat(asList(1, 2), everyItem(isIn(asList(1, 2, 3))));
    }

    @Test
    public void testContains() throws Exception {
        assertThat(asList(1, 2, 3), contains(1, 2, 3));
    }


    @Test
    public void testEmpty() throws Exception {
        assertThat(emptyList(), empty());
    }

    @Test
    public void testContainsInAnyOrder() throws Exception {
        assertThat(new HashSet<>(asList(1, 2, 3)), containsInAnyOrder(2, 3, 1));
    }

    @Test
    public void testHasItems() throws Exception {
        assertThat(asList(1, 2, 3), hasItems(3, 2));
    }
}
