package com.sqq.test.java_8.arithmetic.linkedlist;

import java.util.Arrays;

/**
 * @author sqq
 * @Date 2021/8/20
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 22, 9, 10, 3};
//        sortPlus(arr);
        sort(arr);
//        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr){
        for(int i = 0; i < arr.length - 1 ; i++){
            // 遍历的区间最小的值
            int min = i;
            for (int j = i + 1; j < arr.length ;j++){
                if(arr[j] < arr[min]){
                    // 找到当前遍历区间最小的值的索引
                    min = j;
                }
            }
            if(min != i){
                // 发生了调换
                int temp =  arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }

}
