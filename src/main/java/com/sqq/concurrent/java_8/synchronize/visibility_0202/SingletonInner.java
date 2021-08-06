package com.sqq.concurrent.java_8.synchronize.visibility_0202;

/**
 * @author sqq
 *
 *  静态内部类 -- 单例模式
 */
public class SingletonInner {

    private SingletonInner(){};


    private static class SingletonHoler{
        private static SingletonInner SINGLETON = new SingletonInner();
    }

    public static SingletonInner getInstance(){
        return SingletonHoler.SINGLETON;
    }
}
