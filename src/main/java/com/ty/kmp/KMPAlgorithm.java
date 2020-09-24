package com.ty.kmp;

import java.util.Arrays;

/**
 * program : Algorithm
 * description : KMP算法
 * author : jyf
 * date : 2020-09-16 16:11
 **/
public class KMPAlgorithm {

    public static void main(String[] args) {
        String str1 = "BBC ABCDABABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] ints = kmpNext(str2);
        System.out.println(Arrays.toString(ints));

        int index = kmpSearch(str1, str2, ints);
        System.out.println(index);
    }

    /**
     * @param str1 源字符串
     * @param str2 需要搜索的字符串
     * @param next next 数组
     * @return
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        // i表示str1 的索引, j 表示str2的
        for (int i = 0, j = 0; i < str1.length(); i++) {
            // 需要考虑他们不相等的情况下!
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                // 如果不相等的情况下,获取获取next数组的上一个元素有没有部分匹配的值
                // 然后拿到部分匹配值的下一个索引,继续比较,如果还是不相等,
                // 继续获取上一个元素有没有部分匹配值, 如果等于0说明是没有的
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                // 说明j已经完全找到了
                return i - j + 1;
            }
        }
        return -1;
    }


    public static int[] kmpNext(String str) {
        int[] next = new int[str.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < next.length; i++) {
            // 因为我们第一个可以直接跳过,所以我们直接比较第二个

            // 还有一种情况就是他不等于,不等于我们需要把j归到等等于0的位置上
            // 这里是精华所在, 如果i位置的字符串和J 不一样, 那么j退一步,比较一下, 如果还是没有继续退
            // 因为i一直会++;  所以如果j 一直跟i是一样的值,那么就有多长
            // 如果匹配到相等的了, 中途退出, 就能保留剩下的长度, 因为剩下的是索引,在下一个判断会为true,++ 以后就成了长度了! 相当于索引+1
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1];
            }

            // 如果str[i] == str[j] 说明有一样的部分匹配值+1
            if (str.charAt(i) == str.charAt(j)) {
                // 说明相等, 那么我们把j++, 这样为了匹配下一个
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
