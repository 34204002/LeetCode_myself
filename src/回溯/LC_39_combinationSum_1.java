package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 39. 组合总和 - 解法一：回溯算法
 * 
 * 【题目描述】
 * 给你一个无重复元素的不同整数数组 candidates 和一个目标整数 target，
 * 找出 candidates 中可以使数字和为目标数 target 的所有不同组合，并以列表形式返回。
 * 你可以按任意顺序返回这些组合。
 * candidates 中的同一个数字可以无限制重复被选取。
 * 
 * 【示例】
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 
 * 【解题思路】
 * 核心思想：回溯算法（深度优先搜索 + 状态重置）
 * 
 * 算法流程：
 * 1. 从索引0开始，每次选择一个候选数字加入路径
 * 2. 累加当前路径的和，如果等于target则找到一个有效组合
 * 3. 如果和超过target则剪枝返回
 * 4. 关键：递归时传入当前索引i（而非i+1），允许重复选择同一元素
 * 5. 回溯：撤销选择，尝试下一个候选数字
 * 
 * 【执行示例】candidates = [2,3,6,7], target = 7
 * 
 * 第1层：选择2 (sum=2)
 *   第2层：选择2 (sum=4)
 *     第3层：选择2 (sum=6)
 *       第4层：选择2 (sum=8) → 超过target，剪枝 ✗
 *       第4层：选择3 (sum=9) → 超过target，剪枝 ✗
 *     第3层：选择3 (sum=7) → [2,2,3] ✓
 *     第3层：选择6 (sum=10) → 超过target，剪枝 ✗
 *   第2层：选择3 (sum=5)
 *     第3层：选择3 (sum=8) → 超过target，剪枝 ✗
 *     第3层：选择6 (sum=11) → 超过target，剪枝 ✗
 *   第2层：选择6 (sum=8) → 超过target，剪枝 ✗
 * 第1层：选择3 (sum=3)
 *   第2层：选择3 (sum=6)
 *     第3层：选择3 (sum=9) → 超过target，剪枝 ✗
 *   第2层：选择6 (sum=9) → 超过target，剪枝 ✗
 * 第1层：选择6 (sum=6)
 *   第2层：选择6 (sum=12) → 超过target，剪枝 ✗
 * 第1层：选择7 (sum=7) → [7] ✓
 * 
 * 结果：[[2,2,3],[7]] ✓
 * 
 * 【时间复杂度】O(target/min(candidates) × n) - 最坏情况下的搜索树节点数
 * 【空间复杂度】O(target/min(candidates)) - 递归栈深度
 * 
 * 【关键点】
 * - 第8行：创建结果列表存储所有有效组合
 * - 第9行：启动DFS，初始和为0，起始索引为0
 * - 第13-14行：终止条件 - 找到和为target的组合，添加到结果列表
 * - 第15-16行：剪枝条件 - 和超过target，直接返回
 * - 第17行：从index开始遍历，避免产生重复组合
 * - 第18行：做选择 - 将当前候选数字加入路径
 * - 第19行：递归调用，注意传入i而非i+1，允许重复选择
 * - 第20行：撤销选择 - 移除最后一个元素，实现回溯
 * 
 * 【注意】
 * - 第14行：必须创建新的ArrayList副本，否则后续修改会影响已添加的结果
 * - 第17行：从index开始遍历而非0，避免产生[2,3]和[3,2]这样的重复组合
 * - 第19行：传入i而非i+1是本题关键，允许同一元素被重复选择
 * - 第20行：使用removeLast()移除最后一个元素，实现状态回溯
 */
public class LC_39_combinationSum_1 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list=new ArrayList<>();
        dfs(candidates,new ArrayList<>(),target,0,0,list);
        return list;
    }
    private static void dfs(int[] candidates,List<Integer> path,int target,int sum,int index,List<List<Integer>> list){
        if(sum==target)
            list.add(new ArrayList<>(path));
        if(sum>=target)
            return;
        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(candidates,path,target,sum+candidates[i],i,list);
            path.removeLast();
        }
    }
}
