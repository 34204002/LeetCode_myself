package 二叉树;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 94. 二叉树的中序遍历 - 解法一：递归
 * 
 * 【题目描述】
 * 给定一个二叉树的根节点 root ，返回它的中序遍历。
 * 中序遍历顺序：左子树 → 根节点 → 右子树
 * 
 * 【示例】
 * 输入：root = [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出：[1,3,2]
 * 
 * 【解题思路】
 * 核心思想：递归实现中序遍历
 * 
 * 算法流程：
 * 1. 如果当前节点为空，返回结果列表
 * 2. 递归遍历左子树
 * 3. 访问当前节点（添加到列表）
 * 4. 递归遍历右子树
 * 
 * 【执行示例】
 *     1
 *    / \
 *   2   3
 * 
 * 调用 inorderTraversal(1)：
 * - 遍历左子树 inorderTraversal(2)：
 *   · 左子树为空，返回
 *   · 添加 2
 *   · 右子树为空，返回
 * - 添加 1
 * - 遍历右子树 inorderTraversal(3)：
 *   · 左子树为空，返回
 *   · 添加 3
 *   · 右子树为空，返回
 * 
 * 结果：[2,1,3] ✓
 * 
 * 【时间复杂度】O(n) - 每个节点访问一次
 * 【空间复杂度】O(n) - 递归栈深度最坏 O(n)，结果列表 O(n)
 * 
 * 【关键点】
 * - 第7行：list 作为成员变量，累积遍历结果
 * - 第9-10行：空节点直接返回
 * - 第11-12行：递归遍历左子树
 * - 第13行：访问当前节点
 * - 第14-15行：递归遍历右子树
 * 
 * 【注意】
 * - list 是成员变量，多次调用方法时会累积结果
 * - 实际使用时需要在调用前清空 list 或将其改为局部变量
 */
public class LC_94_inorderTraversal_1 {
    List<Integer> list=new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root==null)
            return list;
        if(root.left!=null)
            inorderTraversal(root.left);  // 递归遍历左子树
        list.add(root.val);               // 访问当前节点
        if(root.right!=null)
            inorderTraversal(root.right); // 递归遍历右子树
        return list;
    }
}
