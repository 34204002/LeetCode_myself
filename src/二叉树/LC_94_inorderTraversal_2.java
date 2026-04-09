package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * LeetCode 94. 二叉树的中序遍历 - 解法二：迭代（栈）
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
 * 核心思想：用栈模拟递归过程
 * 
 * 算法流程：
 * 1. 初始化指针 p 指向 root
 * 2. 当 p 不为空或栈不为空时循环：
 *    a) 一直向左走，将所有节点压入栈
 *    b) 弹出栈顶节点，访问该节点
 *    c) p 指向右子节点，重复上述过程
 * 
 * 【执行示例】
 *     1
 *    / \
 *   2   3
 * 
 * 初始：p=1, stack=[]
 * 
 * 第1轮：
 * - 向左走：stack=[1], p=2
 * - 继续向左：stack=[1,2], p=null
 * - 弹出：p=2, 添加 2, p=2.right=null
 * 
 * 第2轮：
 * - p=null, stack=[1]
 * - 弹出：p=1, 添加 1, p=1.right=3
 * 
 * 第3轮：
 * - 向左走：stack=[3], p=null
 * - 弹出：p=3, 添加 3, p=3.right=null
 * 
 * 结束：stack 为空且 p 为空
 * 结果：[2,1,3] ✓
 * 
 * 【时间复杂度】O(n) - 每个节点入栈出栈各一次
 * 【空间复杂度】O(n) - 栈最多存储 n 个节点
 * 
 * 【对比递归解法】
 * - 递归：代码简洁，但可能栈溢出（树很深时）
 * - 迭代：代码稍复杂，但更可控，避免栈溢出
 * 
 * 【关键点】
 * - 第8行：list 作为成员变量，累积遍历结果
 * - 第10行：用栈存储待处理的节点
 * - 第12行：循环条件，p 不为空或栈不为空
 * - 第13-16行：一直向左走，将路径上的节点压入栈
 * - 第17-18行：弹出栈顶节点并访问
 * - 第19行：转向右子树
 * 
 * 【注意】
 * - list 是成员变量，多次调用方法时会累积结果
 * - 实际使用时需要在调用前清空 list 或将其改为局部变量
 */
public class LC_94_inorderTraversal_2 {
    List<Integer> list=new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        TreeNode p=root;
        while (p!=null||!stack.isEmpty()){
            // 一直向左走，将路径上的节点压入栈
            while (p!=null) {
                stack.add(p);
                p=p.left;
            }
            // 弹出栈顶节点并访问
            p=stack.pop();
            list.add(p.val);
            // 转向右子树
            p=p.right;
        }
        return list;
    }
}
