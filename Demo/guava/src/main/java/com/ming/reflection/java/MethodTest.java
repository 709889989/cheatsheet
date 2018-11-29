package com.ming.reflection.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 //返回所有的public方法，包括其父类的，如果没有方法，返回空数组
 public Method[] getMethods()
 //返回本类声明的所有方法，包括非public的，但不包括父类的
 public Method[] getDeclaredMethods()
 //返回本类或父类中指定名称和参数类型的public方法，找不到抛出异常NoSuchMethodException
 public Method getMethod(String name, Class<?>... parameterTypes)
 //返回本类中声明的指定名称和参数类型的方法，找不到抛出异常NoSuchMethodException
 public Method getDeclaredMethod(String name, Class<?>... parameterTypes)

 获取方法的信息，调用对象的方法

 //获取方法的名称
 public String getName()
 //flag设为true表示忽略Java的访问检查机制，以允许调用非public的方法
 public void setAccessible(boolean flag)
 //在指定对象obj上调用Method代表的方法，传递的参数列表为args
 public Object invoke(Object obj, Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException

 获取方法的修饰符、参数、返回值、注解等信息

 //获取方法的修饰符，返回值可通过Modifier类进行解读
 public int getModifiers()
 //获取方法的参数类型
 public Class<?>[] getParameterTypes()
 //获取方法的返回值类型
 public Class<?> getReturnType()
 //获取方法声明抛出的异常类型
 public Class<?>[] getExceptionTypes()
 //获取注解信息
 public Annotation[] getDeclaredAnnotations()
 public <T extends Annotation> T getAnnotation(Class<T> annotationClass)
 //获取方法参数的注解信息
 public Annotation[][] getParameterAnnotations()
 */

public class MethodTest {
    public static void main(String[] args) throws IllegalAccessException {
        Class c = Childen.class;
        Method[] methods = c.getMethods();
        Method[] declaredMethods = c.getDeclaredMethods();

        Class<?> cls = Integer.class;
        try {
            Method method = cls.getMethod("parseInt", new Class[]{String.class});
            System.out.println(method.invoke(null, "123"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
