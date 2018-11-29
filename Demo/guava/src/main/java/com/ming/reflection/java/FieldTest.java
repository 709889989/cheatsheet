package com.ming.reflection.java;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 //返回所有的public字段，包括其父类的，如果没有字段，返回空数组
 public Field[] getFields()
 //返回本类声明的所有字段，包括非public的，但不包括父类的
 public Field[] getDeclaredFields()
 //返回本类或父类中指定名称的public字段，找不到抛出异常NoSuchFieldException
 public Field getField(String name)
 //返回本类中声明的指定名称的字段，找不到抛出异常NoSuchFieldException
 public Field getDeclaredField(String name)

 //获取字段的名称
 public String getName()
 //判断当前程序是否有该字段的访问权限
 public boolean isAccessible()
 //flag设为true表示忽略Java的访问检查机制，以允许读写非public的字段
 public void setAccessible(boolean flag)
 //获取指定对象obj中该字段的值
 public Object get(Object obj)
 //将指定对象obj中该字段的值设为value
 public void set(Object obj, Object value)

 //返回字段的修饰符  通过Modifier类的静态方法进行解读 Modifier.isPublic(mod)
 public int getModifiers()
 //返回字段的类型
 public Class<?> getType()
 //以基本类型操作字段
 public void setBoolean(Object obj, boolean z)
 public boolean getBoolean(Object obj)
 public void setDouble(Object obj, double d)
 public double getDouble(Object obj)

 //查询字段的注解信息
 public <T extends Annotation> T getAnnotation(Class<T> annotationClass)
 public Annotation[] getDeclaredAnnotations()
 */

public class FieldTest {
    public static void main(String[] args) {
        Class c = Childen.class;
        /**
         * 获取类和父类中的public字段
         */
        Field[] fields = c.getFields();
        /**
         * 获取类中声明的字段(包括private字段)
         */
        Field[] declaredField = c.getDeclaredFields();

        for (Field field: declaredField) {
            String name = field.getName();
            boolean isAccessible = field.isAccessible();

            System.out.println("java name:" + name + " isAccessible: " + isAccessible);
        }
        for (Field field: declaredField) {
            String name = field.getName();
            field.setAccessible(true);

            System.out.println("java name:" + name + " isAccessible: " + field.isAccessible());
            int modifiers = field.getModifiers();
            System.out.println(Modifier.isPublic(modifiers));
        }
    }
}
