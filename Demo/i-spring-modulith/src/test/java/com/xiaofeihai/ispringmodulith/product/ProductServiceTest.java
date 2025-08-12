package com.xiaofeihai.ispringmodulith.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

/**
 *
 * @author xumingming
 * @version 1.0
 * @date 2025/8/8 15:21
 */
// 对单个应用程序模块运行集成测试。
@ApplicationModuleTest(mode = ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void test() {
        productService.create(new Product("产品", "描述", 10));
    }
    @Test
    public void testEvent() {
        productService.createEvent(new Product("产品", "描述", 10));
    }
}
