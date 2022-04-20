package org.xiaofeihai.symmetry;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * @author mingming.xu
 * @description:
 * @date 2022/4/20 10:39
 * @Version 1.0
 */

public class Des {
    public static void main(String[] args) throws Exception {
        // 生成DES密钥
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56);
        SecretKey key = keyGenerator.generateKey();
        byte[] encodedKey = key.getEncoded();

        // 还原密钥
        DESKeySpec spec = new DESKeySpec(encodedKey);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = factory.generateSecret(spec);

        // 加密
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] enData = cipher.doFinal("hello".getBytes());

        // 解密
        Cipher deCipher = Cipher.getInstance("DES");
        deCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] deData = deCipher.doFinal(enData);
        System.out.println(new String(deData));
    }
}
