package 二叉树;


/**
 * LeetCode 98. 验证二叉搜索树 - 解法一：中序遍历
 * 
 * 【题目描述】
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效二叉搜索树定义：
 * - 节点的左子树只包含小于当前节点的数
 * - 节点的右子树只包含大于当前节点的数
 * - 所有左子树和右子树自身必须也是二叉搜索树
 * 
 * 【示例】
 * 输入：root = [2,1,3]
 *    2
 *   / \
 *  1   3
 * 输出：true
 * 
 * 【解题思路】
 * 核心思想：利用 BST 的中序遍历是递增序列的特性
 * 
 * 算法流程：
 * 1. 初始化 last=Long.MIN_VALUE（记录上一个访问的节点值）
 * 2. 中序遍历二叉树（左 → 根 → 右）
 * 3. 每次访问节点时，检查 node.val > last
 * 4. 如果不满足递增条件，设置 flag=false
 * 5. 更新 last 为当前节点值
 * 
 * 【执行示例】
 *       2
 *      / \
 *     1   3
 * 
 * 中序遍历顺序：1 → 2 → 3
 * 
 * 访问 1：last=MIN_VALUE, 1>MIN_VALUE ✓, last=1
 * 访问 2：last=1, 2>1 ✓, last=2
 * 访问 3：last=2, 3>2 ✓, last=3
 * 结果：true ✓
 * 
 * 【时间复杂度】O(n) - 每个节点访问一次
 * 【空间复杂度】O(h) - h为树的高度，递归栈的开销
 * 
 * 【关键点】
 * - 第6行：last 用 long 类型避免 Integer.MIN_VALUE 边界问题
 * - 第7行：flag 标志位，发现非法立即终止
 * - 第12行：初始化为 Long.MIN_VALUE 处理最小值节点
 * - 第17行：!flag 提前终止无效遍历
 * - 第20行：检查是否满足严格递增（<= 而非 <）
 */
public class LC_98_isValidBST_1 {
    long last;
    boolean flag;
    public boolean isValidBST(TreeNode root) {
        if(root==null)
            return false;
        flag=true;
        last=Long.MIN_VALUE;
        dfs(root);
        return flag;
    }
    private void dfs(TreeNode node){
        if(node==null||!flag)
            return;
        dfs(node.left);
        if(node.val<=last){
            flag=false;
        }
        last=node.val;
        dfs(node.right);
    }
}
