package com.mygaienko.common.digest;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Created by dmygaenko on 20/07/2016.
 */
public class DigestTest {
    
    @Test
    public void testDigester() throws NoSuchAlgorithmException {
        MessageDigest digester = MessageDigest.getInstance("SHA-256");
        digester.update("password".getBytes());

        System.out.println(Arrays.toString(digester.digest()));
    }

    @Test
    public void testDigesterWithSalt() throws NoSuchAlgorithmException {
        MessageDigest digester = MessageDigest.getInstance("SHA-256");
        digester.update("salt".getBytes());
        digester.update("password".getBytes());

       // System.out.println(new BigInteger(1, digester.digest()).toString(16));
        //System.out.println(new BigInteger(digester.digest()).toString(16));
        System.out.println(DigestUtils.sha256Hex("salt" + "password"));
    }

    @Test
    public void testBCryptPasswordEncoder() throws NoSuchAlgorithmException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode("password");

        assertTrue(encoder.matches("password", encoded));
    }


}
