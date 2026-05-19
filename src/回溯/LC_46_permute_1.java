package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 46. 全排列 - 解法一：回溯算法
 * 
 * 【题目描述】
 * 给定一个不含重复数字的数组 nums ，返回其所有可能的全排列。
 * 可以按任意顺序返回答案。
 * 
 * 【示例】
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 
 * 【解题思路】
 * 核心思想：回溯算法（深度优先搜索 + 状态重置）
 * 
 * 算法流程：
 * 1. 使用 visited 数组标记元素是否已被使用
 * 2. 从空路径开始，每次选择一个未使用的元素加入路径
 * 3. 当路径长度等于数组长度时，找到一个完整排列
 * 4. 回溯：撤销选择，尝试其他可能性
 * 
 * 【执行示例】nums = [1,2,3]
 * 
 * 第1层：选择1
 *   第2层：选择2
 *     第3层：选择3 → [1,2,3] ✓
 *   第2层：选择3
 *     第3层：选择2 → [1,3,2] ✓
 * 第1层：选择2
 *   第2层：选择1
 *     第3层：选择3 → [2,1,3] ✓
 *   第2层：选择3
 *     第3层：选择1 → [2,3,1] ✓
 * 第1层：选择3
 *   第2层：选择1
 *     第3层：选择2 → [3,1,2] ✓
 *   第2层：选择2
 *     第3层：选择1 → [3,2,1] ✓
 * 
 * 结果：6种排列 ✓
 * 
 * 【时间复杂度】O(n×n!) - n!个排列，每个排列需要O(n)复制
 * 【空间复杂度】O(n) - 递归栈深度O(n)，visited数组O(n)
 * 
 * 【关键点】
 * - 第8行：创建结果列表存储所有排列
 * - 第10行：初始化visited数组（默认值为0），启动DFS
 * - 第14-15行：终止条件 - 路径长度等于数组长度，找到完整排列
 * - 第17行：检查元素是否未被使用
 * - 第18-19行：做选择 - 加入路径并标记已访问
 * - 第20行：递归进入下一层
 * - 第21-22行：撤销选择 - 回溯，恢复状态
 * 
 * 【注意】
 * - 第15行：必须创建新的ArrayList副本，否则后续修改会影响已添加的结果
 * - 第22行：使用removeLast()移除最后一个元素，实现回溯
 */
public class LC_46_permute_1 {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list=new ArrayList<>();

        dfs(nums,new int[nums.length],list,new ArrayList<>());
        return list;
    }
    private static void dfs(int[] nums,int[] visited,List<List<Integer>> list,List<Integer> path){
        if(path.size()==nums.length)
            list.add(new ArrayList<>(path));

        for (int i = 0; i < nums.length; i++) {
            if(visited[i]==0) {
                path.add(nums[i]);
                visited[i] = 1;
                dfs(nums, visited,list,path);
                visited[i] = 0;
                path.removeLast();
            }
        }
    }

}
