package com.example.demo.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @RequestMapping("/hello")
    public String hello(){
        logger.info("执行方法.........");
        return "hello world";
    }
}
