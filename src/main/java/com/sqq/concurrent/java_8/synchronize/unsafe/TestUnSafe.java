package com.sqq.concurrent.java_8.synchronize.unsafe;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author sqq
 * @Date 2021/3/12
 * unsafe对象  利用反射 对底层进行操作的
 */
public class TestUnSafe {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe =(Unsafe) theUnsafe.get(null);
        System.out.println(unsafe);

        //获取域的偏移地址
        long id = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("id"));
        long name = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("name"));
        Teacher teacher = new Teacher();
        //执行cas操作
        unsafe.compareAndSwapInt(teacher, id, 0, 1);
        unsafe.compareAndSwapObject(teacher, name, null, "宋小梦");
        //验证
        System.out.println(teacher);
    }
}

@Data
class Teacher{
    volatile int id;
    volatile String name;

    public void a(){
        System.out.println("q");
    }
    private void a(int m){}

    public int a(int m, float l){
        return 1;
    }


}
