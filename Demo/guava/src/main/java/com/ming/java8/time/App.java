package com.ming.java8.time;

import org.springframework.cglib.core.Local;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author mingming.xu
 * @description:
 * @date 2019/6/18 14:56
 * @Version 1.0
 */

public class App {
    public static void main(String[] args) {
//        Instant 时刻
//        LocalDateTime 时区无关的日期和时间信息
//        LocalDate 与时区无关的日期
//        LocalTime 与时区无关的时间
//        ZonedDateTime 特定时区的日期和时间
//        ZoneId/ZoneOffset 时区

        Instant now = Instant.now();
        Instant now2 = Instant.ofEpochMilli(System.currentTimeMillis());
        //Instant Date 转换
        //Instant.ofEpochMilli(new Date().getTime());
        //new Date(now.toEpochMilli());

        LocalDateTime ldt = LocalDateTime.now();
        //LocalDateTime ldt = LocalDateTime.of(2019, 1, 1, 1, 1);
//        ldt.getYear();
//        ldt.getMonthValue();
//        ldt.getDayOfMonth();
//        ldt.getHour();

        ldt.toInstant(ZoneOffset.of("+08:00"));
        ZoneId defaultZone = ZoneId.systemDefault();
        ZonedDateTime zoneTime = now.atZone(defaultZone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //线程安全
        System.out.println(formatter.format(ldt));
        String str = "2016-08-18 14:20:45";
        LocalDateTime localDateTime = LocalDateTime.parse(str, formatter);
        System.out.println(localDateTime);
    }
}
