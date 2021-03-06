package com.xiaofeihai.proto;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author mingming.xu
 * @description:
 * @date 2020/12/25 15:21
 * @Version 1.0
 */

public class Test {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        //模拟将对象转成byte[]，方便传输
        PersonEntity.Person.Builder builder = PersonEntity.Person.newBuilder();
        builder.setId(1);
        builder.setName("ant");
        builder.setEmail("ghb@soecode.com");
        PersonEntity.Person person = builder.build();
        System.out.println("before :"+ person.toString());

        System.out.println("===========Person Byte==========");
        for(byte b : person.toByteArray()){
            System.out.print(b);
        }
        System.out.println();
        System.out.println(person.toByteString());
        System.out.println("================================");

        //模拟接收Byte[]，反序列化成Person类
        byte[] byteArray =person.toByteArray();
        PersonEntity.Person p2 = PersonEntity.Person.parseFrom(byteArray);
        System.out.println("after :" +p2.toString());
    }
}
