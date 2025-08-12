package com.xiaofeihai.ispringmodulith;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;


@SpringBootTest
class ISpringModulithApplicationTests {

    @Test
    void contextLoads() {
        // 创建应用程序模块模型并验证其结构
        ApplicationModules modules = ApplicationModules.of(ISpringModulithApplication.class).verify();
        // 打印模型信息
        modules.forEach(System.out::println);
        // 创建文档 build/sping-modulith-docs 目录
        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }

}
