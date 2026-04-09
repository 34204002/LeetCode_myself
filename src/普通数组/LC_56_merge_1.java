package 普通数组;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode 56. 合并区间
 * 
 * 【题目描述】
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * 
 * 【示例】
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 
 * 【解题思路】
 * 核心思想：排序 + 贪心合并
 * 
 * 算法流程：
 * 1. 按区间左端点升序排序
 * 
 * 2. 初始化当前合并区间 [left, right] 为第一个区间
 * 
 * 3. 遍历后续区间：
 *    - 如果当前区间左端点 ≤ right（有重叠）：
 *      · 更新 right = max(right, 当前区间右端点)
 *    - 否则（无重叠）：
 *      · 将 [left, right] 加入结果
 *      · 更新 [left, right] 为当前区间
 * 
 * 4. 将最后一个合并区间加入结果
 * 
 * 【执行示例】intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 
 * 排序后：[[1,3],[2,6],[8,10],[15,18]]（已有序）
 * 
 * 初始化：left=1, right=3
 * 
 * i=1: [2,6], 2≤3 → 重叠 → right=max(3,6)=6
 * i=2: [8,10], 8>6 → 不重叠 → 添加[1,6], left=8, right=10
 * i=3: [15,18], 15>10 → 不重叠 → 添加[8,10], left=15, right=18
 * 循环结束：添加[15,18]
 * 
 * 结果：[[1,6],[8,10],[15,18]] ✓
 * 
 * 【时间复杂度】O(n log n)
 * - 排序 O(n log n)
 * - 遍历合并 O(n)
 * 
 * 【空间复杂度】O(n) - 存储结果列表
 * 
 * 【关键点】
 * - 第11-14行：使用 Stream API 按左端点排序
 * - 第16-17行：初始化当前合并区间
 * - 第19-25行：遍历合并逻辑
 *   · 第19-20行：有重叠时扩展右边界
 *   · 第21-24行：无重叠时保存当前区间并开始新区间
 * - 第26-28行：处理最后一个合并区间（边界情况）
 * 
 * 【注意】
 * - 第8-10行的特判可以省略，后续逻辑已涵盖
 * - 第26-28行的边界处理很重要，否则会丢失最后一个区间
 * - 更优雅的写法是在循环结束后统一添加最后一个区间，避免在循环内判断
 * 
 * 【优化建议】
 * - 可以用传统 for 循环替代 Stream API，代码更清晰
 * - 循环结束后直接 result.add(new int[]{left, right})，无需在循环内判断
 */
public class LC_56_merge_1 {
    public static int[][] merge(int[][] intervals) {
        if(intervals.length==1){
            return intervals;
        }
        // 按区间左端点升序排序
        List<int[]> list = Arrays.
                stream(intervals).
                sorted(Comparator.comparingInt((int[] o) -> o[0])).
                collect(Collectors.toList());
        List<int[]> result=new ArrayList<>();
        // 初始化当前合并区间
        int left= list.get(0)[0];
        int right=list.get(0)[1];
        for (int i = 1; i < list.size(); i++) {
            if(list.get(i)[0]<=right) {
                // 有重叠，扩展右边界
                right=Math.max(list.get(i)[1],right);
            }else{
                // 无重叠，保存当前区间并开始新区间
                result.add(new int[]{left,right});
                left=list.get(i)[0];
                right=list.get(i)[1];
            }
            // 处理最后一个区间
            if (i==list.size()-1){
                result.add(new int[]{left,right});
            }
        }
        // 将结果列表转为二维数组
        int[][] arr=new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            arr[i]=result.get(i);
        }
        return arr;
    }
}
