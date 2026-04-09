package 哈希;

import java.util.HashMap;

/**
 * LeetCode 1. 两数之和 - 解法二：HashMap（推荐最优解）
 * 
 * 【题目描述】
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target 的那两个整数，
 * 并返回它们的数组下标。
 * 
 * 【示例】
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 
 * 【解题思路】
 * 核心思想：一次遍历，边存边查
 * 
 * 1. 使用 HashMap 存储 {数值: 索引}
 * 2. 遍历数组，对于当前元素 nums[i]：
 *    - 检查 target - nums[i] 是否已在 map 中
 *    - 如果存在，说明找到了两个数，直接返回 {map.get(target-nums[i]), i}
 *    - 如果不存在，将 {nums[i]: i} 存入 map
 * 
 * 【为什么能一次遍历完成？】
 * - 当我们遍历到 nums[i] 时，map 中存储的是之前所有元素的值和索引
 * - 如果 target-nums[i] 在 map 中，说明之前已经遇到过这个数
 * - 由于题目保证有唯一解，所以一旦找到就可以立即返回
 * 
 * 【时间复杂度】O(n) - 只需一次遍历
 * 【空间复杂度】O(n) - HashMap 最多存储 n 个元素
 * 
 * 【优势】
 * - 代码简洁，逻辑清晰
 * - 一次遍历即可完成，效率最高
 * - 自动处理相同元素的情况（因为是先查后存）
 * 
 * 【对比解法一】
 * - 解法一需要区分相同/不同元素，逻辑复杂
 * - 解法二统一处理，更优雅高效
 */
public class LC_1_twoSum_2 {
    public static int[] twoSum(int[] nums, int target) {
        int p1=-1,p2=-1;
        HashMap<Integer,Integer> map=new HashMap<>();
        // 一次遍历：边查边存
        for (int i = 0; i < nums.length; i++) {
            // 检查 complement 是否已存在
            if(map.containsKey(target-nums[i])){
                // 找到答案，直接返回
                return new int[]{map.get(target - nums[i]),i};
            }else
                // 未找到，将当前元素存入 map
                map.put(nums[i],i);
        }
        return new int[]{p1,p2};
    }
}
