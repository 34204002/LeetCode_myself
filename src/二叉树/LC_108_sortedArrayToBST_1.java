package 二叉树;

/**
 * LeetCode 108. 将有序数组转换为二叉搜索树 - 解法一：递归（DFS）
 * 
 * 【题目描述】
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * 
 * 【示例】
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案
 * 
 * 【解题思路】
 * 核心思想：选择中间元素作为根节点，递归构建左右子树
 * 
 * 算法流程：
 * 1. 调用 dfs(nums, 0, nums.length-1) 开始递归
 * 2. dfs 函数逻辑：
 *    - 如果 left > right，返回 null（无节点可处理）
 *    - 计算中间位置 mid = (left + right) / 2
 *    - 创建根节点，值为 nums[mid]
 *    - 递归构建左子树：dfs(nums, left, mid-1)
 *    - 递归构建右子树：dfs(nums, mid+1, right)
 *    - 返回根节点
 * 3. 返回构建完成的 BST
 * 
 * 【执行示例】
 * nums = [-10,-3,0,5,9]
 * 
 * 第1层：mid=2, root=0
 *        左子树：[-10,-3], 右子树：[5,9]
 * 
 * 第2层左：mid=0, node=-10
 *          右子树：[-3] → node=-3
 * 
 * 第2层右：mid=3, node=5
 *          右子树：[9] → node=9
 * 
 * 结果：    0
 *         / \
 *       -3   9
 *       /   /
 *     -10  5
 * ✓ 高度平衡 BST
 * 
 * 【时间复杂度】O(n) - 每个元素访问一次
 * 【空间复杂度】O(log n) - 递归栈深度为 log n
 * 
 * 【关键点】
 * - 第6行：从整个数组范围开始递归
 * - 第10-11行：递归终止条件（left > right 时返回null）
 * - 第12行：选择中间元素作为根节点，保证平衡
 * - 第14-15行：递归构建左右子树
 */
public class LC_108_sortedArrayToBST_1 {
    public TreeNode sortedArrayToBST(int[] nums) {

        return dfs(nums,0,nums.length-1);

    }
    private TreeNode dfs(int[] nums,int left,int right){
        if(left>right)
            return null;
        int mid=(left+right)/2;
        TreeNode node=new TreeNode(nums[mid]);
        node.left=dfs(nums,left,mid-1);
        node.right=dfs(nums,mid+1,right);
        return node;
    }
}
