package org.xiaofeihai.symmetry;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @author mingming.xu
 * @description:
 * @date 2022/4/20 11:23
 * @Version 1.0
 */

public class IDEA {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        // 生成密钥
        // Bouncy Castle实现 密钥长度 128 默认128
        // 填充方式 PKCS5Padding/PKCS7Padding/ISO10126Padding/ZeroBytePadding
        KeyGenerator keyGenerator = KeyGenerator.getInstance("IDEA");
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        byte[] encodedKey = key.getEncoded();

        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(encodedKey, "IDEA");

        // 加密  算法/工作模式/填充方式
        Cipher cipher = Cipher.getInstance("IDEA/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] enData = cipher.doFinal("hello".getBytes());

        // 解密
        Cipher deCipher = Cipher.getInstance("IDEA/ECB/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] deData = deCipher.doFinal(enData);
        System.out.println(new String(deData));
    }
}
