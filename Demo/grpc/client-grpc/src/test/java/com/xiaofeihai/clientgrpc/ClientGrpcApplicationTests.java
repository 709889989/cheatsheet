package com.xiaofeihai.clientgrpc;

import hello.proto.SimpleGrpc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientGrpcApplicationTests {

    private SimpleGrpc.SimpleBlockingStub stub;
    @Test
    void contextLoads() {

    }

}
