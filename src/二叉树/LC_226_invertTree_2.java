package 二叉树;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 226. 翻转二叉树 - 解法二：层序遍历（BFS）
 * 
 * 【题目描述】
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * 翻转操作：交换每个节点的左右子树。
 * 
 * 【示例】
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * 
 * 【解题思路】
 * 核心思想：层序遍历，逐层交换每个节点的左右子树
 * 
 * 算法流程：
 * 1. 如果 root == null，返回 null
 * 2. 将根节点加入队列
 * 3. 当队列不为空时循环：
 *    - 取出队首节点
 *    - 交换该节点的左右子树
 *    - 如果左子树非空，加入队列
 *    - 如果右子树非空，加入队列
 * 4. 返回根节点
 * 
 * 【执行示例】
 *       4              4
 *      / \           /   \
 *     2   7   ->    7     2
 *    / \ / \       / \   / \
 *   1  3 6  9     9  6  3   1
 * 
 * 第1轮：处理节点4，交换子树 (2↔7)，队列=[7,2]
 * 第2轮：处理节点7，交换子树 (6↔9)，队列=[2,9,6]
 * 第3轮：处理节点2，交换子树 (1↔3)，队列=[9,6,3,1]
 * 第4-7轮：处理叶子节点，无子节点可交换
 * 结果：✓
 * 
 * 【时间复杂度】O(n) - 每个节点访问一次
 * 【空间复杂度】O(n) - 队列最多存储一层的节点
 * 
 * 【关键点】
 * - 第9-10行：边界条件（空树直接返回）
 * - 第11行：根节点入队
 * - 第13行：取出当前节点
 * - 第14-16行：交换左右子树
 * - 第17-20行：非空子节点入队（保证后续处理）
 * - 第23行：返回翻转后的根节点
 * 
 * 【注意】
 * - queue.poll() 不会返回 null，因为只有非null节点才会入队
 * - IDE可能报NPE警告，但这是误报，可以忽略
 */
public class LC_226_invertTree_2 {
    Queue<TreeNode> queue=new ArrayDeque<>();
    public TreeNode invertTree(TreeNode root) {
        if(root==null)
            return null;
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node=queue.poll();
            TreeNode temp=node.left;
            node.left=node.right;
            node.right=temp;
            if(node.left!=null)
                queue.add(node.left);
            if(node.right!=null)
                queue.add(node.right);

        }
        return root;
    }
}
