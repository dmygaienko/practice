package com.mygaienko.common.io.stream;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by enda1n on 18.10.2016.
 */
public class FlatDatabase {
    public final static int PART_NUMBER_LENGTH = 20;
    public final static int DESCRIPTION_LENGTH = 30;
    public final static int QUANTITY_LENGTH = 4;
    public final static int COST_LENGTH = 4;
    private final static int RECORD_LENGTH = 2 * PART_NUMBER_LENGTH + 2 * DESCRIPTION_LENGTH + QUANTITY_LENGTH + COST_LENGTH;
    private RandomAccessFile raf;

    public FlatDatabase(String path) throws IOException {
        raf = new RandomAccessFile(path, "rw");
    }

    public void append(String partnum, String partdesc, int qty, int ucost)
            throws IOException {
        raf.seek(raf.length());
        write(partnum, partdesc, qty, ucost);
    }

    public void close() {
        try {
            raf.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

    public int numRecs() throws IOException {
        return (int) raf.length() / RECORD_LENGTH;
    }

    public Part select(int recno) throws IOException {
        if (recno < 0 || recno >= numRecs())
            throw new IllegalArgumentException(recno + " out of range");
        raf.seek(recno * RECORD_LENGTH);
        return read();
    }

    public void update(int recno, String partnum, String partdesc, int qty,
                       int ucost) throws IOException {
        if (recno < 0 || recno >= numRecs())
            throw new IllegalArgumentException(recno + " out of range");
        raf.seek(recno * RECORD_LENGTH);
        write(partnum, partdesc, qty, ucost);
    }

    private Part read() throws IOException {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < PART_NUMBER_LENGTH; i++)
            sb.append(raf.readChar());
        String partnum = sb.toString().trim();
        sb.setLength(0);
        for (int i = 0; i < DESCRIPTION_LENGTH; i++)
            sb.append(raf.readChar());
        String partdesc = sb.toString().trim();
        int qty = raf.readInt();
        int ucost = raf.readInt();
        return new Part(partnum, partdesc, qty, ucost);
    }

    private void write(String partnum, String partdesc, int qty, int ucost)
            throws IOException {
        StringBuffer sb = new StringBuffer(partnum);
        if (sb.length() > PART_NUMBER_LENGTH)
            sb.setLength(PART_NUMBER_LENGTH);
        else if (sb.length() < PART_NUMBER_LENGTH) {
            int len = PART_NUMBER_LENGTH - sb.length();
            for (int i = 0; i < len; i++)
                sb.append(" ");
        }
        raf.writeChars(sb.toString());
        sb = new StringBuffer(partdesc);
        if (sb.length() > DESCRIPTION_LENGTH)
            sb.setLength(DESCRIPTION_LENGTH);
        else if (sb.length() < DESCRIPTION_LENGTH) {
            int len = DESCRIPTION_LENGTH - sb.length();
            for (int i = 0; i < len; i++)
                sb.append(" ");
        }
        raf.writeChars(sb.toString());
        raf.writeInt(qty);
        raf.writeInt(ucost);
    }

    public static class Part {
        private String partnum;
        private String desc;
        private int qty;
        private int ucost;

        public Part(String partnum, String desc, int qty, int ucost) {
            this.partnum = partnum;
            this.desc = desc;
            this.qty = qty;
            this.ucost = ucost;
        }

        String getDesc() {
            return desc;
        }

        String getPartnum() {
            return partnum;
        }

        int getQty() {
            return qty;
        }

        int getUnitCost() {
            return ucost;
        }

        @Override
        public String toString() {
            return "Part{" +
                    "partnum='" + partnum + '\'' +
                    ", desc='" + desc + '\'' +
                    ", qty=" + qty +
                    ", ucost=" + ucost +
                    '}';
        }
    }
}
