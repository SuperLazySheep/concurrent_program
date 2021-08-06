package com.sqq.concurrent.java_8.synchronize.visibility_0202;

/**
 * @author sqq
 * @Date 2021/2/4
 */
public class Test {
    Person person = new Person("Test");

    static{
        System.out.println("test static");
    }
//    {
//        System.out.println("111");
//    }

    public Test() {
        System.out.println("test constructor");
    }

    public static void main(String[] args) {
        new MyClass();
    }
}

class Person{
    static{
        System.out.println("person static");
    }

    public Person(String str) {
        System.out.println("person "+str);
    }
}


class MyClass extends Test {
    Person person = new Person("MyClass");
    static{
        System.out.println("myclass static");
    }

    public MyClass() {
        System.out.println("myclass constructor");
    }
}
