package 二叉树;

/**
 * LeetCode 437. 路径总和 III - 解法一：双重递归（暴力法）
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
 * 核心思想：对每个节点，计算以该节点为起点的路径和等于 targetSum 的数量
 * 
 * 算法流程：
 * 1. 如果 root == null，返回 0
 * 2. 计算以当前节点为起点的路径数：dfs(root, targetSum, 0)
 * 3. 递归计算左子树中满足条件的路径数：pathSum(root.left, targetSum)
 * 4. 递归计算右子树中满足条件的路径数：pathSum(root.right, targetSum)
 * 5. 返回三者之和
 * 
 * dfs 函数：计算从当前节点向下延伸，路径和等于 targetSum 的路径数
 * - 如果 root == null，返回 0
 * - 累加当前节点值到 curSum
 * - 如果 curSum + root.val == targetSum，count = 1（找到一条路径）
 * - 继续递归左右子树，累加结果
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
 * - 以 10 为起点：无满足条件的路径
 * - 以 5 为起点：5→3、5→2→1，共 2 条
 * - 以 -3 为起点：-3→11，共 1 条
 * - 其他节点为起点：无满足条件的路径
 * 结果：3 ✓
 * 
 * 【时间复杂度】O(n²) - 最坏情况每个节点都要遍历其所有子孙节点
 * 【空间复杂度】O(h) - h 为树的高度，递归栈的开销
 * 
 * 【关键点】
 * - 第7行：外层 pathSum 遍历每个节点作为起点
 * - 第9-16行：内层 dfs 计算从某节点出发的满足条件的路径数
 * - 第14行：判断当前路径和是否等于 targetSum
 * - 注意：即使找到一条路径，仍要继续向下搜索（可能存在更长的满足条件的路径）
 */
public class LC_437_pathSum_1 {
    public int pathSum(TreeNode root, int targetSum) {
        if(root==null)
            return 0;
        return dfs(root,targetSum,0)+pathSum(root.left,targetSum)+pathSum(root.right,targetSum);
    }
    private int dfs(TreeNode root,int targetSum,long curSum){
        int count=0;
        if(root==null) {
            return 0;
        }
        if(targetSum==curSum+root.val)
            count=1;
        return count+dfs(root.left,targetSum,curSum+ root.val)+dfs(root.right,targetSum,curSum+ root.val);
    }
}
