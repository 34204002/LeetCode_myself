package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 199. 二叉树的右视图 - 解法一：层序遍历（BFS）
 * 
 * 【题目描述】
 * 给定一个二叉树的根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 
 * 【示例】
 * 输入：root = [1,2,3,null,5,null,4]
 *    1
 *   / \
 *  2   3
 *   \   \
 *    5   4
 * 输出：[1,3,4]
 * 
 * 【解题思路】
 * 核心思想：层序遍历，每层只取最右边的节点（该层最后一个节点）
 * 
 * 算法流程：
 * 1. 如果 root 为空，返回空列表
 * 2. 使用队列进行层序遍历
 * 3. 记录当前层的节点数 curLen
 * 4. 遍历当前层的所有节点：
 *    - 弹出队首节点
 *    - 如果是当前层最后一个节点（i==curLen-1），加入结果列表
 *    - 将左右子节点加入队列
 * 5. 更新 curLen 为下一层的节点数
 * 6. 重复直到队列为空
 * 
 * 【执行示例】
 *       1
 *      / \
 *     2   3
 *      \   \
 *       5   4
 * 
 * 第1层：curLen=1, i=0, 节点1是最后一个 → 加入 [1]
 *        子节点：2, 3 入队
 * 
 * 第2层：curLen=2
 *        i=0, 节点2, 不是最后一个
 *        i=1, 节点3, 是最后一个 → 加入 [1,3]
 *        子节点：5, 4 入队
 * 
 * 第3层：curLen=2
 *        i=0, 节点5, 不是最后一个
 *        i=1, 节点4, 是最后一个 → 加入 [1,3,4]
 * 
 * 结果：[1,3,4] ✓
 * 
 * 【时间复杂度】O(n) - 每个节点访问一次
 * 【空间复杂度】O(w) - w为树的最大宽度，队列的开销
 * 
 * 【关键点】
 * - 第10-11行：空树直接返回空列表
 * - 第13行：使用队列实现 BFS
 * - 第15行：记录当前层的节点数量
 * - 第17行：遍历当前层的所有节点
 * - 第19-20行：只将每层最后一个节点加入结果
 * - 第21-24行：将子节点加入队列（先左后右）
 * - 第26行：更新下一层的节点数量
 */
public class LC_199_rightSideView_1 {
    public List<Integer> rightSideView(TreeNode root) {
        if(root==null)
            return new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        int curLen=queue.size();
        while (!queue.isEmpty()){
            for (int i = 0; i < curLen; i++) {
                TreeNode node=queue.poll();
                if(i==curLen-1)
                    list.add(node.val);
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }
            curLen=queue.size();
        }
        return list;
    }
}
