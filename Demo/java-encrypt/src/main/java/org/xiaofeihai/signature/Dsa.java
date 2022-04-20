package org.xiaofeihai.signature;

import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author mingming.xu
 * @description:
 * @date 2022/4/20 16:28
 * @Version 1.0
 */

public class Dsa {
    public static final String KEY_ALGORITHM = "DSA";
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "SHA1withDSA";
    /**
     * 密钥长度 范围 512-1024 64整数倍 java 默认：1024
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 密钥初始化
     * @return
     */
    public static KeyPair initKey() throws Exception {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.initialize(KEY_SIZE, new SecureRandom());
        KeyPair keyPair = keyGenerator.generateKeyPair();
        return keyPair;
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
        byte[] signature = sign("hello".getBytes(), keyPair.getPrivate());
        boolean verify = verify(signature, "hello".getBytes(), keyPair.getPublic());
        System.out.println("验签结果：" + verify);
    }
}
