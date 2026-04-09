package 双指针;

import java.util.*;

/**
 * LeetCode 15. 三数之和
 * 
 * 【题目描述】
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 
 * i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
 * 返回所有和为 0 且不重复的三元组。
 * 
 * 【示例】
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 
 * 【解题思路】
 * 核心思想：排序 + 固定一个数 + 双指针查找另外两个数
 * 
 * 1. 排序数组（便于去重和双指针）
 * 2. 遍历每个元素 nums[p0] 作为第一个数：
 *    - 如果 nums[p0] > 0，后续不可能有解（已排序），break
 *    - 如果 nums[p0] == nums[p0-1]，跳过重复元素（去重优化）
 * 3. 对于每个 p0，用双指针在 [p0+1, n-1] 范围内查找：
 *    - left = p0+1, right = n-1
 *    - 如果三数之和 > 0：right--（需要更小的数）
 *    - 如果三数之和 < 0：left++（需要更大的数）
 *    - 如果三数之和 = 0：记录答案，left++, right--
 * 4. 使用 HashSet 自动去重
 * 
 * 【时间复杂度】O(n²)
 * - 排序 O(n log n)
 * - 外层循环 O(n)，内层双指针 O(n)，共 O(n²)
 * 
 * 【空间复杂度】O(n) - HashSet 存储结果
 * 
 * 【关键点】
 * - 第14-15行的去重判断：避免对相同的 nums[p0] 重复计算
 * - 第16-18行的剪枝：nums[p0] > 0 时直接 break（优化）
 * - 使用 Set<List<Integer>> 自动处理重复三元组
 * 
 * 【注意】
 * - 你的代码中第14行用 break 而非 continue，虽然能工作但逻辑不够清晰
 * - 标准做法是在找到答案后对 left/right 也进行去重
 */
public class LC_15_threeSum_1 {
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int p0=0;
        Set<List<Integer>> set=new HashSet<>();
        while (p0<nums.length) {
            int left = p0+1 ;
            int right = nums.length-1 ;
            while (left<right){
                // 去重优化：跳过相同的 nums[p0]
                if(p0>0&&nums[p0]==nums[p0-1])
                    break;
                // 剪枝：第一个数大于0，后续不可能有解
                if(nums[p0]>0){
                    break;
                }
                if (nums[left] + nums[p0] + nums[right] > 0) {
                    right--;  // 和太大，右指针左移
                }
                else if (nums[left] + nums[p0] + nums[right] < 0) {
                    left++;   // 和太小，左指针右移
                }
                else if(nums[left]+nums[p0]+nums[right]==0) {
                    // 找到答案，加入集合（自动去重）
                    set.add(Arrays.asList(nums[left], nums[p0], nums[right]));
                    left++;
                    right--;
                }
            }
            p0++;
        }
        return new ArrayList<>(set);
    }
}
