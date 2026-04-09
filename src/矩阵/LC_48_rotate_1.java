package 矩阵;

/**
 * LeetCode 48. 旋转图像
 * 
 * 【题目描述】
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。
 * 请不要使用另一个矩阵来旋转图像。
 * 
 * 【示例】
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * 
 * 【解题思路】
 * 核心思想：逐层螺旋式旋转，从外层到内层
 * 
 * 你的实现采用了复杂的单层螺旋推进方式：
 * 1. 维护四个边界：top, bottom, left, right
 * 2. 按顺时针方向逐边旋转：上→右→下→左
 * 3. 使用 now 变量暂存被覆盖的值，像"推箱子"一样传递
 * 4. 通过 rotateCount 和 sumRotateCount 控制层数切换
 * 
 * 旋转过程（以一层为例）：
 * - 上边：从左到右，每个位置的值被下一个位置的值替换
 * - 右边：从上到下，继续传递
 * - 下边：从右到左，继续传递
 * - 左边：从下到上，完成一圈
 * 
 * 【执行示例】matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 
 * 初始状态：
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * 
 * 第1圈旋转（外层）：
 * - 上边旋转：now=matrix[1][0]=4
 *   i=0: n=matrix[0][0]=1, matrix[0][0]=4, now=1
 *   i=1: n=matrix[0][1]=2, matrix[0][1]=1, now=2
 *   i=2: n=matrix[0][2]=3, matrix[0][2]=2, now=3
 *   结果：[4,1,2]
 *          [4,5,6]
 *          [7,8,9]
 * 
 * - 右边旋转：
 *   i=1: n=matrix[1][2]=6, matrix[1][2]=3, now=6
 *   i=2: n=matrix[2][2]=9, matrix[2][2]=6, now=9
 *   结果：[4,1,2]
 *          [4,5,3]
 *          [7,8,6]
 * 
 * - 下边旋转：
 *   i=1: n=matrix[2][1]=8, matrix[2][1]=9, now=8
 *   i=0: n=matrix[2][0]=7, matrix[2][0]=8, now=7
 *   结果：[4,1,2]
 *          [4,5,3]
 *          [8,9,6]
 * 
 * - 左边旋转：
 *   i=1: n=matrix[1][0]=4, matrix[1][0]=7, now=4
 *   结果：[4,1,2]
 *          [7,5,3]
 *          [8,9,6]
 * 
 * 最终：[[7,4,1],[8,5,2],[9,6,3]] ✓
 * 
 * 【时间复杂度】O(n²) - 每个元素访问一次
 * 【空间复杂度】O(n) - rotateCount 和 sumRotateCount 数组
 * 
 * 【优化建议】
 * - 标准解法：两次翻转（更简洁）
 *   1. 沿主对角线翻转（转置）
 *   2. 沿垂直中线翻转（左右翻转）
 * - 或者分层旋转，每层四个位置一组交换
 * 
 * 【关键点】
 * - 第16-20行：计算每层需要旋转的元素数量
 * - 第21-25行：计算累计旋转数量，用于判断何时进入下一层
 * - 第36-76行：四层循环分别处理上、右、下、左四条边
 * - 第78-86行：判断是否完成当前层，决定是否进入下一层
 * 
 * 【注意】
 * - 你的代码逻辑复杂，使用了多个计数器和状态变量
 * - 第31-34行的边界处理有些冗余
 * - 推荐使用标准的分层旋转或翻转法，代码更清晰
 */
public class LC_48_rotate_1 {
    public static void rotate(int[][] matrix) {
        if(matrix.length==1){
            return;
        }
        int sum = matrix.length * matrix[0].length;
        int count = 0;
        int count1 = 0;
        int right = matrix[0].length - 1;
        int left = 0;
        int top = 0;
        int bottom = matrix.length - 1;
        int now = 0;
        // 计算每层需要旋转的元素数量
        int[] rotateCount = new int[matrix.length / 2];
        rotateCount[0] = matrix.length - 1;
        for (int i = 1; i < rotateCount.length; i++) {
            rotateCount[i] = rotateCount[i - 1] - 2;
        }
        // 计算累计旋转数量
        int[] sumRotateCount = new int[matrix.length / 2];
        sumRotateCount[0] = rotateCount[0];
        for (int i = 1; i < sumRotateCount.length; i++) {
            sumRotateCount[i] = sumRotateCount[i - 1] + rotateCount[i];
        }

        int c = 0;
        int cc = 0;
        while (left <= right || top <= bottom) {
            count1 = count;
            if ((sum - count == 1) || (sum - count == 0)) {
                int ioii = 0;
                break;
            }
            now = matrix[top + 1][left];  // 暂存下一个要被覆盖的值
            // 上边：从左到右
            for (int i = left; i <= right; i++) {
                int n = matrix[top][i];
                matrix[top][i] = now;
                now = n;
                count++;
            }
            top++;
            if (count == sum) {
                break;
            }

            // 右边：从上到下
            for (int i = top; i <= bottom; i++) {
                int n = matrix[i][right];
                matrix[i][right] = now;
                now = n;
                count++;
            }
            right--;
            if (count == sum) {
                break;
            }
            // 下边：从右到左
            for (int i = right; i >= left; i--) {
                int n = matrix[bottom][i];
                matrix[bottom][i] = now;
                now = n;
                count++;
            }
            bottom--;
            if (count == sum) {
                break;
            }
            // 左边：从下到上
            for (int i = bottom; i >= top; i--) {
                int n = matrix[i][left];
                matrix[i][left] = now;
                now = n;
                count++;
            }
            left++;
            if (count == sum) {
                break;
            }
            c++;
            // 判断是否完成当前层
            if (c < sumRotateCount[cc]) {
                // 未完成，回退边界继续旋转
                top--;
                right++;
                bottom++;
                left--;
                count = count1;
            } else {
                // 完成当前层，进入下一层
                cc++;
            }


        }
    }
}
