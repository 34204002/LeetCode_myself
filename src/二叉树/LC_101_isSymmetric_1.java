package 二叉树;

/**
 * LeetCode 101. 对称二叉树 - 解法一：递归（DFS）
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
 * 核心思想：递归比较镜像位置的节点
 * 
 * 算法流程：
 * 1. 如果 root == null，返回 true（空树对称）
 * 2. 调用 dfs(root.left, root.right) 比较左右子树
 * 3. dfs 函数逻辑：
 *    - 如果两个节点都为 null，返回（对称）
 *    - 如果一个为 null 一个不为 null，flag=false（不对称）
 *    - 如果两个节点值不相等，flag=false（不对称）
 *    - 递归比较：rl.left ↔ rr.right（镜像位置）
 *    - 递归比较：rl.right ↔ rr.left（镜像位置）
 * 4. 返回 flag
 * 
 * 【执行示例】
 *       1
 *      / \
 *     2   2
 *    / \ / \
 *   3  4 4  3
 * 
 * dfs(2L, 2R):
 *   - 值相等 ✓
 *   - dfs(3, 3): 值相等，都是叶子 ✓
 *   - dfs(4, 4): 值相等，都是叶子 ✓
 * 结果：true ✓
 * 
 * 【时间复杂度】O(n) - 每个节点访问一次
 * 【空间复杂度】O(h) - h为树的高度，递归栈的开销
 * 
 * 【关键点】
 * - 第8行：初始化 flag=true
 * - 第11行：从根的左右子节点开始递归比较
 * - 第15-16行：两个都为null，对称，返回
 * - 第17-19行：一个为null一个不为null，不对称
 * - 第21-23行：值不相等，不对称
 * - 第24-27行：只在 flag=true 时继续递归（剪枝优化）
 */
public class LC_101_isSymmetric_1 {
    boolean flag;
    public boolean isSymmetric(TreeNode root) {
        flag=true;
        if(root==null)
            return true;
        dfs(root.left,root.right);
        return flag;
    }
    void dfs(TreeNode rl,TreeNode rr){
        if(rl==null&&rr==null)
            return;
        else if (rl==null||rr==null){
            flag=false;
        }

        if(flag&&rl.val!=rr.val){
            flag=false;
        }
        if(flag)
            dfs(rl.left,rr.right);
        if(flag)
            dfs(rl.right,rr.left);
    }

}
