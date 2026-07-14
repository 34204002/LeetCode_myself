package 动态规划;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 118. 杨辉三角 - 解法一：动态规划（逐行生成）
 *
 * 【题目描述】
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * 【示例】
 * 输入：numRows = 5
 * 输出：[[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 输入：numRows = 1
 * 输出：[[1]]
 *
 * 【解题思路】
 * 核心思想：逐行生成，每行的第一个和最后一个为 1，中间元素由上一行计算
 *
 * 杨辉三角的性质：
 *   - 第 i 行（从 0 开始）有 i+1 个元素
 *   - 每行首尾元素为 1
 *   - 第 i 行第 j 个元素 = 第 i-1 行第 j-1 个元素 + 第 i-1 行第 j 个元素
 *
 * 算法流程：
 * 1. 初始化结果列表 list
 * 2. 遍历 i 从 0 到 numRows-1（生成每一行）：
 *    a. 为当前行添加一个空列表
 *    b. 遍历 j 从 0 到 i（生成当前行的每个元素）：
 *       - 若 j==0 或 j==i（首尾），添加 1
 *       - 否则添加上一行第 j-1 和第 j 个元素之和
 * 3. 返回 list
 *
 * 【执行示例】numRows = 5
 *
 * i=0: [1]
 * i=1: [1, 1]
 * i=2: [1, 1+1=2, 1]
 * i=3: [1, 1+2=3, 2+1=3, 1]
 * i=4: [1, 1+3=4, 3+3=6, 3+1=4, 1]
 * 结果：[[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]] ✓
 *
 * 【时间复杂度】O(numRows²) - 双重循环，约 numRows×(numRows+1)/2 次操作
 * 【空间复杂度】O(numRows²) - 存储整个杨辉三角的结果
 *
 * 【关键点】
 * - 第11行：ArrayList 用于存储结果，支持按索引随机访问
 * - 第12行：外层循环控制行数，每行新建一个列表
 * - 第14行：每行首尾元素恒为 1
 * - 第16行：中间元素由上一行对应位置推导
 *
 * 【注意】
 * - 第11行：add 的是 new ArrayList<>()（可变列表），后续可以通过 list.get(i) 修改
 * - 第16行：list.get(i-1).get(j-1) 获取上一行第 j-1 个元素
 * - 第16行：list.get(i-1).get(j) 获取上一行第 j 个元素
 * - 本质上属于 DP，但不需要额外数组，直接在结果列表中复用前一行数据
 */
public class LC_118_generate_1 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list=new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new ArrayList<>());
            for (int j = 0; j < i+1; j++) {
                if(j==0||j==i)
                    list.get(i).add(1);
                else
                    list.get(i).add(list.get(i-1).get(j-1)+list.get(i-1).get(j));

            }
        }
        return list;
    }
}
