package com.ming;

/**
 * @author mingming.xu
 * @description:
 * @date 2019/7/30 11:23
 * @Version 1.0
 */

public class T {
    public static void main(String[] args) {
        sendMessage();
    }
    private String transferPayCancelMonitorQueueName;

    private static T instance = null;

    public T(){
        instance = this;
    }

    /**
     * 发送消息
     * @param dealNo
     */
    public static void sendMessage() {
        System.out.println(instance.transferPayCancelMonitorQueueName);

    }
}
