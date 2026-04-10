package 二叉树;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 104. 二叉树的最大深度 - 解法二：层序遍历（BFS）
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
 * 核心思想：层序遍历，每处理完一层，深度加1
 * 
 * 算法流程：
 * 1. 如果 root == null，返回 0
 * 2. 将根节点加入队列
 * 3. 记录当前层节点数 curSize = queue.size()
 * 4. 当队列不为空时循环：
 *    - 遍历当前层的所有节点（curSize个）
 *    - 取出节点，将其左右子节点入队
 *    - 更新 curSize 为下一层的节点数
 *    - depth++（完成一层，深度+1）
 * 5. 返回 depth
 * 
 * 【执行示例】
 *       3        <- 第1层，depth=1
 *      / \
 *     9  20      <- 第2层，depth=2
 *       /  \
 *      15   7    <- 第3层，depth=3
 * 
 * 第1轮：处理节点[3]，子节点[9,20]入队，depth=1
 * 第2轮：处理节点[9,20]，子节点[15,7]入队，depth=2
 * 第3轮：处理节点[15,7]，无子节点，depth=3
 * 结果：3 ✓
 * 
 * 【时间复杂度】O(n) - 每个节点访问一次
 * 【空间复杂度】O(n) - 队列最多存储一层的节点
 * 
 * 【关键点】
 * - 第13行：初始化 curSize 为第一层节点数
 * - 第15-21行：内层循环处理当前层的所有节点
 * - 第22行：更新 curSize 为下一层节点数
 * - 第23行：完成一层，深度+1
 * 
 * 【注意】
 * - queue.poll() 不会返回 null，因为只有非null节点才会入队
 * - IDE可能报NPE警告，但这是误报，可以忽略
 */
public class LC_104_maxDepth_2 {
    Queue<TreeNode> queue=new ArrayDeque<>();
    public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;
        int depth=0;
        queue.add(root);
        int curSize=queue.size();
        while (!queue.isEmpty()){
            for (int i = 0; i < curSize; i++) {
                TreeNode node=queue.poll();
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }
            curSize= queue.size();
            depth++;
        }
        return depth;
    }
}
