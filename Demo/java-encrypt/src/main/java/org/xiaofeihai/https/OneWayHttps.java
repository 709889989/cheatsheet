package org.xiaofeihai.https;

import javax.net.ssl.*;
import javax.swing.*;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author mingming.xu
 * @description:
 * @date 2022/4/20 17:36
 * @Version 1.0
 */

public class OneWayHttps {

    private static final String PROTOCOL = "TLS";

    /**
     * 获取 KeyStore
     * @param keyStorePath
     * @param password
     * @return
     * @throws Exception
     */
    private static KeyStore getKeyStore(String keyStorePath, String password) throws Exception {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream is = new FileInputStream(keyStorePath);
        // 加载密钥库
        ks.load(is, password.toCharArray());
        is.close();
        return ks;
    }

    /**
     * 获取SSLSocketFactory
     * @param password
     * @param keyStorePath 密钥库路径
     * @param trustStorePath 信任库路径
     * @return
     */
    private static SSLSocketFactory getSSLSocketFactory(String password, String keyStorePath, String trustStorePath) throws Exception {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        // 获得密钥库
        KeyStore keyStore = getKeyStore(keyStorePath, password);
        // 初始化密钥工厂
        keyManagerFactory.init(keyStore, password.toCharArray());
        // 实例化信任库
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        // 获取信任库
        KeyStore trustStore = getKeyStore(trustStorePath, password);
        // 初始化信任库
        trustManagerFactory.init(trustStore);
        // 初始化SSL上下文
        SSLContext ctx = SSLContext.getInstance(PROTOCOL);
        ctx.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());

        return ctx.getSocketFactory();
    }

    /**
     * 配置 SSLSocketFactory
     * @param conn
     * @param password
     * @param keyStorePath
     * @param trustStorePath
     * @throws Exception
     */
    public static void configSSLSocketFactory(HttpsURLConnection conn, String password, String keyStorePath, String trustStorePath) throws Exception {
        // 获得SSLSocketFactory
        SSLSocketFactory sslSocketFactory = getSSLSocketFactory(password, keyStorePath, trustStorePath);
        conn.setSSLSocketFactory(sslSocketFactory);
    }

    public static void main(String[] args) throws Exception {
        String password = "";
        String keyStorePath = "xx.keystore";
        String trustStorePath = "xx.keystore";
        String urlPath = "https://www.baidu.com/";
        // 建立HTTPS链接
        URL url = new URL(urlPath);
        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
        conn.setDoInput(true);
        // 配置 SSLSocketFactory
        configSSLSocketFactory(conn, password, keyStorePath, trustStorePath);

        int length = conn.getContentLength();
        // length != -1 连接成功
        if(length != -1){
            conn.getInputStream();
        }
    }
}
