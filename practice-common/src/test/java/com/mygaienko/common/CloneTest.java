package com.mygaienko.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        SimpleBeanCloneable clone = (SimpleBeanCloneable) bean.clone();

        assertEquals(bean, clone);
        assertTrue(bean.getIntegersList() != clone.getIntegersList());
        assertTrue(bean.getIntegersArray() != clone.getIntegersArray());
    }


    public static class SimpleBeanCloneable implements Cloneable {

        private String simpleString;

        private List<Integer> integersList;

        private Integer[] integersArray;

        public SimpleBeanCloneable(String simpleString, Integer... integers) {
            this.simpleString = simpleString;
            this.integersList = Arrays.asList(integers);
            integersArray = Arrays.copyOf(integers, integers.length);
        }

        public SimpleBeanCloneable() {
        }

        public String getSimpleString() {
            return simpleString;
        }

        public List<Integer> getIntegersList() {
            return integersList;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            SimpleBeanCloneable copy = (SimpleBeanCloneable) super.clone();
            copy.integersArray = this.integersArray.clone();
            copy.integersList = new ArrayList<>();
            copy.integersList.addAll(this.integersList);
            return copy;
        }

        public Integer[] getIntegersArray() {
            return integersArray;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SimpleBeanCloneable that = (SimpleBeanCloneable) o;

            if (simpleString != null ? !simpleString.equals(that.simpleString) : that.simpleString != null)
                return false;
            if (integersList != null ? !integersList.equals(that.integersList) : that.integersList != null)
                return false;
            // Probably incorrect - comparing Object[] arrays with Arrays.equals
            return Arrays.equals(integersArray, that.integersArray);

        }

        @Override
        public int hashCode() {
            int result = simpleString != null ? simpleString.hashCode() : 0;
            result = 31 * result + (integersList != null ? integersList.hashCode() : 0);
            result = 31 * result + Arrays.hashCode(integersArray);
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
