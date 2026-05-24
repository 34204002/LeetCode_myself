package 二分查找;

/**
 * LeetCode 74. 搜索二维矩阵 - 解法一：二分查找
 *
 * 【题目描述】
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 * - 每行中的整数从左到右按非严格递增顺序排列
 * - 每行的第一个整数大于前一行的最后一个整数
 * 给你一个整数 target，如果 target 在矩阵中，返回 true；否则返回 false。
 *
 * 【示例】
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 *
 * 【解题思路】
 * 核心思想：二维矩阵视为一维有序数组 + 二分查找
 *
 * 将 m×n 的矩阵视为长度为 m×n 的虚拟一维有序数组，
 * 通过下标映射将一维索引转换为二维坐标：
 *   row = mid / n  (n为列数)
 *   col = mid % n
 *
 * 算法流程：
 * 1. 初始化 left=0, right=m×n-1
 * 2. 循环执行二分查找，mid = left + (right-left)/2
 * 3. 将 mid 映射为二维坐标 (row, col)
 * 4. 比较 matrix[row][col] 与 target，按标准二分查找调整区间
 * 5. 找到返回 true，否则循环结束后返回 false
 *
 * 【执行示例】matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 *
 * m=3, n=4, 总元素=12
 * left=0, right=11
 *   第1轮：mid=5, row=5/4=1, col=5%4=1, matrix[1][1]=11 > 3 → right=4
 *   第2轮：mid=2, row=2/4=0, col=2%4=2, matrix[0][2]=5 > 3 → right=1
 *   第3轮：mid=0, row=0/4=0, col=0%4=0, matrix[0][0]=1 < 3 → left=1
 *   第4轮：mid=1, row=1/4=0, col=1%4=1, matrix[0][1]=3 == 3 → return true ✓
 *
 * 【时间复杂度】O(log(m×n)) - 在 m×n 个元素的虚拟数组上二分查找
 * 【空间复杂度】O(1) - 只使用常数个变量
 *
 * 【关键点】
 * - 第5行：right 初始化为 m×n-1，将矩阵展平为虚拟一维数组
 * - 第8行：mid 映射为二维坐标，row = mid/n, col = mid%n
 * - 第9-10行：找到目标值时直接返回 true
 * - 第11-12行：当前值大于目标，搜索左半区
 * - 第13-14行：当前值小于目标，搜索右半区
 *
 * 【注意】
 * - 第5行：矩阵可能为空，但题目保证至少有一行一列
 * - 第7行：循环条件 left<=right（不是 left<right），确保单元素区间也能检查
 * - 第8行：行列映射公式正确的前提是矩阵每行长度相等
 * - 第19行：循环结束未找到返回 false
 */
public class LC_74_searchMatrix_1 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int left=0;
        int right=matrix.length*matrix[0].length-1;
        while (left<=right){
            int mid=left+(right-left)/2;
            int row=mid/matrix[0].length;
            int col=mid%matrix[0].length;
            if(matrix[row][col]==target)
                return true;
            if(matrix[row][col]>target){
                right=mid-1;
            }
            if(matrix[row][col]<target){
                left=mid+1;
            }
        }
        return false;
    }
}
