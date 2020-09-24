package com.ty.kruskal;

import java.util.Arrays;

/**
 * program : Algorithm
 * description : 克鲁斯卡尔算法
 * author : jyf
 * date : 2020-09-21 11:15
 **/
public class KruskalCase {

    private static final int INF = Integer.MAX_VALUE;
    private int edgeNum; // 边的数量
    private char[] vertexs; // 顶点数组
    private int[][] matrix; //邻接矩阵表示图

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        // 创建KruskalCase实例对象
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.print();
        kruskalCase.kruskal();
    }

    /**
     * 克鲁斯卡尔算法
     */
    private void kruskal() {
        // 记录每个顶点的父节点
        int[] ends = new int[vertexs.length];
        // 创建结果数组
        EData[] result = new EData[vertexs.length-1];
        // 获取边的数组
        EData[] eDataS = getEDataS();
        // 排序
        sortEDataS(eDataS);
        int index = 0;
        for (int i = 0; i < eDataS.length; i++) {
            // 因为是从小到大的,所以我们直接获取当前这个边即可
            // 判断他们两个的父节点是不是一样的
            int p1 = getEnd(ends,getPosition(eDataS[i].start));  // 获取在最小生成树中的父节点
            int p2 = getEnd(ends,getPosition(eDataS[i].end));
            // 判断是否相等
            if (p1 != p2){
                result[index++] = eDataS[i];
                ends[p1] = p2;  // 设置起始节点节点父节点,为了下次循环用
            }
        }
        System.out.println("---------------------------");
        System.out.println(Arrays.toString(result));

    }

    /**
     * 获取数组中对应下标的父节点
     * @param ends  父节点数组
     * @param position  需要获取的节点下标
     * @return  返回父节点下标
     */
    private int getEnd(int[] ends, int position) {
        while (ends[position] != 0){
            // 循环是因为,可能他的父节点还有父节点,我们要获取到最大的那个父节点
            position = ends[position];
        }
        // 如果第一次不成立表示等于0,所以这个数就是他本身自己
        return position;
    }

    /**
     * 根据字符获取对应的下标
     * @param start
     * @return
     */
    private int getPosition(char start) {
        for (int i = 0; i < vertexs.length; i++) {
            if (start == vertexs[i]){
                return i;
            }
        }
        return -1;
    }


    public KruskalCase(char[] vertexs, int[][] matrix) {
        int length = vertexs.length;
        // 顶点的个数就是矩阵的长款
        this.matrix = new int[length][length];
        // 顶点数组 拷贝的方式
        this.vertexs = vertexs.clone();
        // 矩阵也用copy
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        // 记录边的数量
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    // 打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵");
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    /**
     * 对EData进行排序
     */
    public void sortEDataS(EData[] eData){
        for (int i = 0; i < eData.length-1; i++) {
            for (int j = 0; j < eData.length-1-i; j++) {
                if (eData[j].weight > eData[j+1].weight){
                    EData e = eData[j];
                    eData[j] = eData[j+1];
                    eData[j+1] = e;
                }
            }
        }

    }
    /**
     * 返回所有边的对象
     */
    public EData[] getEDataS() {
        EData[] eData = new EData[edgeNum];
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (matrix[i][j] != INF) {
                    eData[index++]=new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return eData;
    }
}


class EData {

    char start; //边的一个点
    char end; //边的另外一个点
    int weight; //边的权值

    //构造器
    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    //重写 toString, 便于输出边信息
    @Override
    public String toString() {
        return "EData [<" + start + ", " + end + ">= " + weight + "]";
    }


}
