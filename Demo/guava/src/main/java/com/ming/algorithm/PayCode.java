package com.ming.algorithm;

import java.util.Random;
import java.util.UUID;

/**
 * 钱包扫码支付、二维码支付，付款码生成算法
 */
public class PayCode {
    public static void main(String[] args) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        //生成UUID
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //转换字母为数字
        for (char c : uuid.toCharArray()) {
            stringBuilder.append(Integer.valueOf(c));
        }
        //生成随机位数
        int seed = new Random().nextInt(uuid.length() - 15);
        //付款码
        StringBuilder payCode = new StringBuilder("99");
        //拼装标识:2静态 1 动态
        payCode.append("2");
        //截取UUID
        payCode.append(stringBuilder.substring(seed, seed + 15));

        System.out.println(payCode.toString());
    }
}
