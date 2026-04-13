package 二叉树;

/**
 * LeetCode 543. 二叉树的直径 - 解法一：递归（DFS）
 * 
 * 【题目描述】
 * 给你一棵二叉树的根节点 root ，返回该树的 直径 。
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间边数表示。
 * 
 * 【示例】
 * 输入：root = [1,2,3,4,5]
 * 输出：3
 * 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
 * 
 * 【解题思路】
 * 核心思想：对于每个节点，计算经过该节点的最长路径（左子树高度 + 右子树高度）
 * 
 * 算法流程：
 * 1. 定义全局变量 MaxD 记录最大直径
 * 2. 递归计算每个节点的高度：
 *    - 如果 node == null，返回 0（空节点高度为0）
 *    - 递归计算左子树高度：height(node.left)
 *    - 递归计算右子树高度：height(node.right)
 *    - 更新最大直径：MaxD = max(MaxD, leftLen + rightLen)
 *    - 返回当前节点高度：max(leftLen, rightLen) + 1
 * 3. 从根节点开始递归，最终返回 MaxD
 * 
 * 【执行示例】
 *       1
 *      / \
 *     2   3
 *    / \
 *   4   5
 * 
 * height(4) = 0, height(5) = 0
 * 节点2: leftLen=1, rightLen=1, MaxD=max(0, 1+1)=2, 返回 2
 * 节点3: leftLen=0, rightLen=0, MaxD=max(2, 0+0)=2, 返回 1
 * 节点1: leftLen=2, rightLen=1, MaxD=max(2, 2+1)=3, 返回 3
 * 结果：MaxD = 3 ✓
 * 
 * 【时间复杂度】O(n) - 每个节点访问一次
 * 【空间复杂度】O(h) - h为树的高度，递归栈的开销
 * 
 * 【关键点】
 * - 第4行：全局变量 MaxD 记录最大直径
 * - 第9行：调用 height(root) 启动递归
 * - 第18行：核心逻辑，更新经过当前节点的最长路径
 * - 第19行：返回当前节点的高度（用于父节点计算）
 */
public class LC_543_diameterOfBinaryTree_1 {
    int MaxD=0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        height(root);
        return MaxD;
    }

    private int height(TreeNode node) {
        if(node==null)
            return 0;
        int leftLen = height(node.left);
        int rightLen = height(node.right) ;
        MaxD=Math.max(MaxD,leftLen+rightLen);
        return Math.max(leftLen,rightLen)+1;
    }

}
