package com.sqq.test.java_7;

/**
 * @author sqq
 */
public class Single_Run {

    private Single_Run(){}

    private static class a{
        static {
                System.out.println("静态内部类");
        }

       Single_Run s =  new Single_Run();
      static final Integer k = 100;
    }

    public static Single_Run getInstance(){
        return new a().s;
    }

    public static Integer getInt(){
        return a.k;
    }

    public static void main(String[] args) {
            for (int i = 0; i < 100; i++){
                new Thread(() ->{
                    System.out.println(Single_Run.getInstance() + Thread.currentThread().getName());
                }).start();
            }
    }
}
