package org.xiaofeihai.symmetry;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * @author mingming.xu
 * @description:
 * @date 2022/4/20 10:58
 * @Version 1.0
 */

public class DESede {

    public static void main(String[] args) throws Exception {
        // 生成DES密钥
        // java 7 支持长度 112 168
        // Bouncy Castle 支持128 192
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
        keyGenerator.init(168);
        SecretKey key = keyGenerator.generateKey();
        byte[] encodedKey = key.getEncoded();

        // 还原密钥
        DESedeKeySpec spec = new DESedeKeySpec(encodedKey);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
        SecretKey secretKey = factory.generateSecret(spec);

        // 加密  算法/工作模式/填充方式
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] enData = cipher.doFinal("hello".getBytes());

        // 解密
        Cipher deCipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] deData = deCipher.doFinal(enData);
        System.out.println(new String(deData));
    }
}
