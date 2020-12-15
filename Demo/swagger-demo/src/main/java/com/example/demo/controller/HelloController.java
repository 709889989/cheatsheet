package com.example.demo.controller;

import com.example.demo.controller.request.WorldRequest;
import com.example.demo.controller.response.WorldResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mingming.xu
 * @description:
 * @date 2020/12/15 15:37
 * @Version 1.0
 */
@Api(tags = "测试")
@RequestMapping("/hello")
@RestController
public class HelloController {

    /**
     * 测试
     * @param request
     * @return
     */
    @RequestMapping("/world")
    public WorldResponse world(@RequestBody WorldRequest request){
        return null;
    }
}
