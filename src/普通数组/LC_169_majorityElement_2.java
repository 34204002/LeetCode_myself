package 普通数组;

/**
 * LeetCode 169. 多数元素 - 解法二：Boyer-Moore 投票算法
 *
 * 【题目描述】
 * 给定一个大小为 n 的数组 nums，返回其中的多数元素。多数元素是指在数组中出现次数大于 ⌊n/2⌋ 的元素。
 *
 * 【示例】
 * 输入：nums = [3,2,3]
 * 输出：3
 * 输入：nums = [2,2,1,1,1,2,2]
 * 输出：2
 *
 * 【解题思路】
 * 核心思想：摩尔投票算法，利用多数元素个数超过半数的特性进行对抗抵消
 *
 * 维护一个候选元素 now 和计数器 count：
 *   - 遍历数组，若当前元素与 now 不同则 count--，相同则 count++
 *   - 当 count < 0 时，说明前面的元素中 now 不占优势，切换到当前元素
 *   - 最终 now 即为多数元素
 *
 * 算法流程：
 * 1. now = nums[0]，count = 0
 * 2. 遍历 i 从 1 到 n-1：
 *    a. 若 nums[i] != now，count--；否则 count++
 *    b. 若 count < 0，将 now 切换为 nums[i]，count 重置为 0
 * 3. 返回 now
 *
 * 【执行示例】nums = [2,2,1,1,1,2,2]
 *
 * now=2, count=0
 * i=1(2==now) → count=1
 * i=2(1!=now) → count=0
 * i=3(1!=now) → count=-1 < 0 → now=1, count=0
 * i=4(1==now) → count=1
 * i=5(2!=now) → count=0
 * i=6(2!=now) → count=-1 < 0 → now=2, count=0
 * return 2 ✓
 *
 * 【时间复杂度】O(n) - 一次遍历
 * 【空间复杂度】O(1) - 仅两个变量
 *
 * 【关键点】
 * - 第7行：now 记录当前候选，count 记录相对优势（出现次数 vs 被抵消次数）
 * - 第10-14行：不同值对抗抵消，相同值增加优势
 * - 第16-19行：优势归零时切换候选
 *
 * 【注意】
 * - 此算法成立的前提是多数元素一定存在且出现次数 > n/2
 * - 第10行：count-- 是在对抗抵消，如果 now 是真正的多数元素，最终 count 一定为正
 * - 第16行：count<0 说明当前候选在已遍历部分中不占优，切换
 * - 相比排序解法，摩尔投票将 O(n log n) 降到了 O(n)
 */
public class LC_169_majorityElement_2 {
    public int majorityElement(int[] nums) {
        int count=0;
        int now=nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i]!=now){
                count--;
            }
            else{
                count++;
            }
            if(count<0) {
                now = nums[i];
                count=0;
            }
        }
        return now;
    }
}
