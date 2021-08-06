package com.sqq.concurrent.java_8.juc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sqq
 * @Date 2021/1/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
}

class Test{
    public static void main(String[] args) {
//        Student s1 = new Student("小李");
//        Student s2 = new Student("小张");
//        swap(s1, s2);
//        System.out.println("s1: " + s1.getName() + s1.hashCode());
//        System.out.println("s2: " + s2.getName() + s2.hashCode());
//        String s = "ssssssssssssssssssssssssssssssss1";
//        StringBuffer stringBuffer = new StringBuffer("");
//        stringBuffer.append(s);
//        System.out.println(stringBuffer.toString());
//        stringBuffer.delete(0,stringBuffer.length());
//        System.out.println(stringBuffer);
        System.out.println( 9 >> 2);

    }

    public static  void swap(Student x, Student y){
        Student temp = x;
        x = y;
        y = temp;
        System.out.println("x: " + x.getName() + x.hashCode());
        System.out.println("y: " + y.getName() + y.hashCode());
        System.out.println(42 == 42.0);
        Long i = 1000L;
        Long j = 1000L;
        Long i2 = 100L;
        Long j2 = 100L;
        System.out.println(i == j);
        System.out.println( i2 == j2);

    }
}