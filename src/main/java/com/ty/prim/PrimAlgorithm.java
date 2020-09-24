package com.ty.prim;

import java.util.Arrays;

/**
 * program : Algorithm
 * description : 普利姆算法
 * author : jyf
 * date : 2020-09-18 16:47
 **/
public class PrimAlgorithm {

    public static void main(String[] args) {
        //测试看看图是否创建 ok
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000 这个大数，表示两个点不联通
        int [][]weight=new int[][]{
                {10000,5,7,10000,10000,10000,2}, //A
                {5,10000,10000,9,10000,10000,3},  // B
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},};
        // 创建图对象
        MGraph graph = new MGraph(verxs);
        // 创建最小树对象
        MinTree minTree = new MinTree();
        // 构建图
        minTree.createGraph(graph, verxs, data, weight);
        minTree.show(graph);
        minTree.prim(graph, 1);

    }
}

// 创建一个最小生成树-> 也就是我们需要修的路
class MinTree {
    //创建图的邻接矩阵

    /**
     *
     * @param graph  传入的图对象
     * @param verxs  顶点个数
     * @param data   顶点的值
     * @param weight  图的邻接矩阵
     */
    public void createGraph(MGraph graph,int verxs,char[] data, int[][] weight){
        for (int i = 0; i <verxs; i++) {
            // 外层遍历的是节点
            graph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                // 内层就是把所有的边都连起来
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void show(MGraph graph){
        for (int[] ints : graph.weight) {
            System.out.println(Arrays.toString(ints));
        }
    }

    // prim算法

    /**
     * 生成最小树 普利姆算法
     * @param graph 图
     * @param v 从哪个顶点开始
     */
    public void prim(MGraph graph , int v){
        // 先定义出来一个临时最小边
        int minWeight = 10000;
        // 定义两个下标, 待会记录对应的两个顶点
        int h1 = -1;
        int h2 = -1;
        // 需要定义一个临时记录已访问过的顶点
        int[] graphTemp = new int[graph.verxs];
        graphTemp[v] = 1; // 1表示已经访问过了
        // 因为我们生成的最小树是最大节点数-1
        // K 这里代表我们每遍历一次需要找出从graphTemp里面为1位置的节点开始他的最小相邻的节点
        // 也就是每次我们都需要去访问过的节点里面去找所有节点中最小的下一个顶点
        for (int k = 1; k < graph.verxs; k++) {

            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    if (graphTemp[i] == 1  // 表示这个节点是被访问过的,就需要去看他相邻节点的路径找出最小的
                            && graphTemp[j] != 1  // 到的节点肯定不能为已访问过的
                            && minWeight > graph.weight[i][j] // 如果我们上次记录的最小路径,大于当前两个节点之间的路径,就把min替换成当前
                    ){
                        // 替换掉最小路径
                        minWeight = graph.weight[i][j];
                        // 记录下标,方便后面输出
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            // 每次遍历完了都可以找到当前最小路径的两个节点
            System.out.println("<"+graph.data[h1]+","+graph.data[h2]+">的路径长度为"+minWeight);
            minWeight = 10000;
            // 还要注意把这次被选中的节点标记为1
            graphTemp[h2] = 1;
        }
    }
}


class MGraph {
    int verxs;  // 表示节点的个数
    char[] data;  // 存放节点的数据  也就是 "A" , "B"...
    int[][] weight; // 存放边


    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }

}
