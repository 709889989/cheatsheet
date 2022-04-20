package org.xiaofeihai.asymmetry;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author mingming.xu
 * @description:
 * @date 2022/4/20 15:43
 * @Version 1.0
 */

public class ElGamal {
    public static final String KEY_ALGORITHM = "ELGamal";
    /**
     * 密钥长度 范围 160-16384 8的整数倍 默认：1024
     */
    private static final int KEY_SIZE = 256;

    /**
     * 密钥初始化
     * @return
     */
    public static KeyPair initKey() throws Exception {
        // 算法参数生成器
        AlgorithmParameterGenerator apg = AlgorithmParameterGenerator.getInstance(KEY_ALGORITHM);
        apg.init(KEY_SIZE);
        AlgorithmParameters parameters = apg.generateParameters();
        DHParameterSpec paramSpec = parameters.getParameterSpec(DHParameterSpec.class);

        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.initialize(paramSpec, new SecureRandom());
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
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM,"BC");
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
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM,"BC");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        // 报错 Illegal key size or default parameters ，需要替换JCE安全策略文件
        // http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html
        Security.addProvider(new BouncyCastleProvider());
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
    }
}
