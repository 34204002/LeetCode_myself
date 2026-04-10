package 二叉树;

/**
 * LeetCode 104. 二叉树的最大深度 - 解法一：递归（DFS）
 * 
 * 【题目描述】
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 * 
 * 【示例】
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：3
 * 
 * 【解题思路】
 * 核心思想：递归计算左右子树的最大深度
 * 
 * 算法流程：
 * 1. 如果 root == null，返回 0（空树深度为0）
 * 2. 递归计算左子树深度：maxDepth(root.left) + 1
 * 3. 递归计算右子树深度：maxDepth(root.right) + 1
 * 4. 返回两者的最大值
 * 
 * 【执行示例】
 *       3
 *      / \
 *     9  20
 *       /  \
 *      15   7
 * 
 * maxDepth(3) = max(maxDepth(9)+1, maxDepth(20)+1)
 *             = max(1, max(2, 2)+1)
 *             = max(1, 3)
 *             = 3 ✓
 * 
 * 【时间复杂度】O(n) - 每个节点访问一次
 * 【空间复杂度】O(h) - h为树的高度，递归栈的开销
 * 
 * 【关键点】
 * - 第5-6行：递归终止条件（空节点返回0）
 * - 第7行：递归公式，+1表示当前层
 */
public class LC_104_maxDepth_1 {
    public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;
        return Math.max(maxDepth(root.left)+1,maxDepth(root.right)+1);
    }
}
