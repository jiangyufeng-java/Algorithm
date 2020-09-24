package com.ty.kmp;

/**
 * program : Algorithm
 * description : 暴力匹配
 * author : jyf
 * date : 2020-09-16 13:58
 **/
public class ViolenceMatch {

    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";        // 10
        String str2 = "尚硅谷你尚硅你";  // 7
        int i = violenceMatch(str1, str2);
        System.out.println(i);
    }

    /**
     * 暴力匹配算法
     *
     * @param str1 字符串1
     * @param str2 需要匹配的字符串
     * @return
     */
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int l1 = 0;  // 指向s1
        int l2 = 0;  // 指向s2

        while (l1 < s1.length && l2 < s2.length){
            if (s1[l1] == s2[l2]){
                // 表示匹配上了
                l1++;
                l2++;
            }else{
                // 表示不匹配
                l1 = l1 - l2 + 1;
                l2=0;
            }
        }
        if (l2 == s2.length){
            return l1-l2;
        }
        return -1;
    }

}
