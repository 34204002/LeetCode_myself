package 二叉树;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 114. 将二叉树展开为链表 - 解法一：前序遍历 + 重构
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
 * 核心思想：前序遍历收集所有节点，然后重新连接成链表
 * 
 * 算法流程：
 * 1. 初始化 list 存储前序遍历的节点
 * 2. 前序遍历二叉树（根 → 左 → 右），将节点依次加入 list
 * 3. 遍历 list，将每个节点的 right 指向下一个节点，left 设为 null
 * 
 * 【执行示例】
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 
 * 前序遍历顺序：1 → 2 → 3 → 4 → 5 → 6
 * list = [1, 2, 3, 4, 5, 6]
 * 
 * 重构链表：
 * i=0: 1.right=2, 1.left=null
 * i=1: 2.right=3, 2.left=null
 * i=2: 3.right=4, 3.left=null
 * i=3: 4.right=5, 4.left=null
 * i=4: 5.right=6, 5.left=null
 * 
 * 结果：1 -> 2 -> 3 -> 4 -> 5 -> 6 ✓
 * 
 * 【时间复杂度】O(n) - 遍历所有节点两次
 * 【空间复杂度】O(n) - list 存储所有节点
 * 
 * 【关键点】
 * - 第7行：list 作为成员变量存储前序遍历结果
 * - 第10行：每次调用前初始化 list
 * - 第11行：前序遍历收集节点
 * - 第12-15行：重新连接节点成链表
 * - 第19-21行：递归终止条件
 * - 第22行：先添加当前节点（前序）
 * - 第23-24行：递归遍历左右子树
 */
public class LC_114_flatten_1 {
    List<TreeNode> list;

    public void flatten(TreeNode root) {
        list = new ArrayList<>();
        dfs(root);
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).right = list.get(i + 1);
            list.get(i).left = null;
        }
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        list.add(node);
        dfs(node.left);
        dfs(node.right);

    }
}
