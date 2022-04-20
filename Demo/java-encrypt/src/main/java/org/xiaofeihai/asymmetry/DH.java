package org.xiaofeihai.asymmetry;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mingming.xu
 * @description:
 * @date 2022/4/20 12:21
 * @Version 1.0
 */

public class DH {
    /**
     * 非对称加密密钥算法
     */
    private static final String KEY_ALGORITHM = "DH";
    /**
     * 本地密钥算法，即对称加密密钥算法
     * 可选DES、DESede或者AES
     */
    private static final String SELECT_ALGORITHM = "AES";
    /**
     * 密钥长度
     * DH 算法默认长度 1024
     * 密钥长度必须为64的倍数，范围再512-1024
     */
    private static final int KEY_SIZE = 512;
    //公钥
    private static final String PUBLIC_KEY = "DHPublicKey";
    //私钥
    private static final String PRIVATE_KEY = "DHPrivateKey";

    /**
     * 初始化密钥
     * @return Map 密钥Map
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception{
        //实例化密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥对生成器
        keyPairGenerator.initialize(KEY_SIZE);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //甲方公钥
        DHPublicKey publicKey = (DHPublicKey)keyPair.getPublic();
        //甲方私钥
        DHPrivateKey privateKey = (DHPrivateKey)keyPair.getPrivate();
        //将密钥对存储在Map中
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 初始化密钥
     * @param key 甲方公钥
     * @return Map 密钥Map
     * @throws Exception
     */
    public static Map<String, Object> initKey(byte[] key) throws Exception{
        //解析甲方公钥
        //转换公钥材料
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //由甲方公钥构建乙方密钥
        DHParameterSpec dhParameterSpec = ((DHPublicKey)pubKey).getParams();
        //实例化密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //初始化密钥对生成器
        keyPairGenerator.initialize(dhParameterSpec);
        //产生密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //乙方公钥
        DHPublicKey publicKey = (DHPublicKey) keyPair.getPublic();
        //乙方私约
        DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
        //将密钥对存储在Map中
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 加密
     * @param data 待加密数据
     * @param key 密钥
     * @return byte[] 加密数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception{
        //生成本地密钥
        SecretKey secretKey = new SecretKeySpec(key, SELECT_ALGORITHM);
        //数据加密
        Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    /**
     * 解密
     * @param data 待解密数据
     * @param key 密钥
     * @return byte[] 解密数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception{
        //生成本地密钥
        SecretKey secretKey = new SecretKeySpec(key, SELECT_ALGORITHM);
        //数据揭秘
        Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    /**
     * 构建密钥
     * @param publicKey 公钥
     * @param privateKey 私钥
     * @return byte[] 本地密钥
     * @throws Exception
     */
    public static byte[] getSecretKey(byte[] publicKey, byte[] privateKey) throws Exception{
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        //初始化私钥
        //密钥材料转换
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
        //产生私钥
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //实例化
        KeyAgreement keyAgree = KeyAgreement.getInstance(keyFactory.getAlgorithm());
        //初始化
        keyAgree.init(priKey);
        keyAgree.doPhase(pubKey, true);
        //生成本地密钥
        SecretKey secretKey = keyAgree.generateSecret(SELECT_ALGORITHM);
        return secretKey.getEncoded();
    }

    /**
     * 取得私钥
     * @param keyMap 密钥Map
     * @return byte[] 私钥
     * @throws Exception
     */
    public static byte[] getPrivateKey(Map<String, Object> keyMap) throws Exception{
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /**
     * 取得公钥
     * @param keyMap 密钥Map
     * @return byte[] 公钥
     * @throws Exception
     */
    public static byte[] getPublicKey(Map<String, Object> keyMap) throws Exception{
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }
    /**
     * 组装DHPublicKey公钥
     * @param p
     * @param g
     * @param y
     * @return
     * @throws Exception
     */
    public static byte[] getDHPublicKey(String p, String g, String y) throws Exception {
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        DHPublicKeySpec spec = new DHPublicKeySpec(new BigInteger(y), new BigInteger(p), new BigInteger(g));
        PublicKey publicKey = keyFactory.generatePublic(spec);

        return publicKey.getEncoded();
    }

    /**
     * 获取 publicKeySpec 参数
     * @param key
     * @return
     * @throws Exception
     */
    public static DHPublicKeySpec getPublicKeySpec(byte[] key) throws Exception {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance("DH");
        //产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        DHPublicKeySpec spec = keyFactory.getKeySpec(pubKey, DHPublicKeySpec.class);
        return spec;
    }

    public static void main(String[] args) throws Exception {
        // 甲方公私钥
        Map<String, Object> keyMap1 = initKey();
        byte[] publicKey1 = getPublicKey(keyMap1);
        byte[] privateKey1 = getPrivateKey(keyMap1);
//        System.out.println("甲方公钥: " + Base64.encode(publicKey1));
//        System.out.println("甲方私钥: " + Base64.encode(privateKey1));

        // 由甲方公钥生成乙方公私钥
        Map<String, Object> keyMap2 = initKey(publicKey1);
        byte[] publicKey2 = getPublicKey(keyMap2);
        byte[] privateKey2 = getPrivateKey(keyMap2);
//        System.out.println("乙方公钥: " + Base64.encode(publicKey2));
//        System.out.println("乙方私钥: " + Base64.encode(privateKey2));

        byte[] key1 = getSecretKey(publicKey2, privateKey1);
        byte[] key2 = getSecretKey(publicKey1, privateKey2);
        System.out.println("甲方密钥: " + Base64.encode(key1));
        System.out.println("乙方密钥: " + Base64.encode(key2));

        byte[] enData = encrypt("hello".getBytes(), key1);
        byte[] deData = decrypt(enData, key2);
        System.out.println(new String(deData));

        // illegal Key Size 报错需要替换 JCE 无限制权限策略文件
    }
}
