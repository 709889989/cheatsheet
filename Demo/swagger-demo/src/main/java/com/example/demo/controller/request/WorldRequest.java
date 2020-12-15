package com.example.demo.controller.request;

import com.howbuy.tms.common.application.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author mingming.xu
 * @description:
 * @date 2020/12/15 15:38
 * @Version 1.0
 */
@Getter
@Setter
public class WorldRequest extends BaseRequest {
    private static final long serialVersionUID = 3453695098977944616L;
    /**
     * 姓名
     */
    @NotBlank
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
