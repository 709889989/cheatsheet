package org.xiaofeihai.symmetry;

import com.google.common.collect.Maps;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.core.codec.CodecException;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;

public final class AesUtil {

    /**
     * 算法/模式/填充
     */
    private static final String CIPHER_MODE = "AES/CBC/PKCS7Padding";
    private static final String AES = "AES";
    private static final String MD5_SALT = "@%";
    private static final int SALT_LEN = MD5_SALT.length();
    private static final String RANDOM_ALG = "SHA1PRNG";
    private static final IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[16]); // 16位向量
    private static final Map<String, SecretKey> secretKeyMap = Maps.newConcurrentMap();

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static SecretKey getSecretKey(String password) throws Exception {
        SecretKey secretKey = secretKeyMap.get(password);
        if (Objects.nonNull(secretKey)) {
            return secretKey;
        }
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        SecureRandom random = SecureRandom.getInstance(RANDOM_ALG);
        random.setSeed(password.getBytes(StandardCharsets.UTF_8));
        keyGenerator.init(256, random); // 128/192/256
        secretKey = keyGenerator.generateKey();

        secretKeyMap.put(password, secretKey);
        return secretKey;
    }

    /**
     * 加密字符串数据
     *
     * @param content 待加密字符串
     * @param key     秘钥
     * @return 加密密文
     */
    public static String encrypt(String content, String key) {
        try {
            SecretKey secretKey = getSecretKey(key);
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            byte[] data = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().encodeToString(data);
        } catch (Exception e) {
            throw new CodecException("加密失败", e);
        }
    }

    /**
     * 解密
     *
     * @param content 密文
     * @param key     秘钥
     * @return 密文对应原文
     */
    public static String decrypt(String content, String key) {
        try {
            SecretKey secretKey = getSecretKey(key);
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] data = cipher.doFinal(Base64.getUrlDecoder().decode(content));
            return new String(data, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new CodecException("解密失败", e);
        }
    }

    /**
     * md5加盐
     */
    public static String md5Salt(String originKey) {
        String md5 = DigestUtils.md5DigestAsHex(originKey.getBytes(StandardCharsets.UTF_8));
        StringBuilder res = new StringBuilder(32 + SALT_LEN); // md5长度固定为32
        res.append(md5);

        for (int i = 1; i <= SALT_LEN; i++) {
            res.insert(i << 3, MD5_SALT.charAt(i - 1));
        }
        return res.toString();
    }

    /**
     * 指定位数随机生成字符串
     */
    public static String randomSalt(int num) {
        SecureRandom random = new SecureRandom();
        return Base64.getUrlEncoder().encodeToString(random.generateSeed(num));
    }

    /**
     * 判断是否加密密文
     */
    public static boolean isCipher(String text) {
        if (!StringUtils.hasText(text) || text.trim().length() <= 2) {
            return false;
        }
        String content = text.substring(0, text.trim().length() - 2);
        try {
            byte[] decode = Base64.getUrlDecoder().decode(content);
            return decode.length % 16 == 0;
        } catch (Exception e) {
            // 非法base64
            return false;
        }
    }

    public static void main(String[] args) {
//        {"01":"3cKQU23xXMgKh8UI"}
//        {"01":"xDYj/NqOKmOte13yMLkS3mD/GTUNWosh","02":"I1Xv7mouMYlRjGsY"}
//        http://hps.inner.howbuy.com/hps/ps?key=howbuyAuthKeyPart
//        {"01":"3cKQU23xXMgKh8UI"}
//        {"01":"xDYj/NqOKmOte13yMLkS3mD/GTUNWosh","02":"I1Xv7mouMYlRjGsY"}

        // 8c8f9f8a@e837577%1323f961d394f2f1c
        String key = AesUtil.md5Salt("3cKQU23xXMgKh8UI" + "e4SH3botDa33ys5g");
        System.out.println(key);
//        String cypherText = "g4l9IzfreE4_xQX3Qdx-Pw==";
        String cypherText = "nerMDvgcua0HbPAXP55nQlgaMSn00k1TKHOEOQY4uts=";
        String decrypt = AesUtil.decrypt(cypherText, key);
        System.out.println(decrypt);
    }

}
