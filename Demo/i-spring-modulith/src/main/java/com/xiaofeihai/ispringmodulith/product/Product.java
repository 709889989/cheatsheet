package com.xiaofeihai.ispringmodulith.product;

import lombok.Data;

/**
 *
 * @author xumingming
 * @version 1.0
 * @date 2025/8/8 14:54
 */
@Data
public class Product {

    private String name;
    private String description;
    private int price;

    public Product(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // 省略 get/set 方法

}
