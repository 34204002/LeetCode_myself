package 二叉树;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * LeetCode 236. 二叉树的最近公共祖先 - 解法一：DFS 路径法
 *
 * 【题目描述】
 * 给定一个二叉树，找到该树中两个指定节点的最近公共祖先。
 * 最近公共祖先的定义为："对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。"
 *
 * 【示例】
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 *
 * 【解题思路】
 * 核心思想：DFS 回溯收集根到目标节点的路径 + 双路径比对找最后公共节点
 *
 * 算法流程：
 * 1. 使用 DFS（前序遍历 + 回溯）遍历整棵树
 * 2. 维护一个 path 列表记录从根到当前节点的路径
 * 3. 遇到节点 p 时，深拷贝当前路径保存到 list1
 * 4. 遇到节点 q 时，深拷贝当前路径保存到 list2
 * 5. 遍历完成后，同时遍历 list1 和 list2：
 *    - 找到最后一个相同位置的节点，即为最近公共祖先
 *
 * 【执行示例】root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 *
 * 树结构（括号内为节点值）：
 *           3
 *         /   \
 *        5     1
 *       / \   / \
 *      6  2  0   8
 *        / \
 *       7   4
 *
 * DFS 收集路径：
 *   list1（到 p=5）：[3, 5]
 *   list2（到 q=1）：[3, 1]
 *
 * 比较两条路径：
 *   i=0: list1[0]=3, list2[0]=1, 不等（但 3 不是 1...）
 *   等等，i=0: list1[0]=3, list2[0]=3, 相同 → node=3
 *   i=1: list1[1]=5, list2[1]=1, 不同 → 停止
 *   返回 node=3 ✓
 *
 * 【时间复杂度】O(n) - DFS 遍历整棵树一次，路径比较 O(min(path1, path2)) = O(h)
 * 【空间复杂度】O(h) - 递归调用栈 O(h) + 路径存储 O(h)，h 为树的高度
 *
 * 【关键点】
 * - 第11行：递归入口，传入空的 path 列表
 * - 第33-36行：前序遍历将当前节点加入路径
 * - 第38-39行：遇到 p 时深拷贝路径（new ArrayList 创建独立副本）
 * - 第40-41行：遇到 q 时深拷贝路径
 * - 第42-43行：递归遍历左右子树
 * - 第44行：回溯，移除当前节点
 * - 第15-26行：比较两条路径，找最后一个公共节点
 *
 * 【注意】
 * - 第38行：必须用 new ArrayList<>(list) 深拷贝，否则后续回溯会修改已保存的路径
 * - 第17行和第22行：比较用 == 而非 equals，因为路径中的节点引用来自同一棵树
 * - 第15行：取较短路径的长度作为遍历上限，防止越界
 * - 第44行：回溯时必须在递归返回后移除当前节点，恢复路径状态
 * - 递归调用前未剪枝，即使已找到两个节点仍会遍历整棵树，可优化
 */
public class LC_236_lowestCommonAncestor_1 {
    ArrayList<TreeNode> list1;
    ArrayList<TreeNode> list2;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)
            return null;

        dfs(root,new ArrayList<>(),p,q);
        TreeNode node=null;
        if(list1.size()> list2.size()){
            for (int i = 0; i < list2.size(); i++) {
                if(list1.get(i)== list2.get(i)){
                    node=list1.get(i);
                }
            }
        }else{
            for (int i = 0; i < list1.size(); i++) {
                if(list1.get(i)== list2.get(i)){
                    node=list1.get(i);
                }
            }
        }
        return node;
    }
    private void dfs(TreeNode root, ArrayList<TreeNode> list, TreeNode p, TreeNode q){
        if(root==null)
            return;
        list.add(root);
        if(root==p)
            list1= new ArrayList<>(list);
        if(root==q)
            list2= new ArrayList<>(list);
        dfs(root.left,list,p,q);
        dfs(root.right,list,p,q);
        list.remove(root);
    }
}
