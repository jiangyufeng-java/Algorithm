package com.ty.dac;

/**
 * program : Algorithm
 * description : 汉诺达算法
 * author : jyf
 * date : 2020-09-15 10:04
 **/
public class Hanoi {
    public static Long count = 0L;

    public static void main(String[] args) {
        hanoi(64, 'A', 'B', 'C');
        System.out.println(count);
    }


    /**
     * 汉诺塔算法
     *
     * @param number
     * @param a
     * @param b      移动过程中使用的盘
     * @param c
     */
    public static void hanoi(int number, char a, char b, char c) {
        count++;
        if (number == 1) {
            // 说明他是最简单的步骤了
//            System.out.println("第 1 个盘从 " + a + "->" + c);
        } else {
            // 表示他是n>=2 的 可以看成上面的盘,和下面所有的盘
            // 1. 先把上面的盘移动到b  中间的数为移动过程中使用的盘
            hanoi(number - 1, a, c, b);
            // 2.把最下边的盘 A->C
//            System.out.println("第 "+number+" 个盘从 "+a+"->"+c);
            // 3 把所有b的盘到c
            hanoi(number - 1, b, a, c);
        }

    }
}
