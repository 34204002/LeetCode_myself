package 矩阵;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 54. 螺旋矩阵
 * 
 * 【题目描述】
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 
 * 【示例】
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 * 【解题思路】
 * 核心思想：模拟螺旋遍历，维护四个边界逐步收缩
 * 
 * 算法流程：
 * 1. 初始化四个边界：top=0, bottom=m-1, left=0, right=n-1
 * 2. 按顺时针方向逐层遍历：
 *    a) 从左到右遍历上边（top 行）
 *    b) 从上到下遍历右边（right 列）
 *    c) 从右到左遍历下边（bottom 行）
 *    d) 从下到上遍历左边（left 列）
 * 3. 每遍历完一条边，收缩对应边界
 * 4. 每次遍历后检查是否已收集所有元素
 * 
 * 【执行示例】matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 
 * 初始：top=0, bottom=2, left=0, right=2
 * 
 * 第1轮：
 * - 从左到右：i=0→2, 添加 [1,2,3], top++ → top=1
 * - 从上到下：i=1→2, 添加 [6,9], right-- → right=1
 * - 从右到左：i=1→0, 添加 [8,7], bottom-- → bottom=1
 * - 从下到上：i=1→1, 添加 [4], left++ → left=1
 * 
 * 第2轮：
 * - 从左到右：i=1→1, 添加 [5], top++ → top=2
 * - 检查：list.size()=9 == 3×3，break
 * 
 * 结果：[1,2,3,6,9,8,7,4,5] ✓
 * 
 * 【时间复杂度】O(m×n) - 遍历所有元素
 * 【空间复杂度】O(1) - 不计输出数组
 * 
 * 【关键点】
 * - 第9-12行：初始化四个边界
 * - 第15-17行：从左到右遍历上边
 * - 第21-24行：从上到下遍历右边
 * - 第28-31行：从右到左遍历下边
 * - 第35-37行：从下到上遍历左边
 * - 第19-20, 26-27, 33-34, 39-40行：每次遍历后检查是否完成
 * 
 * 【为什么需要多次检查 size？】
 * - 非方阵可能在遍历完某条边后就已完成
 * - 例如 3×4 矩阵，遍历完上边和右边后可能已收集所有元素
 * - 每条边遍历后立即检查，避免重复添加
 * 
 * 【注意】
 * - 第14行的循环条件 left<=right||top<=bottom 可以简化
 * - 实际上只需检查 list.size() 即可判断是否完成
 */
public class LC_54_spiralOrder_1 {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list=new ArrayList<>();
        int right=matrix[0].length-1;
        int left=0;
        int top=0;
        int bottom=matrix.length-1;

        while (left<=right||top<=bottom){
            // 从左到右遍历上边
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            top++;
            if(list.size()==matrix.length*matrix[0].length)
                break;
            // 从上到下遍历右边
            for (int i = top; i <= bottom; i++) {
                list.add(matrix[i][right]);

            }
            right--;
            if(list.size()==matrix.length*matrix[0].length)
                break;
            // 从右到左遍历下边
            for (int i = right; i >= left; i--) {
                list.add(matrix[bottom][i]);

            }
            bottom--;
            if(list.size()==matrix.length*matrix[0].length)
                break;
            // 从下到上遍历左边
            for (int i = bottom; i >= top; i--) {
                list.add(matrix[i][left]);
            }
            left++;
            if(list.size()==matrix.length*matrix[0].length)
                break;
        }
        return list;
    }
}
