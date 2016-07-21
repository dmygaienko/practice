package com.mygaienko.common.digest;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
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
    public void testPBKDF2WithHmacSHA1() throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec("password".toCharArray(), "salt".getBytes(), 10, 160);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] encoded = factory.generateSecret(spec).getEncoded();

        System.out.println(new BigInteger(1, encoded).toString(16));
    }

    @Test
    public void testBCryptPasswordEncoder() throws NoSuchAlgorithmException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode("password");

        assertTrue(encoder.matches("password", encoded));
    }


}
