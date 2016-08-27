package com.mygaienko.common.files;


import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmygaenko on 20/07/2016.
 */
public class FileEditor {

    public static final String EDITED_STRING = "TARGET_TYPE=\"RECORD\"";
    public static final String CLOSED_BRACKET = "/>";
    public static final String NEWLINE = System.getProperty("line.separator");
    public static final String TO_EDIT = " " + EDITED_STRING + CLOSED_BRACKET;

    public static final Pattern PATTERN = Pattern.compile("((.)*<ALT_IDENTIFIER_TYPE(.)*/>)");

    public static void main(String[] args) throws IOException {
        traverseFile(new File("C:\\Users\\mygadmy\\dev"));
    }

    private static void traverseFile(File file) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File dirFile : files) {
                traverseFile(dirFile);
            }
        } else {
            editFile(file);
        }
    }

    private static void editFile(File file) throws IOException {

        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        StringBuilder builder = new StringBuilder();

        boolean toWrite = false;
        String readString;
        while ((readString = bufferedReader.readLine()) != null) {

            Matcher matcher = PATTERN.matcher(readString);
            if (matcher.matches()) {
                String matched = matcher.toMatchResult().group(0);

                String edited;
                if (!matched.contains(EDITED_STRING)) {
                    toWrite = true;
                    edited = matched.replace(CLOSED_BRACKET, TO_EDIT);
                    readString = edited;
                }
            }
            builder.append(readString);
            builder.append(NEWLINE);
        }

        builder.deleteCharAt(builder.length() - 1);

        bufferedReader.close();

        if (toWrite) {
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.append(builder);
            bufferedWriter.close();
        }
    }
}
