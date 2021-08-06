package com.sqq.concurrent.java_8.synchronize.cas_0205;

import lombok.Data;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author sqq
 * @Date 2021/2/7
 */
public class ReferenceUpdateTest {

    public static void main(String[] args) {
        Student student = new Student();
        AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");

        updater.compareAndSet(student, null, "宋小梦");
        System.out.println(student.name);
    }

}

@Data
@ToString
class Student{
        volatile String name;

}
