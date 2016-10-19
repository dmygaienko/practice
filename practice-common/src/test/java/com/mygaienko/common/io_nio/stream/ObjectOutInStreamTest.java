package com.mygaienko.common.io_nio.stream;

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
        file.delete();
        file.createNewFile();
        file.deleteOnExit();
    }

    @Test
    public void test() throws Exception {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(getBean(1, '2', "3", "optional"));
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            assertThat(inputStream.readObject(), is(getBean(1, '2', "3", "optional")));
        }
    }

    @Test
    public void testExternalizable() throws Exception {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(getBeanExternalizable(1, '2', "3"));
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            assertThat(inputStream.readObject(), is(getBeanExternalizable(1, '2', "3")));
        }
    }

    private Bean getBean(int i, char c, String s, String optional) {
        return new Bean(i, c, s, optional);
    }

    private BeanExternalizable getBeanExternalizable(int i, char c, String s) {
        return new BeanExternalizable(i, c, s);
    }

    private static class Bean implements Serializable {

        private int i;
        private char c;
        private String s;
        private transient String optional;

        public Bean(int i, char c, String s, String optional) {
            this.i = i;
            this.c = c;
            this.s = s;
            this.optional = optional;
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

        public String getOptional() {
            return optional;
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

    private static class BeanExternalizable implements Externalizable {

        private int i;
        private char c;
        private String s;

        public BeanExternalizable() {
        }

        public BeanExternalizable(int i, char c, String s) {
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
        public void writeExternal(ObjectOutput out) throws IOException {
            out.write(i);
            out.writeChar(c);
            out.writeChars(s);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            this.i = in.read();
            this.c = in.readChar();
            //TODO find how to get string
            this.s = in.readLine();
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof BeanExternalizable)) return false;

            if (this == obj) return true;

            final BeanExternalizable that = (BeanExternalizable) obj;

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
