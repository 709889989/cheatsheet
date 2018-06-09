package com.ming.testcase;

import org.junit.*;


/**
 * Junit测试各个注解的执行顺序
 *  @BeforeClass
 *  @Before
 *  @Test
 *  @After
 *  @AfterClass
 */
public class JunitAnnotation {

    //execute before class
    @BeforeClass
    public static void beforeClass() {
        System.out.println("@BeforeClass");
    }

    //execute after class
    @AfterClass
    public static void  afterClass() {
        System.out.println("@AfterClass");
    }

    //execute before test
    @Before
    public void before() {
        System.out.println("@Before");
    }

    //execute after test
    @After
    public void after() {
        System.out.println("@After");
    }

    //test case
    @Test
    public void test() {
        System.out.println("@Test");
    }

    //test case ignore and will not execute
    @Ignore
    public void ignoreTest() {
        System.out.println("@Ignore");
    }
}