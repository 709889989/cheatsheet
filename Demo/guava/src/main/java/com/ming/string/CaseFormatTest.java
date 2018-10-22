package com.ming.string;

import com.google.common.base.CaseFormat;


/**
 * 变量名命名格式转换
 * 参考：https://www.yiibai.com/guava/guava_caseformat.html
 */
public class CaseFormatTest {
    public static void main(String[] args) {
        String data = "test_data";
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));
//        testData
//        testData
//        TestData
    }
}
