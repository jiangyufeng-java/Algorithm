package com.ty.binarysearchnorecursion;

/**
 * program : Algorithm
 * description : 二分查找非递归
 * author : jyf
 * date : 2020-09-15 08:57
 **/
public class BinarySearchNoRecursion {

    public static void main(String[] args) {
        int[] arr = {1,3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 11);
        System.out.println("index=" + index);
    }


    /**
     * 二分查找算法
     * @param arr 数组
     * @param target 需要找的值
     * @return 索引
     */
    public static int binarySearch(int[] arr,int target){
        int left = 0;
        int right = arr.length-1;
        while (left <= right){
            int index = (left + right) / 2;
            if (arr[index] == target){
                return index;
            }else if (arr[index] < target){
                left = index + 1;
            }else{
                right = index - 1;
            }
        }
        return -1;
    }
}
