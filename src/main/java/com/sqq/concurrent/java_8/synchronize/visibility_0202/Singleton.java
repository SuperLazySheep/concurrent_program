package com.sqq.concurrent.java_8.synchronize.visibility_0202;

/**
 * @author sqq
 *
 * DCl -- 单例模式
 */
public class Singleton {

    private static volatile Singleton SINGLETON = null;

    private Singleton(){};

    public static Singleton getInstance(){
        if(SINGLETON == null){
            synchronized (Singleton.class){
                if(SINGLETON == null){
                    SINGLETON = new Singleton();
                }
            }
        }
        return SINGLETON;
    }
}
