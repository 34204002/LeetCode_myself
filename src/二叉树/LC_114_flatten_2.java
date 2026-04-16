package 二叉树;

/**
 * LeetCode 114. 将二叉树展开为链表 - 解法二：后序遍历 + 分治合并
 * 
 * 【题目描述】
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * - 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null
 * - 展开后的单链表应该与二叉树先序遍历顺序相同
 * 
 * 【示例】
 * 输入：root = [1,2,5,3,4,null,6]
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 即：1 -> 2 -> 3 -> 4 -> 5 -> 6
 * 
 * 【解题思路】
 * 核心思想：后序遍历，自底向上展开子树，然后合并左右链表
 * 
 * 算法流程：
 * 1. 递归展开左子树，使其成为链表
 * 2. 递归展开右子树，使其成为链表
 * 3. 保存原右子树根节点 p
 * 4. 将左子树链表移到右边（node.right = node.left）
 * 5. 清空左指针（node.left = null）
 * 6. 找到新右链表的末尾，接上原右子树链表 p
 * 
 * 【执行示例】
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 
 * 后序遍历顺序：3 → 4 → 2 → 6 → 5 → 1
 * 
 * 处理节点 2：
 * - 左子树已展开：3
 * - 右子树已展开：4
 * - p = 4
 * - 2.right = 3, 2.left = null
 * - dfs2 找到 3 的末尾，接上 4
 * - 结果：2 -> 3 -> 4
 * 
 * 处理节点 1：
 * - 左子树已展开：2 -> 3 -> 4
 * - 右子树已展开：5 -> 6
 * - p = 5
 * - 1.right = 2, 1.left = null
 * - dfs2 找到 4 的末尾，接上 5
 * - 结果：1 -> 2 -> 3 -> 4 -> 5 -> 6 ✓
 * 
 * 【时间复杂度】O(n²) - 每个节点访问一次，但 dfs2 找末尾最坏 O(n)
 * 【空间复杂度】O(h) - h 为树的高度，递归栈的开销
 * 
 * 【为什么必须后序？】
 * - 需要先展开左右子树成链表，才能进行合并
 * - 如果先处理当前节点，会破坏子树结构，导致递归路径错误
 * - 后序保证处理当前节点时，子树已经是完美链表
 * 
 * 【关键点】
 * - 第11-12行：先递归展开左右子树（后序）
 * - 第14行：保存原右子树根节点
 * - 第15-16行：断左链，将左子树移到右边
 * - 第18行：dfs2 找到新右链末尾，接上原右子树
 * - 第21-28行：dfs2 递归找到右链最末端（node.right==null），连接 p
 */
public class LC_114_flatten_2 {
    public void flatten(TreeNode root) {
        dfs(root);
    }
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        //断左断右，接左
        TreeNode p = node.right;
        node.right = node.left;
        node.left = null;
        //接右
        dfs2(node,p);


    }private void dfs2(TreeNode node,TreeNode p){
        if(node==null){
            return;
        }
        dfs2(node.right,p);
        if(node.right==null)
            node.right=p;
    }
}
