/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;;

/**
 *
 * @author pythonee
 */
public class MD5_Encrypt {
    public static String encrypt(String str) {
        String encrypted = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            String part = null;
            for (int i = 0; i < md5.length; i++) {
                part = Integer.toHexString(md5[i] & 0xFF);
                if (part.length() == 1) {
                    part = "0" + part;
                }
                sb.append(part);
            }
            encrypted = sb.append(part).toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5_Encrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encrypted;
    }
}
