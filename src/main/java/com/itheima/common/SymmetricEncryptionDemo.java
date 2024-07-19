package com.itheima.common;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


/**
 * 对称加密算法加密密码
 */
public class SymmetricEncryptionDemo {

    public static void main(String[] args) throws Exception {
        String originalText = "W00575";
        String key = "sfh79878901234563820123434589w12";


        byte[] res = encodeText(originalText, key);
        System.out.println(res);


        String decodedText = decodeText(res, key);
        System.out.println(decodedText);




    }


    /**
     * 加密
     * @param originalText    原始文本
     * @param key    密钥
     * @return    加密后的文本
     * @throws Exception 异常
     */
    public static byte[] encodeText(String originalText, String key) throws Exception {
        try {
            // 加密
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(originalText.getBytes());
            return encryptedBytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *  解密
     * @param encryptedBytes    加密后的字节数组
     * @param key    密钥
     * @return    解密后的文本
     * @throws Exception 异常
     */

    public static String decodeText(byte[] encryptedBytes, String key) throws Exception {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // 解密
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            String decryptedText = new String(decryptedBytes);
            return decryptedText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
