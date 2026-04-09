package 矩阵;

/**
 * LeetCode 240. 搜索二维矩阵 II
 * 
 * 【题目描述】
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * - 每行的元素从左到右升序排列。
 * - 每列的元素从上到下升序排列。
 * 
 * 【示例】
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 * 
 * 【解题思路】
 * 核心思想：从右上角（或左下角）开始的"二叉搜索树"式查找
 * 
 * 关键观察：
 * - 右上角元素的特性：它是该行最大、该列最小
 * - 类似二叉搜索树的根节点：
 *   · 如果 target < 当前值：target 不可能在当前列（该列都 ≥ 当前值），col--
 *   · 如果 target > 当前值：target 不可能在当前行（该行都 ≤ 当前值），row++
 *   · 如果 target == 当前值：找到目标
 * 
 * 算法流程：
 * 1. 从右上角开始（row=0, col=n-1）
 * 2. 循环直到越界：
 *    - 如果 matrix[row][col] == target：返回 true
 *    - 如果 matrix[row][col] > target：col--（排除当前列）
 *    - 如果 matrix[row][col] < target：row++（排除当前行）
 * 3. 越界仍未找到，返回 false
 * 
 * 【执行示例】target = 5
 * matrix:
 * [1,  4,  7, 11, 15]
 * [2,  5,  8, 12, 19]
 * [3,  6,  9, 16, 22]
 * [10, 13, 14, 17, 24]
 * [18, 21, 23, 26, 30]
 * 
 * 初始：row=0, col=4, matrix[0][4]=15
 * 15 > 5 → col-- → col=3
 * 11 > 5 → col-- → col=2
 * 7 > 5 → col-- → col=1
 * 4 < 5 → row++ → row=1
 * matrix[1][1]=5 == 5 → 返回 true ✓
 * 
 * 【时间复杂度】O(m + n)
 * - 每次循环排除一行或一列，最多遍历 m+n 次
 * 
 * 【空间复杂度】O(1) - 只使用常数变量
 * 
 * 【为什么从右上角开始？】
 * - 右上角和左下角都具有"单向决策"特性
 * - 左上角和右下角无法确定移动方向（两个方向都可能更大或更小）
 * 
 * 【关键点】
 * - 第5-6行：初始化从右上角开始
 * - 第7-16行：循环查找，每次排除一行或一列
 * - 第11-12行：当前值太大，向左移动（排除列）
 * - 第13-14行：当前值太小，向下移动（排除行）
 */
public class LC_240_searchMatrix_1 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row=0;
        int col=matrix[0].length-1;  // 从右上角开始
        while(row<matrix.length&&col>=0){
            if(matrix[row][col]==target){
                return true;
            }
            else if(matrix[row][col]>target){
                col--;  // 当前值太大，向左移动
            }else{
                row++;  // 当前值太小，向下移动
            }
        }
        return false;
    }
}
