package com.ty.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * program : Algorithm
 * description : 马踏棋盘算法
 * author : jyf
 * date : 2020-09-24 14:16
 **/
public class HorseChessboard {

    private static int X; // 表示列
    private static int Y; // 表示行
    public static int[][] chessboard; // 表示棋盘
    private static boolean[] visited; // 用一维数组表示二维数组
    private static boolean finished; // 表示棋盘是否走完


    public static void main(String[] args) {
        X = 10;
        Y = 10;
        chessboard = new int[Y][X];
        visited = new boolean[X * Y];
        traversalChessboard(chessboard,0,0,1);

        for (int[] ints : chessboard) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * @param chessboard 棋盘
     * @param row        行
     * @param column     列
     * @param step
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        // 标记当前为已访问
        chessboard[row][column] = step;
        visited[row * X + column] = true;
        // 获取出棋盘
        List<Point> next = next(new Point(column, row));
        // 进行贪心算法排序, 从小到大排
        sort(next);
        // 不为空遍历
        while (!next.isEmpty()) {
            // 取出删除第一个
            Point p = next.remove(0);
            if (!visited[p.y * X + p.x]) {
                //
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        // 如果并没有完成,我们需要重置
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            // 说明完成了
            finished = true;
        }
    }

    public static void sort(List<Point> next){
        next.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return next(o1).size() - next(o2).size();
            }
        });
    }

    /**
     * 获取马儿下一个可以走的位置
     *
     * @param point 马儿所在的下标
     * @return 所有可以到达的位置
     */
    public static List<Point> next(Point point) {
        List<Point> ps = new ArrayList<>();
        // 如果-2大于等于0表示列的位置可以走, 如果y-1大于等于0表示行的位置也可以走
        if (point.x - 2 >= 0 && point.y - 1 >= 0) {
            ps.add(new Point(point.x - 2, point.y - 1));
        }
        // 6
        if (point.x - 1 >= 0 && point.y - 2 >= 0) {
            ps.add(new Point(point.x - 1, point.y - 2));
        }
        // 7
        if (point.x + 1 < X && point.y - 2 >= 0) {
            ps.add(new Point(point.x + 1, point.y - 2));
        }
        // 0
        if (point.x + 2 < X && point.y - 1 >= 0) {
            ps.add(new Point(point.x + 2, point.y - 1));
        }
        // 1
        if (point.x + 2 < X && point.y + 1 < Y) {
            ps.add(new Point(point.x + 2, point.y + 1));
        }
        // 2
        if (point.x + 1 < X && point.y + 2 < Y) {
            ps.add(new Point(point.x + 1, point.y + 2));
        }
        // 3
        if (point.x - 1 >= 0 && point.y + 2 < Y) {
            ps.add(new Point(point.x - 1, point.y + 2));
        }
        // 4
        if (point.x - 2 >= 0 && point.y + 1 < Y) {
            ps.add(new Point(point.x - 2, point.y + 1));
        }

        return ps;
    }
}
