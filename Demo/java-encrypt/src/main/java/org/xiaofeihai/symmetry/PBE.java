package org.xiaofeihai.symmetry;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author mingming.xu
 * @description:
 * @date 2022/4/20 11:51
 * @Version 1.0
 */

public class PBE {
    public static void main(String[] args) throws Exception {
        String ALGORITHM = "PBEWITHMD5andDES";
        // 迭代次数
        int ITERATION_COUNT = 100;
        // 盐初始化
        SecureRandom random = new SecureRandom();
        byte[] salt = random.generateSeed(8);

        // 生成密钥，密钥转换
        PBEKeySpec keySpec = new PBEKeySpec("password".toCharArray());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = keyFactory.generateSecret(keySpec);

        // 加密
        PBEParameterSpec parameterSpec = new PBEParameterSpec(salt, ITERATION_COUNT);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);
        byte[] enData = cipher.doFinal("hello".getBytes());

        // 解密
        PBEParameterSpec parameSpec = new PBEParameterSpec(salt, ITERATION_COUNT);
        Cipher deCipher = Cipher.getInstance(ALGORITHM);
        deCipher.init(Cipher.DECRYPT_MODE, secretKey, parameSpec);
        byte[] deData = deCipher.doFinal(enData);
        System.out.println(new String(deData));
    }
}
