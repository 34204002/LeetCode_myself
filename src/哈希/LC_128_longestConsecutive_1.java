package 哈希;

import java.util.HashSet;

/**
 * LeetCode 128. 最长连续序列
 * 
 * 【题目描述】
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 要求设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * 
 * 【示例】
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 
 * 【解题思路】
 * 核心思想：使用 HashSet 去重后，只从每个连续序列的起点开始向后查找
 * 
 * 1. 将所有数字存入 HashSet（O(n) 去重 + O(1) 查找）
 * 2. 遍历 set 中的每个数字 i：
 *    - 如果 i-1 存在于 set 中，说明 i 不是序列起点，跳过
 *    - 如果 i-1 不存在，说明 i 是某个序列的起点，从 i 开始向后查找连续数字
 * 3. 记录最长序列长度
 * 
 * 【时间复杂度】O(n)
 * - 虽然有两层循环，但每个数字最多被访问两次（一次作为起点判断，一次在 while 中）
 * - 内层 while 只在遇到序列起点时执行，整体仍是线性复杂度
 * 
 * 【空间复杂度】O(n) - HashSet 存储所有数字
 * 
 * 【关键点】
 * - 通过判断 i-1 是否存在来确保只从序列起点开始计数，避免重复计算
 * - 这是保证 O(n) 时间复杂度的关键优化
 */
public class LC_128_longestConsecutive_1 {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set=new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans=0;
        for (Integer i : set) {
            // 只有当 i 是序列起点时才开始查找（i-1 不存在）
            if(set.contains(i-1))
                continue;
            int y=i+1;
            // 从起点 i 开始向后查找连续数字
            while (set.contains(y)){
                y++;
            }
            // y-i 即为从 i 开始的连续序列长度
            ans=Math.max(y-i,ans);
        }
        return ans;
    }
}
