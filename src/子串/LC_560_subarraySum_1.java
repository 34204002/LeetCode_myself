package 子串;

/**
 * LeetCode 560. 和为 K 的子数组
 * 
 * 【题目描述】
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 * 
 * 【示例】
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 解释：子数组 [1,1]（索引0-1）和 [1,1]（索引1-2）的和都是 2
 * 
 * 【解题思路】
 * 核心思想：前缀和 + 暴力枚举所有子数组
 * 
 * 1. 计算前缀和数组 sums：
 *    - sums[i] = nums[0] + nums[1] + ... + nums[i]
 *    - sums[0] = nums[0]
 * 
 * 2. 枚举所有子数组 [i, j]：
 *    - 如果 i == 0：子数组和 = sums[i]
 *    - 如果 i > 0：子数组和 = sums[j] - sums[i-1]
 *    - 你的代码中：sums[j] - sums[i] 对应子数组 [i+1, j]
 * 
 * 3. 统计和为 k 的子数组数量
 * 
 * 【执行示例】nums = [1,1,1], k = 2
 * 前缀和：sums = [1, 2, 3]
 * 
 * i=0: sums[0]=1 ≠ 2
 *      j=1: sums[1]-sums[0] = 2-1 = 1 ≠ 2
 *      j=2: sums[2]-sums[0] = 3-1 = 2 = k ✓ count=1
 * i=1: sums[1]=2 = k ✓ count=2
 *      j=2: sums[2]-sums[1] = 3-2 = 1 ≠ 2
 * i=2: sums[2]=3 ≠ 2
 * 
 * 结果：count = 2 ✓
 * 
 * 【时间复杂度】O(n²)
 * - 计算前缀和 O(n)
 * - 双重循环枚举所有子数组 O(n²)
 * 
 * 【空间复杂度】O(n) - 前缀和数组
 * 
 * 【优化建议】
 * - 使用 HashMap 优化到 O(n)：
 *   · key: 前缀和, value: 该前缀和出现的次数
 *   · 遍历数组时，检查 (当前前缀和 - k) 是否在 map 中
 *   · 如果存在，说明有若干个子数组和为 k
 * - 优化后只需一次遍历，时间复杂度 O(n)
 * 
 * 【关键点】
 * - 第5-10行：构建前缀和数组
 * - 第12-13行：处理从索引 0 开始的子数组（sums[i] == k）
 * - 第14-17行：枚举所有子数组 [i+1, j]，检查和是否为 k
 * 
 * 【注意】
 * - 你的代码中 sums[j] - sums[i] 计算的是子数组 [i+1, j] 的和
 * - 第12行单独处理 sums[i] == k，对应子数组 [0, i] 的和
 * - 这种拆分是正确的，但逻辑稍显复杂
 */
public class LC_560_subarraySum_1 {
    public static int subarraySum(int[] nums, int k) {
        // 构建前缀和数组
        int[] sums=new int[nums.length];
        sums[0]=nums[0];
        int count=0;
        for (int i = 1; i < nums.length; i++) {
            sums[i]=nums[i]+sums[i-1];
        }
        // 枚举所有子数组，统计和为 k 的数量
        for (int i = 0; i < sums.length; i++) {
            // 处理子数组 [0, i] 的和
            if(sums[i]==k)
                count++;
            // 处理子数组 [i+1, j] 的和
            for (int j = i+1; j < sums.length; j++) {
                if(sums[j]-sums[i]==k)
                    count++;
            }
        }
        return count;
    }
}
