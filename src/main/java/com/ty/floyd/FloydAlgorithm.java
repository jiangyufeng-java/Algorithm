package com.ty.floyd;

import java.util.Arrays;

/**
 * program : Algorithm
 * description : 弗洛伊德算法
 * author : jyf
 * date : 2020-09-23 16:28
 **/
public class FloydAlgorithm {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};     //A
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};     //B
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};     //C
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};     //D
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};     //E
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};     //F
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};     //G
        Graph graph = new Graph(vertex, matrix);
        graph.floyd();
        graph.print();
    }
}


class Graph {
    private char[] vertex;
    private int[][] matrix;// 邻接矩阵
    private int[][] pre;// 前驱节点


    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
        pre = new int[vertex.length][vertex.length];
        // 遍历pre把他的前驱节点默认为自己
        for (int i = 0; i < pre.length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    /**
     * 弗洛伊德算法
     */
    public void floyd() {
        // 这个表示中间的节点
        for (int i = 0; i < matrix.length; i++) {
            // 这个表示起始节点, j->i->K  i是中转点
            for (int j = 0; j < matrix.length; j++) {
                // 这个表示结束节点
                for (int k = 0; k < matrix.length; k++) {
                    // 如果ji+ik即为3个顶点的距离, 小于jk这个直接到的距离
                    if (matrix[j][i]+matrix[i][k] < matrix[j][k]){
                        matrix[j][k] = matrix[j][i]+matrix[i][k];
                        // 更新前驱节点 修改为中间节点的j
                        pre[j][k] = pre[i][j];
                    }
                }
            }
        }
    }

    /**
     * 展示当前的矩阵
     */
    public void print() {
        System.out.print("   ");
        for (int j = 0; j < matrix.length; j++) {
            System.out.print(vertex[j] + ",   ");
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(vertex[i] + ": ");
            System.out.println(Arrays.toString(matrix[i]));
        }

        System.out.println("-----------");
        for (int i = 0; i < pre.length; i++) {
            System.out.println(Arrays.toString(pre[i]));
        }

    }

}