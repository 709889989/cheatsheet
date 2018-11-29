package com.ming.reflection.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * https://mp.weixin.qq.com/s?__biz=MzIxOTI1NTk5Nw==&mid=2650047510&idx=1&sn=6d8873ffbfd31e802d87ebf4ac135f39&chksm=8fde21c4b8a9a8d2df3eaec9a88ec9dfe748b2bfe7066cea0ea7c3dfc4f1f37d559305cccf7c&scene=21#wechat_redirect
 * newInstance()
 * Map<String,Integer> map = HashMap.class.newInstance();
 *
 //获取所有的public构造方法，返回值可能为长度为0的空数组
 public Constructor<?>[] getConstructors()
 //获取所有的构造方法，包括非public的
 public Constructor<?>[] getDeclaredConstructors()
 //获取指定参数类型的public构造方法，没找到抛出异常NoSuchMethodException
 public Constructor<T> getConstructor(Class<?>... parameterTypes)
 //获取指定参数类型的构造方法，包括非public的，没找到抛出异常NoSuchMethodException
 public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)

 创建对象 contructor.newInstance()

 //获取参数的类型信息
 public Class<?>[] getParameterTypes()
 //构造方法的修饰符，返回值可通过Modifier类进行解读
 public int getModifiers()
 //构造方法的注解信息
 public Annotation[] getDeclaredAnnotations()
 public <T extends Annotation> T getAnnotation(Class<T> annotationClass)
 //构造方法中参数的注解信息
 public Annotation[][] getParameterAnnotations()

 */
public class ConstructorTest {
    public static void main(String[] args) throws Exception {
        Constructor<StringBuilder> contructor= StringBuilder.class.getConstructor(new Class[]{int.class});
        StringBuilder sb = contructor.newInstance(100);

    }
}
