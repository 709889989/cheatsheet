package com.ming.testcase;

import org.junit.*;


/**
 * Junit测试各个注解的执行顺序
 *  @BeforeClass  所有测试之前运行 -- 只运行一次
 *  @Before  在@Test 方法前运行 -- 每个@Test 之前运行一次
 *  @Test  测试案例
 *  @After  在@Test 方法后运行 -- 每个@Test 之前运行一次
 *  @AfterClass  所有测试结束后运行 -- 只运行一次
 *  @Ignore  用来忽略有关不需要执行的测试
 *
 *  @Before、@After、@BeforeClass、@AfterClass 的区别：
 *      @Before、@After 会在每个@Test 测试用例前后都运行一次，
 *      @BeforeClass、@AfterClass 每个测试类中仅运行一次
 */
public class JunitAnnotationTest {

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
        System.out.println("@Test 测试用例1");
    }

    //test case ignore and will not execute
    @Ignore
    public void ignoreTest() {
        System.out.println("@Ignore");
    }

    //test case
    @Test
    public void test2() {
        System.out.println("@Test 测试用例2");
    }
}