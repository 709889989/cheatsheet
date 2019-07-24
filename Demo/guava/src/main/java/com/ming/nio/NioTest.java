package com.ming.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author mingming.xu
 * @description: https://mp.weixin.qq.com/s/NpAvd0c2hn1_3g2XIZuw3Q
 * @date 2019/7/24 14:28
 * @Version 1.0
 */

public class NioTest {
    public static void main(String[] args) {
        // 相对路径
        Path dir = Paths.get("chenmo");
        // 输出 dir 的绝对路径
        System.out.println(dir.toAbsolutePath());
        if (Files.notExists(dir)) {
            try {
                // 如果目录不存在，则创建目录
                Files.createDirectory(dir);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        // 这时候 chenmo.txt 文件并未创建
        // 通过 resolve 方法把 dir 和 chenmo.txt 链接起来
        Path file = dir.resolve("chenmo.txt");
        // 输出 file 的绝对路径
        System.out.println(file.toAbsolutePath());

        if (Files.notExists(file)) {
            try {
                // 如果文件不存在，则创建文件
                Files.createFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        directory();
        walkFileTree();

    }

    private void toPath(){
        // toPath
        File file = new File("沉默王二.txt");
        Path path = file.toPath();
    }
    private void toFile(){
        // toFile
        Path path = Paths.get("沉默王二.txt");
        File file = path.toFile();
    }

    /**
     * 目录处理
     */
    public static void directory(){
        // 相对路径
        Path chenmo = Paths.get("chenmo");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(chenmo, "*.txt")) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历目录树
     */
    public static void walkFileTree(){
        // 相对路径
        Path dir = Paths.get("chenmo");

        try {
            Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (file.toString().endsWith(".txt")) {
                        System.out.println(file.getFileName());
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件操作，删除/复制/移动
     * @param file
     */
    public static void file(Path file) throws IOException {
        //delete
        Files.delete(file);
        Files.deleteIfExists(file);
        //copy
        Path source = Paths.get("沉默王二.txt");
        Path target = Paths.get("沉默王二1.txt");
        Files.copy(source, target);
        //move
//        Path source = Paths.get("沉默王二.txt");
//        Path target = Paths.get("沉默王二1.txt");
        Files.move(source, target);
    }

    /**
     * 文件读写
     */
    public static void readWrite(){
        Path file = Paths.get("沉默王二.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            writer.write("一个有趣的程序员");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步IO future
     */
    public static void asynIOFuture() throws IOException, ExecutionException, InterruptedException {
        Path file = Paths.get("沉默王二.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);
        Future<Integer> result = channel.read(ByteBuffer.allocate(100_000), 0);
        while (!result.isDone()) {
            System.out.println("主线程继续做事情");
        }

        Integer bytesRead = result.get();
        System.out.println(bytesRead);
    }

    /**
     * 异步IO callback
     */
    public static void asynIOCallback() throws IOException {
        Path file = Paths.get("沉默王二.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);
        channel.read(ByteBuffer.allocate(100_000), 0, null, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println(result);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println(exc.getMessage());
            }
        });

        System.out.println("主线程继续做事情");
    }
}
