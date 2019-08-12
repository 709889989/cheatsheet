package com.example.demo;

import com.howbuy.tms.batch.facade.query.queryrefundorder.QueryRefundOrderFacade;
import com.howbuy.tms.batch.facade.query.queryrefundorder.QueryRefundOrderRequest;
import com.howbuy.tms.batch.facade.query.queryrefundorder.QueryRefundOrderResponse;
import com.howbuy.tms.common.client.BaseRequest;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
