package com.mygaienko.common.nio;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by enda1n on 18.10.2016.
 */
public class ObjectOutInStreamTest {

    private File file;

    @Before
    public void setUp() throws Exception {
        file = new File("ObjectOutInStreamTest.dat");
        file.createNewFile();
        file.deleteOnExit();
    }

    @Test
    public void test() throws Exception {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(getBean(1, '2', "3"));
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            assertThat(inputStream.readObject(), is(getBean(1, '2', "3")));
        }
    }

    private Bean getBean(int i, char c, String s) {
        return new Bean(i, c, s);
    }

    private static class Bean implements Serializable {

        private int i;
        private char c;
        private String s;

        public Bean(int i, char c, String s) {
            this.i = i;
            this.c = c;
            this.s = s;
        }

        public int getI() {
            return i;
        }

        public char getC() {
            return c;
        }

        public String getS() {
            return s;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Bean)) return false;

            if (this == obj) return true;

            final Bean that = (Bean) obj;

            return new EqualsBuilder()
                    .append(this.i, that.i)
                    .append(this.c, that.c)
                    .append(this.s, that.s)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + (int) c;
            result = 31 * result + (s != null ? s.hashCode() : 0);
            return result;
        }
    }
}
