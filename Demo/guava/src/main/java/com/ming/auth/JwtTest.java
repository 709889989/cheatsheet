package com.ming.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xu.mingming
 * JWt token认证方案
 *
 * iss (issuer)：签发人
 * exp (expiration time)：过期时间
 * sub (subject)：主题
 * aud (audience)：受众
 * nbf (Not Before)：生效时间
 * iat (Issued At)：签发时间
 * jti (JWT ID)：编号
 *
 * 实践：https://juejin.im/post/5c1200ece51d4560f0435795
 */
public class JwtTest {
    public static void main(String[] args) {
        String token = createToken();
        System.out.println(token);
//        String token = "eyJhbGciOiJIUzM4NCIsInR5cCI6IkpXVCIsImhlbGxvIjoid29ybGQifQ.eyJ0ZXN0IjoidGVzdCIsImlzcyI6IumigeWPkeiAhSIsImhlbGxvIjoid29ybGQiLCJpYXQiOjE1NDM1NzA5NjZ9.htrJijN_QC9RZ6xS-FtiRBouzJE7RnRetHtaE6smYV92trMuwbJoPn2IBlwzv5Xf";
        verifyToken(token);
    }

    /**
     * 获取token
     * @return
     */
    public static String createToken(){
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 1);
        Date expiresDate = nowTime.getTime();

        Map<String, Object> header = new HashMap<>();
//        默认添加，不需要填写
//        header.put("alg", "HS256");
//        header.put("typ", "JWT");
        // 设置头信息
        header.put("hello", "world");
        String token = JWT.create()
                // header
                .withHeader(header)
                //payload
                .withClaim("hello", "world")
                .withClaim("test", "test")
                // 过期时间
                .withExpiresAt(expiresDate)
                // 颁发时间
                .withIssuedAt(iatDate)
                .withIssuer("颁发者")
                //签名算法
                .sign(Algorithm.HMAC384("hello"));

        return token;
    }

    public static void verifyToken(String token){
        // 验证token的签名有效性，是否过期，特定claim 是否正确
        JWTVerifier verifier = JWT.require(Algorithm.HMAC384("hello")).withIssuer("颁发者").build();
        DecodedJWT decodedJWT = verifier.verify(token);

//     * @throws AlgorithmMismatchException if the algorithm stated in the token's header it's not equal to the one defined in the {@link JWTVerifier}.
//     * @throws SignatureVerificationException if the signature is invalid.
//     * @throws TokenExpiredException if the token has expired.
//     * @throws InvalidClaimException if a claim contained a different value than the expected one.
    }

}
