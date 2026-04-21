package 二叉树;

import java.util.HashMap;

/**
 * LeetCode 437. 路径总和 III - 解法二：前缀和 + 哈希表优化
 * 
 * 【题目描述】
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 
 * 【示例】
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 
 * 【解题思路】
 * 核心思想：利用前缀和 + 哈希表，将问题转化为"两数之差"问题
 * 
 * 前缀和定义：从根节点到当前节点的路径上所有节点值的和
 * 关键性质：如果 curSum - targetSum 在之前出现过，说明存在一段子路径的和等于 targetSum
 * 
 * 算法流程：
 * 1. 初始化 map，map.put(0L, 1) 表示前缀和为 0 的路径出现 1 次（虚拟起点）
 * 2. DFS 遍历二叉树：
 *    a. 更新当前前缀和 curSum += root.val
 *    b. 查询 map 中是否存在 curSum - targetSum，存在则累加其出现次数到 count
 *    c. 将当前 curSum 加入 map（出现次数 +1）
 *    d. 递归处理左右子树
 *    e. 回溯：将当前 curSum 从 map 中移除（出现次数 -1）
 * 3. 返回 count
 * 
 * 【执行示例】
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 * 
 * targetSum = 8
 * 
 * 遍历到节点 5（路径：10→5）：
 * - curSum = 15
 * - 查询 15 - 8 = 7，不存在
 * - map: {0:1, 10:1, 15:1}
 * 
 * 遍历到节点 3（路径：10→5→3）：
 * - curSum = 18
 * - 查询 18 - 8 = 10，存在（出现1次）→ count = 1
 * - 对应路径：5→3（从节点5到节点3的路径和为8）✓
 * 
 * 遍历到节点 2（路径：10→5→2）：
 * - curSum = 17
 * - 查询 17 - 8 = 9，不存在
 * 
 * 遍历到节点 1（路径：10→5→2→1）：
 * - curSum = 18
 * - 查询 18 - 8 = 10，存在（出现1次）→ count = 1
 * - 对应路径：5→2→1（从节点5到节点1的路径和为8）✓
 * 
 * 遍历到节点 -3（路径：10→-3）：
 * - curSum = 7
 * - 查询 7 - 8 = -1，不存在
 * 
 * 遍历到节点 11（路径：10→-3→11）：
 * - curSum = 18
 * - 查询 18 - 8 = 10，存在（出现1次）→ count = 1
 * - 对应路径：-3→11（从节点-3到节点11的路径和为8）✓
 * 
 * 总结果：3 ✓
 * 
 * 【时间复杂度】O(n) - 每个节点访问一次，哈希表操作 O(1)
 * 【空间复杂度】O(h) - h 为树的高度，哈希表和递归栈的开销
 * 
 * 【关键点】
 * - 第12行：map.put(0L, 1) 初始化虚拟起点，处理从根节点开始的路径
 * - 第22-23行：先查询再添加，避免将当前节点自身计入结果
 * - 第25-28行：记录当前前缀和的出现次数
 * - 第33-36行：回溯时恢复状态，确保不同分支互不干扰
 * - 使用 long 类型避免整数溢出
 */
public class LC_437_pathSum_2 {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null)
            return 0;
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        return dfs(root, map, targetSum, 0L);
    }

    private int dfs(TreeNode root, HashMap<Long, Integer> map, int targetSum, long curSum) {
        if (root == null)
            return 0;
        curSum += root.val;
        
        int count = 0;
        if (map.containsKey(curSum - targetSum))
            count += map.get(curSum - targetSum);
        
        if (map.containsKey(curSum))
            map.put(curSum, map.get(curSum) + 1);
        else
            map.put(curSum, 1);
        
        count += dfs(root.left, map, targetSum, curSum);
        count += dfs(root.right, map, targetSum, curSum);
        
        if (map.get(curSum) == 1)
            map.remove(curSum);
        else
            map.put(curSum, map.get(curSum) - 1);
        return count;
    }
}
