package com.example.demo.controller.response;

import com.howbuy.tms.common.application.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author mingming.xu
 * @description:
 * @date 2020/12/15 15:40
 * @Version 1.0
 */
@Getter
@Setter
public class WorldResponse extends BaseResponse {
    private static final long serialVersionUID = 2166960579816971754L;

    /**
     * 姓名
     */
    @NotEmpty
    private String name;
    /**
     * 年龄
     */
    private String age;
    /**
     * 级别
     */
    private String level;
}
