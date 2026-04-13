package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 102. 二叉树的层序遍历 - 解法一：BFS（队列）
 * 
 * 【题目描述】
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * 
 * 【示例】
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 * 
 * 【解题思路】
 * 核心思想：使用队列进行广度优先搜索（BFS），逐层处理节点
 * 
 * 算法流程：
 * 1. 如果 root == null，返回空列表
 * 2. 初始化队列和结果列表，将根节点加入队列
 * 3. 记录当前层节点数 count = queue.size()
 * 4. 当队列不为空时循环：
 *    - 创建当前层的列表 list1
 *    - 遍历当前层的所有节点（count 次）：
 *      * 从队列取出节点
 *      * 将节点值加入 list1
 *      * 将左右子节点加入队列（即使为null也加入）
 *    - 更新 count 为下一层的节点数
 *    - 如果 list1 非空，加入结果列表
 * 5. 返回结果列表
 * 
 * 【执行示例】
 *       3
 *      / \
 *     9  20
 *       /  \
 *      15   7
 * 
 * 第1层：queue=[3], count=1
 *        处理节点3 → list1=[3], queue=[9,20]
 *        结果：[[3]]
 * 
 * 第2层：queue=[9,20], count=2
 *        处理节点9 → list1=[9], queue=[20,null,null]
 *        处理节点20 → list1=[9,20], queue=[null,null,15,7]
 *        结果：[[3],[9,20]]
 * 
 * 第3层：queue=[null,null,15,7], count=4
 *        跳过null节点，处理15和7 → list1=[15,7]
 *        结果：[[3],[9,20],[15,7]] ✓
 * 
 * 【时间复杂度】O(n) - 每个节点访问一次
 * 【空间复杂度】O(n) - 队列最多存储一层的节点
 * 
 * 【关键点】
 * - 第17行：记录当前层节点数，确保逐层处理
 * - 第20-26行：for循环处理当前层的所有节点
 * - 第24-25行：将左右子节点加入队列（包括null节点）
 * - 第28行：更新count为下一层节点数
 * - 第29-30行：过滤空层，只添加非空列表
 */
public class LC_102_levelOrder_1 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<List<Integer>> list=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        int count=queue.size();
        while (!queue.isEmpty()){
            List<Integer> list1=new ArrayList<>();
            for (int i = 0; i < count; i++) {
                TreeNode node=queue.poll();
                if(node!=null) {
                    list1.add(node.val);
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            count=queue.size();
            if(!list1.isEmpty())
                list.add(list1);
        }
        return list;
    }

}
