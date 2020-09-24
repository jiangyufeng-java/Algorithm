package com.ty.greedy;

import java.util.*;

/**
 * program : Algorithm
 * description : 贪心算法
 * author : jyf
 * date : 2020-09-18 10:09
 **/
public class GreedyAlgorithm {

    public static void main(String[] args) {
        // 首先定义出广播台,和覆盖地区的对应关系
        Map<String, HashSet<String>>  broadcasts = new HashMap<String, HashSet<String>>();
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");


        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");


        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);
        // 定义出所有地区的集合
        HashSet<String> address = new HashSet<String>();
        address.addAll(hashSet1);
        address.addAll(hashSet2);
        address.addAll(hashSet3);
        address.addAll(hashSet4);
        address.addAll(hashSet5);

        List<String> key = new ArrayList<>();
        // 循环遍历地区集合
        while (address.size() != 0 ){
            String maxKey = null;
            int max = 0;
            // 遍历广播电台,获取对应关系
            for (Map.Entry<String, HashSet<String>> bro : broadcasts.entrySet()) {
                HashSet<String> temp = new HashSet<>();
                temp.addAll(bro.getValue());
                // 取当前set集合和 地区集合的交集,
                temp.retainAll(address);
                 // 如果取交集过后的长度大于0 说明这个地区都被覆盖了直接跳过
                // 既然前提满足了, 还要满足一个条件就是当前广播台覆盖的地址个数 大于 之前记录的最大,
                // 或者他是第一次为空, 那就直接使用当前的即可.
                if (temp.size() > 0 && (maxKey == null || temp.size() >  max)){
                    maxKey = bro.getKey();
                    max = temp.size();
                }
            }
            // 删除地区里面的集合,并且把这个key加入到集合中
            if (maxKey != null){
                key.add(maxKey);
                // 删除地区, 这样下次取并集,就剔除掉已经加入的地区了
                address.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println(key);

    }
}
