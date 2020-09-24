package com.ty.dijkstra;

import java.util.Arrays;

/**
 * program : Algorithm
 * description : 迪杰斯特拉算法
 * author : jyf
 * date : 2020-09-22 17:47
 **/
public class DijkstraAlgorithm {

    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};     //A
        matrix[1]=new int[]{5,N,N,9,N,N,3};     //B
        matrix[2]=new int[]{7,N,N,N,8,N,N};     //C
        matrix[3]=new int[]{N,9,N,N,N,4,N};     //D
        matrix[4]=new int[]{N,N,8,N,N,5,4};     //E
        matrix[5]=new int[]{N,N,N,4,5,N,6};     //F
        matrix[6]=new int[]{2,3,N,N,4,6,N};     //G
        Graph graph = new Graph(vertex, matrix);
        graph.print();
        graph.dijkstra(2);
    }
}

class Graph{
    private char[] vertex; // 顶点数组
    private int[][] matrix;// 邻接矩阵
    private int[] distance;// 存放源点到其他点的矩离


    public Graph(char[] vertex,int[][] matrix){
        this.vertex = vertex;
        this.matrix = matrix;
        this.distance = new int[vertex.length];
    }

    public void print(){
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 迪杰斯特拉算法
     * @param index 从哪个节点开始
     */
    public void dijkstra(int index){
        // 更新源点到其他点的记录
        distance = matrix[index];
        boolean[] st = new boolean[distance.length];// 默认初始为false
        distance[index] = 0; // 标记自己为0
        // 把源点标记成已访问
        st[index] = true;
        // 因为我们遍历的点直接可以排除自身所以-1
        for (int i = 0; i < distance.length - 1; i++) {
            // min 代表最小索引对应的权值,也就是 A-B点的路径,方便后面需要
            int min = 65535; // 这个是代码中定义的访问不通的路径多大
            int minIndex = -1;
            // 遍历寻找当前这个最小的索引
            for (int j = 0; j < distance.length; j++) {
                // 需要找到未访问过的最小索引也就是从[2, 3, 65535, 65535, 4, 6, 65535] 去找
                if (!st[j]){
                    // 表示还没访问过
                    if (distance[j] < min){
                        min = distance[j];
                        minIndex = j;
                    }
                }
            }
            // 判断索引是否还是-1
            if (minIndex != -1) {
                st[minIndex] = true;
                // 保存当前最小的索引
                int[] matrix = this.matrix[minIndex];
                // 遍历这个点能访问到的所有的节点
                for (int j = 0; j < matrix.length; j++) {
                    // 排除不能访问到的
                    if (matrix[j] < 65535
                            // 比较当前节点到J 这个索引节点的距离,加上源节点到当前节点的距离, 如果小于源节点记录到这个节点距离,那么替换
                            && (matrix[j] + min) < distance[j]
                    ) {
                        distance[j] = matrix[j] + min;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(distance));
    }
}
