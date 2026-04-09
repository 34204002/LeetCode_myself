package 子串;

import java.util.*;

/**
 * LeetCode 239. 滑动窗口最大值
 * 
 * 【题目描述】
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * 
 * 【示例】
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 窗口位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 
 * 【解题思路】
 * 核心思想：单调双端队列（递减队列），队首始终是当前窗口的最大值索引
 * 
 * 队列特性：
 * - 存储的是数组索引，而非值
 * - 队列中的索引对应的值保持单调递减
 * - 队首永远是当前窗口最大值的索引
 * 
 * 算法流程：
 * 1. 遍历数组每个元素 nums[i]：
 *    a) 移除过期元素：如果队首索引 < i-k+1（超出窗口左边界），pollFirst()
 *    b) 维护单调性：
 *       - 如果队列为空或队首对应值 < nums[i]：addFirst(i)（新元素最大）
 *       - 否则：从队尾移除所有对应值 < nums[i] 的索引，然后 addLast(i)
 *    c) 记录结果：当 i >= k-1 时，队首即为当前窗口最大值
 * 
 * 【执行示例】nums = [1,3,-1,-3,5,3,6,7], k = 3
 * i=0: queue=[0](值1), 窗口未满
 * i=1: 3>1, 移除0 → queue=[1](值3), 窗口未满
 * i=2: -1<3, queue=[1,2](值3,-1), 窗口满 → max=nums[1]=3
 * i=3: -3<-1, queue=[1,2,3](值3,-1,-3), 窗口满 → max=nums[1]=3
 * i=4: 5>-3,-1,3, 全部移除 → queue=[4](值5), 窗口满 → max=nums[4]=5
 * i=5: 3<5, queue=[4,5](值5,3), 窗口满 → max=nums[4]=5
 * i=6: 6>3,5, 全部移除 → queue=[6](值6), 窗口满 → max=nums[6]=6
 * i=7: 7>6, 移除6 → queue=[7](值7), 窗口满 → max=nums[7]=7
 * 结果：[3,3,5,5,6,7] ✓
 * 
 * 【时间复杂度】O(n)
 * - 每个元素最多入队和出队各一次，均摊 O(1)
 * 
 * 【空间复杂度】O(k) - 双端队列最多存储 k 个索引
 * 
 * 【关键点】
 * - 第10-13行：移除超出窗口左边界的过期索引
 * - 第15-22行：维护队列单调性，保证队首是最大值
 *   · 特殊情况处理：新元素比队首还大时，直接 addFirst
 *   · 一般情况：从队尾移除较小元素后 addLast
 * - 第23-25行：窗口形成后，记录队首对应的最大值
 * 
 * 【注意】
 * - 你的代码在第15-17行做了特殊优化：新元素最大时直接放队首
 * - 标准实现通常统一从队尾移除，然后 addLast，逻辑更统一
 */
public class LC_239_maxSlidingWindow_1 {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 移除超出窗口左边界的过期索引
            if(i>k-1){
                while (!queue.isEmpty()&&queue.peekFirst()<i-k+1){
                    queue.pollFirst();
                }
            }
            // 维护单调递减队列
            if(queue.isEmpty()||nums[queue.peekFirst()]<nums[i]){
                // 特殊情况：新元素比队首还大，直接放队首
                queue.addFirst(i);

            }else {
                // 一般情况：从队尾移除所有小于当前元素的索引
                while (!queue.isEmpty()&&nums[queue.peekLast()]< nums[i])
                    queue.pollLast();
                queue.addLast(i);
            }
            // 窗口形成后，记录当前窗口最大值（队首对应的值）
            if(i>=k-1) {
                list.add(nums[queue.peekFirst()]);
            }

        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
