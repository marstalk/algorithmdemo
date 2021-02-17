package com.marstalk.algorithmdemo.infoq.array;

/**
 * 问题 42：在按行和按列排序的矩阵中搜索
 * 给定按行和按列排序的矩阵，需要以最小的时间复杂度搜索元素。
 * 分析：
 * 1)
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 * 这道题跟leetcode中的有点儿不一样，
 * leetcode的顺序是每一行从左到右升序
 * 下一行的最小数一定会比上一行的最大数要大，这个题目可以将这个矩阵展开成一个排好序的数组，然后使用二分法来搜索。
 * 其中二分法中的idx可以使用以下公式转换为行和列：
 * row = idx/r
 * col = idx%c
 *
 * 2)
 * 知乎上的分析：https://zhuanlan.zhihu.com/p/123359947
 * 题目中的这种排序方式有一个特点，那就是从右上角的元素开始，往左一步，变小。往下一步变大。那就符合二分法的思想了。
 */
public class Question42TODO {
    public static void main(String[] args) {
        int[][] sortedMatrix =
                {
                        { 1, 6, 10, 12, 20 },
                        { 4, 8, 15, 22, 25 },
                        { 5, 20, 35, 37, 40 },
                        { 10, 28, 38, 45, 55 }
                };

        searchElementInSortedMatrix(sortedMatrix, 37);
    }

    /**
     * 从右上角开始，如果太小，则往下一位，
     * 如果太大，则往左一位。
     * @param sortedMatrix
     * @param tartget
     */
    private static void searchElementInSortedMatrix(int[][] sortedMatrix, int tartget) {
        int row = 0;
        int col = sortedMatrix[0].length - 1;
        while (row < sortedMatrix.length && col > -1) {
            int tem = sortedMatrix[row][col];
            if (tartget == tem) {
                System.out.println("row=" + row + " col=" + col);
                break;
            }
            if (tem > tartget) {
                col--;
            }else{
                row++;
            }
        }
    }
}
