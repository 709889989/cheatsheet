package org.xiaofeihai.signature;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;

/**
 * @author mingming.xu
 * @description:
 * @date 2022/4/20 16:38
 * @Version 1.0
 */

public class Ecdsa {
    public static final String KEY_ALGORITHM = "ECDSA";
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "SHA512withECDSA";
    /**
     * 密钥长度 范围 112-571 java 默认：256
     */
    private static final int KEY_SIZE = 256;

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
        Security.addProvider(new BouncyCastleProvider());
        // 公钥加密，私钥解密，私钥加签，公钥验签
        KeyPair keyPair = initKey();
        byte[] signature = sign("hello".getBytes(), keyPair.getPrivate());
        boolean verify = verify(signature, "hello".getBytes(), keyPair.getPublic());
        System.out.println("验签结果：" + verify);
    }
}
