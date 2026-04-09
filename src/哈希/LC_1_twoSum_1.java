package 哈希;

import java.util.HashSet;

/**
 * LeetCode 1. 两数之和 - 解法一：HashSet
 * 
 * 【题目描述】
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target 的那两个整数，
 * 并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案，且数组中同一个元素不能使用两遍。
 * 
 * 【示例】
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 
 * 【解题思路】
 * 本解法使用 HashSet 存储所有数字，然后分两种情况处理：
 * 
 * 1. 不同元素之和为 target：
 *    - 遍历数组，对于每个 num，检查 target-num 是否在 set 中且 num != target-num
 *    - 找到后记录这两个数值
 * 
 * 2. 相同元素之和为 target（如 target=6, num=3）：
 *    - 需要找到两个值为 target/2 的不同位置的元素
 *    - 单独遍历数组找到这两个位置
 * 
 * 【时间复杂度】O(n) - 三次遍历数组
 * 【空间复杂度】O(n) - HashSet 存储所有数字
 * 
 * 【缺点】
 * - 逻辑较复杂，需要区分相同元素和不同元素的情况
 * - 需要多次遍历数组
 * - 推荐使用解法二（HashMap 一次遍历）
 */
public class LC_1_twoSum_1 {
    public static int[] twoSum(int[] nums, int target) {
        int num1=-1,num2=-1;
        int p1=-1,p2=-1;
        boolean flag=false;
        HashSet<Integer> set=new HashSet<>();
        // 第一次遍历：将所有数字加入 HashSet
        for (int num : nums) {
            set.add(num);
        }
        // 第二次遍历：查找是否存在两个不同元素之和为 target
        for (int num : nums) {
            if(set.contains(target-num)&&num!=target-num){
                flag=true;//有不同元素之和为target
                num1=num;
                num2=target-num;
            }
        }
        // 元素相同的情况：需要找到两个值为 target/2 的不同位置
        if(!flag){
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target / 2) {
                    if (p1 == -1) {
                        p1 = i;
                    }
                    if (p1 != i && p2 == -1) {
                        p2 = i;
                    }
                }
            }
        }
        else {
            // 元素不同的情况：找到 num1 和 num2 的位置
            for (int i = 0; i < nums.length; i++) {
                if(nums[i]==num1){
                    p1=i;
                }if(nums[i]==num2){
                    p2=i;
                }
            }
        }
        return new int[]{p1,p2};
    }
}
