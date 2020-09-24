package com.ty.dynamic;

/**
 * program : Algorithm
 * description : 动态规划(背包问题)
 * author : jyf
 * date : 2020-09-15 15:40
 **/
public class KnapsackProblem {

    public static void main(String[] args) {

        int[] w = {1, 4, 3};// 表示物品的重量
        int[] val = {1500, 3000, 2000};// 物品的价值
        // 定义一个二维数组表示图
        int m = 4; //背包的容量
        int[][] v = new int[w.length + 1][m + 1];
        int[][] path = new int[w.length + 1][m + 1];


        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                // 判断当前容量是否大于当前物品的容量
                if (j >= w[i - 1]) {
                    // 如果当前容量大于物品容量
                    // v[i-1][j]获取上一个累加的值
                    // val[i-1]+v[i-1][j-w[i-1]]  获取当前商品的价值+剩余容量的最大值
                    // v[i-1][j-w[i-1]] 获取减去当前的容量剩下的最大值,
//                    v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    // 拆分出来看到路径
                    if (v[i - 1][j] > val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = v[i - 1][j];
                    } else {
                        // 如果使用了当前商品,那么我们就标记一下这个路径
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    }
                } else {

                    // 如果不大于当前容量,直接用上一份的就行了
                    v[i][j] = v[i - 1][j];
                }
            }
        }

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("=========================");

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(path[i][j]+" ");
            }
            System.out.println();
        }

        // 获取出我们的最佳方案
        // 我们的路径需要从后往前遍历
        int n = v.length - 1; // 这是行
        int j = v[0].length - 1;// 这是列
        while (n > 0 && j > 0) {
            if (path[n][j]==1){
                // 说明匹配到了路径
                System.out.println("放进去了第"+n+"个商品");
                // 需要减掉当前的背包
                j=m-w[n-1];
            }
            // 表示上一个商品
            n--;
        }


    }
}
