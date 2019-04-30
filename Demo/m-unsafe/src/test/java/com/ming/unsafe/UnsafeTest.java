package com.ming.unsafe;

import com.ming.unsafe.UnsafeHolder;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author mingming.xu
 * @description: Unsafe 对象详细示例
 * @date 2019/4/29 16:29
 * @Version 1.0
 */

public class UnsafeTest {

    /**
     * 获取Unsafe 对象
     */
    @Test
    public void test(){
        Unsafe u = UnsafeHolder.getUnsafe();
        System.out.println(u.addressSize());
    }

    /**
     * 获取实例对象field 值
     */
    @Test
    public void getObjectFieldVal() throws NoSuchFieldException {
        TestObject o = new TestObject();
        ChildObject c = new ChildObject();
        Unsafe u = UnsafeHolder.getUnsafe();

        // 获取实例域的值
        Field objField = TestObject.class.getDeclaredField("objField");
        System.out.println("TestObject objField数据类型=int.class:" + (int.class == objField.getType()));

        long offset =u.objectFieldOffset(objField);
        int objFieldValue = u.getInt(o, offset);
        System.out.println("TestObject 实例域 objField=" + objFieldValue);

        int childValue = u.getInt(c, offset);
        System.out.println("ChildObject 实例域 objField=" + childValue);

        Field pObjField = TestObject.class.getDeclaredField("pObjField");
        Field childField = ChildObject.class.getField("pObjField");
        long parentOffset = u.objectFieldOffset(pObjField);
        long childOffset = u.objectFieldOffset(childField);
        // 子类继承父类内存结构，offset相同
        System.out.println("父类offset=" + parentOffset + " 子类offset=" + childOffset);

        short errVal1=u.getShort(objField, offset);
        long errVal2 = u.getLong(objField, offset);
        System.out.println("不同处理器获取值不一定正确："+errVal1);
        System.out.println("getLong返回值一定不正确："+errVal2);
    }

    /**
     * 获取静态域值
     */
    @Test
    public void getStaticFieldVal() throws NoSuchFieldException {
        // 加载TestObject 类并初始化，否则获取不到值
        TestObject testObject = new TestObject();

        Unsafe u = UnsafeHolder.getUnsafe();
        Field field=TestObject.class.getDeclaredField("clsField");
        long offset= u.staticFieldOffset(field);
        Object obj=u.staticFieldBase(field);
        int val1=u.getInt(TestObject.class,offset);
        System.out.println("TestObject静态域clsField=" + val1);
        int val2=u.getInt(obj,offset);
        System.out.println("TestObject静态域clsField=" + val2);
    }

    /**
     * 获取数组的值
     */
    @Test
    public void getArrayVal() throws NoSuchFieldException {
        int index = 0;
        int expectedVal = 1;
        Unsafe u = UnsafeHolder.getUnsafe();

        TestObject obj=new TestObject();
        Field field=TestObject.class.getDeclaredField("array");
        long offset= u.objectFieldOffset(field);
        int[] array= (int[]) u.getObject(obj,offset);
        System.out.println("TestObject 实例域 array=" + Arrays.toString(array));

        // 数组偏移量计算公式 B+N*S
        // B 是数组元素在数组中的基准偏移量
        // S 是每个元素占用的字节数
        // N 是数组元素的下标
        int base=u.arrayBaseOffset(int[].class);
        int scale=u.arrayIndexScale(int[].class);
        long arrayOffset = base+index*scale;
        int val1=u.getInt(array, arrayOffset);
        System.out.println("获取数组内的值, index=" + index + ", value=" + val1);
        int val2=u.getInt(obj.array,(long)base+index*scale);
        System.out.println("获取数组内的值, index=" + index + ", value=" + val2);
    }

