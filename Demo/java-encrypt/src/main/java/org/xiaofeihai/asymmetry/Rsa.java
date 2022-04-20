package org.xiaofeihai.asymmetry;

import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author mingming.xu
 * @description:
 * @date 2022/4/20 15:10
 * @Version 1.0
 */

public class Rsa {
    public static final String KEY_ALGORITHM = "RSA";
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
    /**
     * 密钥长度 范围 512-65536 64整数倍 java 默认：1024
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 密钥初始化
     * @return
     */
    public static KeyPair initKey() throws Exception {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.initialize(KEY_SIZE);
        KeyPair keyPair = keyGenerator.generateKeyPair();
        return keyPair;
    }
    /**
     * 获取公钥
     * @param key
     * @return
     */
    public static PublicKey getPublicKey(byte[] key) throws Exception {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePublic(spec);
    }
    /**
     * 获取私钥
     * @param key
     * @return
     */
    public static PrivateKey getPrivateKey(byte[] key) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(spec);
    }

    /**
     * 加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * 解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    /**
     * 签名
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] sign(byte[] data, PrivateKey key) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(key);
        signature.update(data);
        return signature.sign();
    }
    /**
     * 验签
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] sign, byte[] data, PublicKey key) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(key);
        signature.update(data);
        return signature.verify(sign);
    }
    public static void main(String[] args) throws Exception {
        // 公钥加密，私钥解密，私钥加签，公钥验签
        KeyPair keyPair = initKey();
        // 公私钥，字符
        String publicKey = Base64.toBase64String(keyPair.getPublic().getEncoded());
        String privateKey = Base64.toBase64String(keyPair.getPrivate().getEncoded());
        System.out.println(publicKey);
        System.out.println(privateKey);
        // 公钥加密
        byte[] enData = encrypt("hello".getBytes(), keyPair.getPublic());
        // 私钥解密
        byte[] deData = decrypt(enData, keyPair.getPrivate());
        System.out.println(new String(deData));

        byte[] signature = sign("hello".getBytes(), keyPair.getPrivate());
        boolean verify = verify(signature, "hello".getBytes(), keyPair.getPublic());
        System.out.println("验签结果：" + verify);
    }
}
