package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 101. 对称二叉树 - 解法二：层序遍历（BFS）
 * 
 * 【题目描述】
 * 给你一个二叉树的根节点 root ，检查它是否轴对称。
 * 对称二叉树：左子树和右子树关于中心轴镜像对称。
 * 
 * 【示例】
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 * 
 * 【解题思路】
 * 核心思想：用两个队列分别遍历左右子树，按镜像顺序比较节点
 * 
 * 算法流程：
 * 1. 初始化两个队列 lQueue 和 rQueue
 * 2. 将 root.left 加入 lQueue，root.right 加入 rQueue
 * 3. 当两个队列都不为空时循环：
 *    - 分别从两个队列取出节点 lNode 和 rNode
 *    - 如果任一为 null：
 *      - 如果不同时为 null，返回 false
 *      - 否则 continue（跳过本次循环）
 *    - 如果值不相等，返回 false
 *    - 按镜像顺序入队：
 *      - lQueue: 先加 left，再加 right
 *      - rQueue: 先加 right，再加 left（镜像）
 * 4. 循环结束后，两个队列都为空则对称
 * 
 * 【执行示例】
 *       1
 *      / \
 *     2L  2R
 *    / \ / \
 *   3  4 4  3
 * 
 * 初始：lQueue=[2L], rQueue=[2R]
 * 
 * 第1轮：lNode=2L, rNode=2R，值相等 ✓
 *       lQueue=[3,4], rQueue=[3,4]（rQueue先加right再加left）
 * 
 * 第2轮：lNode=3, rNode=3，值相等 ✓
 *       lQueue=[4,null,null], rQueue=[4,null,null]
 * 
 * 第3轮：lNode=4, rNode=4，值相等 ✓
 *       lQueue=[null,null,null,null], rQueue=[null,null,null,null]
 * 
 * 第4-7轮：处理null节点，continue
 * 结果：true ✓
 * 
 * 【时间复杂度】O(n) - 每个节点访问一次
 * 【空间复杂度】O(n) - 队列最多存储一层的节点
 * 
 * 【关键点】
 * - 第9-10行：使用 LinkedList（允许null元素），不能用 ArrayDeque
 * - 第11-12行：初始化队列，分别加入左右子节点
 * - 第16-19行：处理null节点的情况
 * - 第21-23行：值不相等直接返回false
 * - 第24-27行：按镜像顺序入队（关键！）
 * - 第29行：简化判断，两个队列都为空才对称
 * 
 * 【注意】
 * - ArrayDeque 不允许添加 null 元素，会抛 NPE
 * - 必须使用 LinkedList 或 ArrayList 作为队列实现
 */
public class LC_101_isSymmetric_2 {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> lQueue=new LinkedList<>();
        Queue<TreeNode> rQueue=new LinkedList<>();
        lQueue.add(root.left);
        rQueue.add(root.right);
        while (!lQueue.isEmpty()&&!rQueue.isEmpty()){
            TreeNode lNode=lQueue.poll();
            TreeNode rNode=rQueue.poll();
            if(lNode==null||rNode==null){
                if(lNode!=rNode)
                    return false;
                continue;
            }
            if(lNode.val!=rNode.val){
                return false;
            }
            lQueue.add(lNode.left);
            lQueue.add(lNode.right);
            rQueue.add(rNode.right);
            rQueue.add(rNode.left);
        }
        return lQueue.isEmpty() && rQueue.isEmpty();
    }

}
