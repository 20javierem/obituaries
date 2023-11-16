package com.moreno.obituaries.utilities;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class Auth {
    private static final String algorithm = "PBKDF2WithHmacSHA512";
    private static final String transformation = "AES/CBC/PKCS5Padding";
    private static final String regex = ":";

    public static SecretKeySpec getSecretKey() {
        byte[] salt = "12345678".getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        try {
            return createSecretKey(Properties.getInstance().getKey().toCharArray(), salt, iterationCount, keyLength);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SecretKeySpec createSecretKey(char[] password, byte[] salt, int iterationCount, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
        SecretKey keyTmp = keyFactory.generateSecret(keySpec);
        return new SecretKeySpec(keyTmp.getEncoded(), "AES");
    }

    public static String encrypt(String dataToEncrypt) {
        try {
            Cipher pbeCipher = Cipher.getInstance(transformation);
            pbeCipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
            AlgorithmParameters parameters = pbeCipher.getParameters();
            IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
            byte[] cryptoText = pbeCipher.doFinal(dataToEncrypt.getBytes(StandardCharsets.UTF_8));
            byte[] iv = ivParameterSpec.getIV();
            return base64Encode(iv) + regex + base64Encode(cryptoText);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, notifique al administrador");
        }
        return null;
    }

    private static String base64Encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decrypt(String string) {
        try {
            String iv = string.split(regex)[0];
            String property = string.split(regex)[1];
            Cipher pbeCipher = Cipher.getInstance(transformation);
            pbeCipher.init(Cipher.DECRYPT_MODE, getSecretKey(), new IvParameterSpec(base64Decode(iv)));
            return new String(pbeCipher.doFinal(base64Decode(property)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] base64Decode(String property) {
        return Base64.getDecoder().decode(property);
    }
}
