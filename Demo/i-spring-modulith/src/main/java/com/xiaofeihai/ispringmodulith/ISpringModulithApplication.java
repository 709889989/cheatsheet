package com.xiaofeihai.ispringmodulith;

import com.xiaofeihai.ispringmodulith.product.Product;
import com.xiaofeihai.ispringmodulith.product.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ISpringModulithApplication {

    // 有两种模块间交互的方法：依赖其他模块的 Spring Bean 或使用事件

    public static void main(String[] args) {
        SpringApplication.run(ISpringModulithApplication.class, args);
    }

}
