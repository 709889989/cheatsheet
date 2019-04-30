package com.ming.reactor;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mingming.xu
 * @description:
 * @date 2019/4/29 14:42
 * @Version 1.0
 */

public class Acceptor implements Runnable {
    private final ExecutorService executor = Executors.newFixedThreadPool(20);

    private final ServerSocketChannel serverSocket;

    public Acceptor(ServerSocketChannel serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            SocketChannel channel = serverSocket.accept();  // 获取客户端连接
            if (null != channel) {
                executor.execute(new Handler(channel));  // 将客户端连接交由线程池处理
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}