package org.xiaofeihai.symmetry;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * @author mingming.xu
 * @description:
 * @date 2022/4/20 11:13
 * @Version 1.0
 */

public class Aes {

    public static void main(String[] args) throws Exception {
        // 生成密钥
        // java 7 支持长度 128 192 256 默认128
        // 填充方式 NoPadding/PKCS5Padding/ISO10126Padding
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        byte[] encodedKey = key.getEncoded();

        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(encodedKey, "AES");

        // 加密  算法/工作模式/填充方式
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] enData = cipher.doFinal("hello".getBytes());

        // 解密
        Cipher deCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] deData = deCipher.doFinal(enData);
        System.out.println(new String(deData));
    }
}
