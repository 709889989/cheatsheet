package com.xiaofeihai.clientgrpc;

import com.xiaofeihai.clientgrpc.proto.HelloRequest;
import com.xiaofeihai.clientgrpc.proto.SimpleGrpc;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClientGrpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientGrpcApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(SimpleGrpc.SimpleBlockingStub stub) {
        return args -> {
            System.out.println(stub.sayHello(HelloRequest.newBuilder().setName("Alien").build()));
        };
    }
}
