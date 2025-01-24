package com.xiaofeihai.sse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author xumingming
 * @version 1.0
 * @date 2025/1/24 13:27
 */
@Controller
public class SseController {

    @GetMapping(value = "/sse/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<String>> streamSse() {
        Flux<String> flux = Flux.interval(Duration.ofSeconds(1)).map(i -> "message " + i);
        return ResponseEntity.ok().body(flux);
    }

    @GetMapping(value = "/user-activity-sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamUserActivitySSE() {
        // 创建一个持续时间为指定秒数的 Flux 流
        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(sequence -> Mono
                        .fromCallable(()-> "message " + sequence)
                        .onErrorReturn("error")
                );
    }
    @GetMapping("/sse/stream2")
    public Flux<ServerSentEvent<String>> streamSse2() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data("Current time: " + LocalTime.now())
                        .build());
    }
    @GetMapping("/stream")
    public SseEmitter stream() {
        // 3S超时
        SseEmitter emitter = new SseEmitter(10000L);

        // 注册回调函数，处理服务器向客户端推送的消息
        emitter.onCompletion(() -> {
            System.out.println("Connection completed");
            // 在连接完成时执行一些清理工作
        });

        emitter.onTimeout(() -> {
            System.out.println("Connection timeout");
            // 在连接超时时执行一些处理
            emitter.complete();
        });

        // 在后台线程中模拟实时数据
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    emitter.send(SseEmitter.event().name("message").data("[" + new Date() + "] Data #" + i));
                    Thread.sleep(1000);
                }
                emitter.complete(); // 数据发送完成后，关闭连接
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e); // 发生错误时，关闭连接并报错
            }
        }).start();

        return emitter;
    }
}