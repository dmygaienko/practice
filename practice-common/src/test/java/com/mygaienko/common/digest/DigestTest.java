package com.mygaienko.common.digest;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by dmygaenko on 20/07/2016.
 */
public class DigestTest {
    
    @Test
    public void test() throws NoSuchAlgorithmException {
        MessageDigest digester = MessageDigest.getInstance("SHA-256");
        digester.update("password".getBytes());

        System.out.println(Arrays.toString(digester.digest()));
    }
    
}
