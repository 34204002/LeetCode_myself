package 动态规划;

import java.util.Arrays;

/**
 * LeetCode 300. 最长递增子序列
 * 
 * 【题目描述】
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 
 * 【示例】
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 
 * 【解题思路】
 * 核心思想：动态规划
 * 
 * 状态定义：
 * - dp[i]：以 nums[i] 结尾的最长递增子序列的长度
 * 
 * 状态转移方程：
 * - 对于每个位置 i，遍历其前面的所有位置 j（0 ≤ j < i）
 * - 如果 nums[i] > nums[j]，说明 nums[i] 可以接在以 nums[j] 结尾的递增子序列后面
 * - 则 dp[i] = max(dp[i], dp[j] + 1)
 * 
 * 算法流程：
 * 1. 初始化 dp 数组，所有值设为 1（每个元素本身构成长度为1的递增子序列）
 * 2. 双重循环：
 *    - 外层循环 i 从 0 到 n-1，计算以每个位置结尾的最长递增子序列长度
 *    - 内层循环 j 从 i-1 到 0，检查所有前面的位置
 *    - 如果 nums[i] > nums[j]，更新 dp[i]
 * 3. 在计算过程中维护全局最大值 max
 * 4. 返回 max
 * 
 * 【执行示例】nums = [10,9,2,5,3,7,101,18]
 * 
 * 初始：dp = [1,1,1,1,1,1,1,1]
 * 
 * i=0: 没有前面的元素，dp[0]=1, max=1
 * i=1: nums[1]=9 < nums[0]=10，不更新，dp[1]=1, max=1
 * i=2: nums[2]=2 < nums[0],nums[1]，不更新，dp[2]=1, max=1
 * i=3: nums[3]=5 > nums[2]=2 → dp[3]=max(1,dp[2]+1)=2
 *      dp[3]=2, max=2
 * i=4: nums[4]=3 > nums[2]=2 → dp[4]=max(1,dp[2]+1)=2
 *      dp[4]=2, max=2
 * i=5: nums[5]=7 > nums[4]=3 → dp[5]=max(1,dp[4]+1)=3
 *      nums[5]=7 > nums[3]=5 → dp[5]=max(3,dp[3]+1)=3
 *      nums[5]=7 > nums[2]=2 → dp[5]=max(3,dp[2]+1)=3
 *      dp[5]=3, max=3
 * i=6: nums[6]=101 > nums[5]=7 → dp[6]=max(1,dp[5]+1)=4
 *      ... 继续检查前面的元素，最终 dp[6]=4, max=4
 * i=7: nums[7]=18 > nums[5]=7 → dp[7]=max(1,dp[5]+1)=4
 *      ... 继续检查，最终 dp[7]=4, max=4
 * 
 * 结果：max=4 ✓
 * 
 * 【时间复杂度】O(n²) - 双重循环遍历数组
 * 【空间复杂度】O(n) - dp 数组的空间
 * 
 * 【关键点】
 * - 第7行：初始化 dp 数组，每个位置至少为1（单独一个元素）
 * - 第10-15行：双重循环计算每个位置的最长递增子序列长度
 * - 第12-14行：只有当 nums[i] > nums[j] 时才能构成递增关系
 * - 第16-18行：实时更新全局最大值，避免最后再遍历一次 dp 数组
 * 
 * 【优化思路】
 * - 可以使用二分查找将时间复杂度优化到 O(n log n)
 * - 维护一个 tails 数组，tails[i] 表示长度为 i+1 的递增子序列的最小末尾元素
 */
public class LC_300_lengthOfLIS_1 {
    public int lengthOfLIS(int[] nums) {
        int[] dp=new int[nums.length];
        Arrays.fill(dp,1);
        int max=0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i-1; j >= 0; j--) {
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            if(dp[i]>max){
                max=dp[i];
            }
        }
        return max;
    }
}
