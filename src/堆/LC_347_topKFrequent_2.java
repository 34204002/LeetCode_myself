package 堆;

import java.util.*;

/**
 * LeetCode 347. 前 K 个高频元素 - 解法二：哈希表计数 + 小顶堆
 *
 * 【题目描述】
 * 给你一个整数数组 nums 和一个整数 k，请你返回其中出现频率前 k 高的元素。
 * 你可以按任意顺序返回答案。
 *
 * 【示例】
 * 输入：nums = [1,1,1,2,2,3], k = 2
 * 输出：[1,2]
 *
 * 【解题思路】
 * 核心思想：HashMap 统计频率 + 大小为 k 的小顶堆筛选 Top-K
 *
 * 使用大小为 k 的小顶堆维护当前频率最高的 k 个元素：
 *   - 堆顶始终是堆中频率最小的元素（即 Top-K 中的"门槛"）
 *   - 遍历所有 entry，当新元素频率大于堆顶时，弹出堆顶并加入新元素
 *   - 若堆未满（size < k），直接加入
 *
 * 算法流程：
 * 1. HashMap 统计每个元素的出现频率
 * 2. 创建小顶堆 PriorityQueue，按 entry.getValue() 升序排列
 * 3. 遍历 HashMap 的每个 entry：
 *    - 若堆非空且当前频率 > 堆顶频率，说明当前元素应进入 Top-K
 *      若堆已满(k个)，先弹出堆顶（最小的），再入堆
 *    - 否则若堆未满，直接入堆
 * 4. 堆中剩余的 k 个 entry 即为频率前 k 高的元素
 *
 * 【执行示例】nums = [1,1,1,2,2,3], k = 2
 *
 * 1. 统计频率：{1:3, 2:2, 3:1}
 * 2. 遍历 entry：
 *    entry {1:3}：堆空 → 直接入堆 → 堆=[{1:3}]
 *    entry {2:2}：堆未满(size=1<k=2) → 直接入堆 → 堆=[{2:2}, {1:3}]
 *    entry {3:1}：堆已满(size=2)，1 < peek=2（堆顶频率2）→ 不处理
 * 3. 堆中元素：[{2:2}, {1:3}] → 输出 [2, 1] ✓（顺序任意）
 *
 * 【时间复杂度】O(n log k) - 遍历 n 个不同元素，堆操作每次 O(log k)
 * 【空间复杂度】O(n) - HashMap O(n) + 堆 O(k)
 *
 * 【关键点】
 * - 第15行：小顶堆比较器 o1.value - o2.value，堆顶为最小频率
 * - 第17-19行：当前频率 > 堆顶频率时，淘汰堆顶（更小的），加入新元素
 * - 第21-23行：堆未满时直接加入，用于前 k 个元素的初始积累
 * - 第28-31行：堆的 poll 按频率升序弹出，结果顺序不影响答案
 *
 * 【注意】
 * - 第15行：PriorityQueue 默认是小顶堆（自然顺序/比较器顺序），此处显式指定
 * - 第17行：条件 priorityQueue.peek().getValue() < entry.getValue() 用 < 而非 <=，
 *   等于时保留先入堆的元素（任意选择，不影响正确性）
 * - 第22行：不能省略 size()<k 的判断，否则堆未满时可能拒绝加入低频率元素
 *   导致最终堆中不足 k 个元素
 * - 若 k 接近 n，本解法退化为 O(n log n)，与排序法性能相近
 */
public class LC_347_topKFrequent_2 {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(hashMap.containsKey(nums[i])){
                hashMap.put(nums[i],hashMap.get(nums[i])+1);
            }else {
                hashMap.put(nums[i],1);
            }
        }
        PriorityQueue<Map.Entry<Integer,Integer>> priorityQueue=new PriorityQueue<>((o1,o2)->o1.getValue()-o2.getValue());
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if(!priorityQueue.isEmpty()&&priorityQueue.peek().getValue()<entry.getValue()){
                if(priorityQueue.size()==k)
                    priorityQueue.poll();
                priorityQueue.add(entry);
            }else{
                if(priorityQueue.size()<k)
                    priorityQueue.add(entry);
            }
        }
        int[] res=new int[k];
        int count=0;
        while (!priorityQueue.isEmpty()){
            res[count]=priorityQueue.poll().getKey();
            count++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LC_347_topKFrequent_2().topKFrequent(new int[]{1, 1, 1, 2, 2, 3},2)));
    }
}
