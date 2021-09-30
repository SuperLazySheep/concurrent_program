package com.sqq.test.java_8.arithmetic.linkedlist;

import java.util.Arrays;

/**
 * @author sqq
 * @Date 2021/8/20
 */
public class MaoPao {

    /**
     * 终极版冒泡排序
     * 加入一个布尔变量,如果内循环没有交换值,说明已经排序完成,提前终止
     *
     * @param arr
     */
    public static void sortPlus(int[] arr) {
        if (arr != null && arr.length > 1) {
            for (int i = 0; i < arr.length - 1; i++) {
                // 初始化一个布尔值
                boolean flag = true;
                for (int j = 0; j < arr.length - i - 1; j++) {
                    if (arr[j] > arr[j + 1]) {
                        // 调换
                        int temp;
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                        // 改变flag
                        flag = false;
                    }
                }
                if (flag) {
                    break;
                }
            }
        }
    }


    public static void sort2(int[] arr) {
        //第一层for循环,用来控制冒泡的次数
        for (int i = 1; i < arr.length ; i++) {
            //第二层for循环,用来控制冒泡一层层到最后
            for (int j = 0; j < arr.length - i; j++) {
                //如果前一个数比后一个数大,两者调换 ,意味着泡泡向上走了一层
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 22, 9, 10, 3};
//        sortPlus(arr);
        sort2(arr);
//        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr));
    }

}
