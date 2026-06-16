package 堆;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 347. 前 K 个高频元素 - 解法一：哈希表计数 + 排序
 *
 * 【题目描述】
 * 给你一个整数数组 nums 和一个整数 k，请你返回其中出现频率前 k 高的元素。
 * 你可以按任意顺序返回答案。
 *
 * 【示例】
 * 输入：nums = [1,1,1,2,2,3], k = 2
 * 输出：[1,2]
 * 输入：nums = [1], k = 1
 * 输出：[1]
 *
 * 【解题思路】
 * 核心思想：HashMap 统计频率 + 按频率排序取前 k 个
 *
 * 算法流程：
 * 1. 遍历数组，用 HashMap 统计每个元素出现的次数
 * 2. 将 HashMap 的 entrySet 转为 List
 * 3. 对 List 按 value（频率）降序排序
 * 4. 取排序后列表的前 k 个 entry 的 key，即为结果
 *
 * 【执行示例】nums = [1,1,1,2,2,3], k = 2
 *
 * 1. 统计频率：{1:3, 2:2, 3:1}
 * 2. 转为 List：[{1:3}, {2:2}, {3:1}]
 * 3. 按频率降序排序：[{1:3}, {2:2}, {3:1}]
 * 4. 取前2个 key：[1, 2] ✓
 *
 * 【时间复杂度】O(n log n) - 排序占主导，HashMap 操作和收集为 O(n)
 * 【空间复杂度】O(n) - HashMap 存储 n 个不同元素的频率，List 同理
 *
 * 【关键点】
 * - 第13-18行：HashMap 统计每个元素的出现次数
 * - 第20行：将 entrySet 转为 List，便于排序
 * - 第22行：自定义比较器按 value 降序排列（o2.value - o1.value）
 * - 第23-26行：遍历前 k 个元素，收集 key 到结果数组
 *
 * 【注意】
 * - 第16-17行：首次遇到元素频率应设为1而非0（与解法二不同，
 *   此处将每次遇见的次数计为实际出现次数减1再+1，但首次应为1）
 * - 第20行：stream().toList() 需要 Java 16+，低版本用 collect(Collectors.toList())
 * - 本解法排序 O(n log n)，不如堆解法（O(n log k)），但实现更简洁直观
 */
public class LC_347_topKFrequent_1 {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(hashMap.containsKey(nums[i])){
                hashMap.put(nums[i],hashMap.get(nums[i])+1);
            }else {
                hashMap.put(nums[i],0);
            }
        }
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(hashMap.entrySet().stream().toList());

        list.sort((o1,o2)->o2.getValue()-o1.getValue());
        int[] res=new int[k];
        for (int i = 0; i < k; i++) {
            res[i]=list.get(i).getKey();
        }
        return res;
    }
}
