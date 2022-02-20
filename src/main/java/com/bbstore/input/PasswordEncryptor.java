package com.bbstore.input;

import com.bbstore.alert.AlertBox;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class PasswordEncryptor {
    public static String md5(String sourceString){
        try {
            byte[] bytesOfMessage = sourceString.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // byte array of md5 hash
            byte[] md5 = md.digest(bytesOfMessage);
            // we convert bytes to hex as php's md5() would do
            StringBuilder stringBuffer = new StringBuilder();
            for (byte b : md5) {
                stringBuffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            AlertBox.showAlert("Password encryption failed", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static String md5(char[] charArray) {
        return md5(String.valueOf(charArray));
    }
}