    /**
     * 修改值
     */
    @Test
    public void setObjFieldVal() throws NoSuchFieldException {
        int newVal = 1;
        Unsafe u = UnsafeHolder.getUnsafe();

        Field field=TestObject.class.getDeclaredField("objField");
        long offset= u.objectFieldOffset(field);
        TestObject obj=new TestObject();
        u.putInt(obj,offset,newVal);
        int getVal= u.getInt(obj,offset);
        System.out.println("设置TestObject 实体域objField值为：" + getVal);

//        u.putLong(obj,offset,newVal);
//        Field fieldArray=TestObject.class.getDeclaredField("array");
//        long offsetArray= u.objectFieldOffset(fieldArray);
//        int[] array= (int[]) u.getObject(obj,offsetArray);
//        for(int i=0;i<array.length;i++){
//            System.out.println(array[i]);
//        }
    }


    /**
     * 和本地内存有关的几个方法
     * 包装malloc，realloc，free
     *
     * 分配指定大小的一块本地内存。分配的这块内存不会初始化，它们的内容通常是没用的数据
     * 返回的本地指针不会是 0，并且该内存块是连续的。调用 freeMemory 方法可以释放此内存，调用
     * reallocateMemory 方法可以重新分配
     *
     * public native long allocateMemory(long bytes);
     *
     * 重新分配一块指定大小的本地内存，超出老内存块的字节不会被初始化，它们的内容通常是没用的数据
     * 当且仅当请求的大小为 0 时，该方法返回的本地指针会是 0。
     * 该内存块是连续的。调用 freeMemory 方法可以释放此内存，调用 reallocateMemory 方法可以重新分配
     * 参数 address 可以是 null，这种情况下会分配新内存(和 allocateMemory 一样)
     *
     * public native long reallocateMemory(long address, long bytes);
     *
     * 将给定的内存块的所有字节设置成固定的值(通常是 0)
     * 该方法通过两个参数确定内存块的基准地址，就像在 getInt(Object,long) 中讨论的，它提供了 double-register 地址模型
     * 如果引用的对象是 null, 则 offset 会被当成绝对基准地址
     * 该写入操作是按单元写入的，单元的字节大小由地址和长度参数决定，每个单元的写入是原子性的。如果地址和长度都是 8 的倍数，则一个单元为 long
     * 型(一个单元 8 个字节)；如果地址和长度都是 4 的倍数，则一个单元为 int 型(一个单元 4 个字节)；
     * 如果地址和长度都是 2 的倍数，则一个单元为 short 型(一个单元 2 个字节)；
     *
     * public native void setMemory(Object o, long offset, long bytes, byte value);
     *
     * 将给定的内存块的所有字节设置成固定的值(通常是 0)
     * 就像在 getInt(Object,long) 中讨论的，该方法提供 single-register 地址模型
     *
     * public void setMemory(long address, long bytes, byte value)
     *
     * 复制指定内存块的字节到另一内存块
     * 该方法的两个基准地址分别由两个参数决定
     *
     * public native void copyMemory(Object srcBase, long srcOffset,
     *                                   Object destBase, long destOffset,
     *                                   long bytes);
     *
     * 复制指定内存块的字节到另一内存块，但使用 single-register 地址模型
     *
     * public void copyMemory(long srcAddress, long destAddress, long bytes)
     *
     * 释放通过 allocateMemory 或者 reallocateMemory 获取的内存，如果参数 address 是 null，则不做任何处理
     *
     * public native void freeMemory(long address);
     */
    /**
     * 本地内存操作方法
     */
    @Test
    public void allocateMemory(){
        Unsafe u = UnsafeHolder.getUnsafe();
        long address=u.allocateMemory(10);
        u.setMemory(address,10,(byte)1);
        /**
         * 1的二进制码为00000001，int为四个字节，U.getInt将读取四个字节，
         * 读取的字节为00000001 00000001 00000001 00000001
         */
        int i=0b00000001000000010000000100000001;
        System.out.println(i==u.getInt(address));
        u.freeMemory(address);
    }

