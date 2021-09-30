package com.sqq.test.java_8.arithmetic.linkedlist;

import java.util.Arrays;

/**
 * @author sqq
 * @Date 2021/8/25
 * 快排
 */
public class QuickSort {

    /**
     * 实现快速排序
     * @param num
     * @param i
     * @param j
     */
    public static void quickSort(int[] num,int i,int j){
        //只剩一个元素不用处理直接结束
        if(i >= j){
            return;
        }
        //选取基准数
        int val =num[i];
        int l = i;
        int r = j;
        //当l == r时，就是调整完成时
        while(l < r){
            //从后往前找第一个小于val的数字
            while (l < r && num[r] > val){
                r --;
            }
            //找到了数字
            if(l < r){
                num[l++] = num[r];
            }
            //从前往后找第一个大于val的数字
            while (l < r && num[l] < val){
                l ++;
            }
            //找到了数字
            if(l < r){
                num[r--] = num[l];
            }
        }
        //l==r,基准数放进去
        num[l] = val;
        quickSort(num,i,l-1);
        quickSort(num,l+1,j);
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 22, 9, 10, 3};
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort2(int[] num, int i, int j){
        if(i >=j ){
            return;
        }
    }
}
