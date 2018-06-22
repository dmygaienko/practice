package com.mygaienko.common.crypto;

import org.junit.Before;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by enda1n on 12.12.2017.
 */
public class CipherTest {

    private Cipher cipher;
    private Key key;

    @Before
    public void setUp() throws Exception {
        cipher = Cipher.getInstance("AES");
        key = new SecretKeySpec(toBytes("1234567890123456"),"AES");
    }

    @Test
    public void testEncrypt() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encrypted = cipher.doFinal(toBytes("password"));

        System.out.println("bytes: " + Arrays.toString(encrypted));
        System.out.println("string: " + new String(encrypted, "UTF-8"));
    }

    @Test
    public void testDecrypt() throws Exception {
        byte[] encrypted = new byte[] {-103, -61, 55, -55, 101, -109, -5, -53, 43, 123, -93, 35, -108, 85, 41, -61};

        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(encrypted);

        System.out.println("bytes: " + Arrays.toString(decrypted));
        System.out.println("string: " + new String(decrypted, "UTF-8"));
    }

    private byte[] toBytes(String raw) throws UnsupportedEncodingException {
        return raw.getBytes("UTF-8");
    }
}