    /**
     * 类初始化相关
     * shouldBeInitialized 判断类是否已初始化
     * ensureClassInitialized 执行初始化
     * final修饰的值在编译期就决定了，存放在类的常量表里
     */
    @Test
    public void clsInitialized(){
        Unsafe u = UnsafeHolder.getUnsafe();

        System.out.println("TestObject是否需要初始化：" + u.shouldBeInitialized(TestObject.class));
        System.out.println("ChildObject是否需要初始化：" + u.shouldBeInitialized(ChildObject.class));
        // ChildObject 执行初始化过程
        u.ensureClassInitialized(ChildObject.class);
        System.out.println("TestObject是否需要初始化：" + u.shouldBeInitialized(TestObject.class));
    }

    /**
     * 获取本地指针所占用的字节大小，值为 4 或者 8。其他基本类型的大小由其内容决定
     * addressSize 返回指针的大小，32 位虚拟机返回 4，64 位虚拟机默认返回 8，开启指针压缩功能（-XX:-UseCompressedOops）则返回 4
     * public native int addressSize();
     *
     * 本地内存页大小，值为 2 的 N 次方
     * public native int pageSize();
     */
    /**
     * CAS 操作方法
     * 如果变量的值为预期值，则更新变量的值，该操作为原子操作
     * 如果修改成功则返回true
     * compareAndSwapObject
     * compareAndSwapInt
     * compareAndSwapLong
     * @throws NoSuchFieldException
     */
    @Test
    public void cas() throws NoSuchFieldException {
        Unsafe u = UnsafeHolder.getUnsafe();

        Field field=TestObject.class.getDeclaredField("objField");
        long offset= u.objectFieldOffset(field);
        TestObject myObj=new TestObject();

        boolean r = u.compareAndSwapInt(myObj,offset,0,2);
        int objField = u.getInt(myObj, offset);
        System.out.println("compareAndSwapInt结果result=" + r + ", objField=" + objField);

        boolean b = u.compareAndSwapInt(myObj,offset,10,2);
        System.out.println("compareAndSwapInt结果result=" + b + ", objField=" + objField);
    }
    /**
     * putObject
     * putObjectVolatile
     * putOrderedObject
     * putXx 只是写本线程缓存，不会将其它线程缓存置为失效，所以不能保证其它线程一定看到此次修改；
     * putXxVolatile 相反，它可以保证其它线程一定看到此次修改；
     * putOrderedXx 也不保证其它线程一定看到此次修改，但和 putXx 又有区别，它的注释上有两个关键字：顺序性（Ordered）和延迟性（lazy）
     * ，顺序性是指不会发生重排序，延迟性是指其它线程不会立即看到此次修改，只有当调用 putXxVolatile 使才能看到。
     *
     * 释放当前阻塞的线程。如果当前线程没有阻塞，则下一次调用 park 不会阻塞。这个操作是"非安全"的
     * 是因为调用者必须通过某种方式保证该线程没有被销毁
     * public native void unpark(Object thread);
     *
     * 阻塞当前线程，当发生如下情况时返回：
     * 1、调用 unpark 方法
     * 2、线程被中断
     * 3、时间过期
     * 4、spuriously
     * 该操作放在 Unsafe 类里没有其它意义，它可以放在其它的任何地方
     *
     * public native void park(boolean isAbsolute, long time);
     *
     * 获取一段时间内，运行的任务队列分配到可用处理器的平均数(平常说的 CPU 使用率)
     *
     * public native int getLoadAverage(double[] loadavg, int nelems);
     *
     */
    @Test
    public void getLoadAverage(){
        Unsafe u = UnsafeHolder.getUnsafe();
        double[] d = new double[5];
        int i = u.getLoadAverage(d, 1);
        System.out.println(i);
    }
    /**
     * 面的方法包含基于 CAS 的 Java 实现，用于不支持本地指令的平台
     *
     * 在给定的字段或数组元素的当前值原子性的增加给定的值
     * getAndAddInt
     *
     * 将给定的字段或数组元素的当前值原子性的替换给定的值
     * getAndSetInt
     */
    //确保该栏杆前的读操作不会和栏杆后的读写操作发生重排序
    public native void loadFence();

    //确保该栏杆前的写操作不会和栏杆后的读写操作发生重排序
    public native void storeFence();

    //确保该栏杆前的读写操作不会和栏杆后的读写操作发生重排序
    public native void fullFence();


}
