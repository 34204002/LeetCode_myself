package 二叉树;

/**
 * LeetCode 230. 二叉搜索树中第 K 小的元素 - 解法一：中序遍历
 * 
 * 【题目描述】
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（从 1 开始计数）。
 * 
 * 【示例】
 * 输入：root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出：1
 * 
 * 【解题思路】
 * 核心思想：利用 BST 的中序遍历是递增序列的特性，找到第 k 个访问的节点
 * 
 * 算法流程：
 * 1. 初始化 count=0（计数器）、flag=false（找到标志）
 * 2. 中序遍历二叉树（左 → 根 → 右）
 * 3. 每访问一个节点，count++
 * 4. 当 count==k 时，记录当前节点值到 res，设置 flag=true
 * 5. flag 为 true 时提前终止遍历
 * 
 * 【执行示例】
 *       3
 *      / \
 *     1   4
 *      \
 *       2
 * k=2
 * 
 * 中序遍历顺序：1 → 2 → 3 → 4
 * 
 * 访问 1：count=1, count!=2, 继续
 * 访问 2：count=2, count==2 ✓, res=2, flag=true
 * 提前终止遍历
 * 结果：2 ✓
 * 
 * 【时间复杂度】O(H+k) - H为树高，最多遍历 k 个节点
 * 【空间复杂度】O(H) - 递归栈的开销
 * 
 * 【关键点】
 * - 第4-6行：count 计数器、res 结果、flag 提前终止标志
 * - 第8-10行：初始化状态变量
 * - 第15行：flag 为 true 或节点为空时立即返回
 * - 第17行：先遍历左子树（较小值）
 * - 第18行：访问当前节点，计数器+1
 * - 第19-22行：找到第 k 小元素，记录并设置终止标志
 * - 第23行：如果还没找到，继续遍历右子树
 */
public class LC_230_kthSmallest_1 {
    int count;
    int res;
    boolean flag;
    public int kthSmallest(TreeNode root, int k) {
        count=0;
        res=0;
        flag=false;
        dfs(root,k);
        return res;
    }
    private void dfs(TreeNode node,int k){
        if(flag||node==null)
            return;
        dfs(node.left,k);
        count++;
        if(count==k) {
            res=node.val;
            flag=true;
        }
        dfs(node.right,k);
    }
}
