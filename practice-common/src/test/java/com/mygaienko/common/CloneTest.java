package com.mygaienko.common;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by dmygaenko on 20/09/2016.
 */
public class CloneTest {

    @Test(expected = CloneNotSupportedException.class)
    public void testSimpleBeanClone() throws CloneNotSupportedException {
        SimpleBean simpleBean = new SimpleBean("simpleString");
        simpleBean.clone();
    }

    @Test
    public void testSimpleBeanCloneableClone() throws CloneNotSupportedException {
        SimpleBeanCloneable bean = new SimpleBeanCloneable("simpleString", 1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        bean.clone();

        assertEquals(bean, bean.clone());
    }


    public static class SimpleBeanCloneable implements Cloneable {

        private String simpleString;

        private List<Integer> integers;

        public SimpleBeanCloneable(String simpleString, Integer... integers) {
            this.simpleString = simpleString;
            this.integers = Arrays.asList(integers);
        }

        public String getSimpleString() {
            return simpleString;
        }

        public List<Integer> getIntegers() {
            return integers;
        }

        public Object clone() throws CloneNotSupportedException {
           return super.clone();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SimpleBeanCloneable that = (SimpleBeanCloneable) o;

            if (simpleString != null ? !simpleString.equals(that.simpleString) : that.simpleString != null)
                return false;
            return integers != null ? integers.equals(that.integers) : that.integers == null;

        }

        @Override
        public int hashCode() {
            int result = simpleString != null ? simpleString.hashCode() : 0;
            result = 31 * result + (integers != null ? integers.hashCode() : 0);
            return result;
        }
    }

    public static class SimpleBean {

        private String simpleString;

        public SimpleBean(String simpleString) {
            this.simpleString = simpleString;
        }

        public String getSimpleString() {
            return simpleString;
        }

        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

    }

}
