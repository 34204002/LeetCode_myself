package 二叉树;

/**
 * LeetCode 236. 二叉树的最近公共祖先 - 解法二：递归（后序遍历）
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
 * 核心思想：后序遍历递归，自底向上传递"找到 p 或 q"的信息
 *
 * 定义递归函数返回值的语义：
 *   - 返回 null：以当前节点为根的子树中不存在 p 和 q
 *   - 返回非 null：以当前节点为根的子树中存在 p 或 q（或两者的最近公共祖先）
 *
 * 算法流程：
 * 1. 递归终止条件：root 为 null 时返回 null（空子树无结果）
 *                    root 为 p 或 q 时返回 root（找到目标节点）
 * 2. 后序遍历：先递归查找左子树，再递归查找右子树
 * 3. 根据左右子树的返回值分三种情况：
 *    - 左右均非 null：p 和 q 分别位于左右子树中，当前 root 即最近公共祖先
 *    - 仅左非 null：p 和 q 都在左子树，返回左子树的结果
 *    - 仅右非 null：p 和 q 都在右子树，返回右子树的结果
 *
 * 【执行示例】root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
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
 * 递归过程（后序遍历）：
 *   dfs(3): 左=dfs(5), 右=dfs(1)
 *     dfs(5): 左=dfs(6), 右=dfs(2)
 *       dfs(6): left=null, right=null, return null（没找到 p,q）
 *       dfs(2): 左=dfs(7), 右=dfs(4)
 *         dfs(7): return null
 *         dfs(4): root==q → return 4
 *       dfs(2): left=null, right=4 → return right=4（右子树有 q）
 *     dfs(5): left=null, right=4 → return right=4（右子树有 q）
 *       ※ 此时 root=5, 左=null, 右=4, root=5==p → ...等等，root=5 时
 *       实际上 root=5 时，p=5，在第9行就 return 5 了！
 *
 * 重新追踪（考虑 p=5 提前返回）：
 *   dfs(3): 左=dfs(5)
 *     dfs(5): root==p → return 5  （提前返回，不递归子树）
 *   由于 dfs(5) 返回了 5，左子树搜索提前结束
 *   dfs(3): 左=5, 右=dfs(1)
 *     dfs(1): 左=dfs(0) → null, 右=dfs(8) → null, return null
 *   dfs(3): 左=5, 右=null → return left=5
 *
 * 返回 5 ✓（但这是错的！q=4 没有被找到！）
 *
 * 问题：当 root==p 时直接返回，不会继续搜索右子树中的 q。
 * 但如果 p 本身不是 LCA（即 q 在 p 的子树中），p 确实应该是答案，
 * 因为"一个节点也可以是它自己的祖先"——p 是 q 的祖先！
 *
 *   dfs(5) → p=5, return 5
 *   q=4 在 5 的子树中，5 确实是 LCA ✓
 *
 * 【时间复杂度】O(n) - 每个节点最多被访问一次
 * 【空间复杂度】O(h) - 递归调用栈深度，h 为树的高度，最坏 O(n)
 *
 * 【关键点】
 * - 第9行：root==p 或 root==q 时直接返回 root，利用"节点自身可以是祖先"的性质
 * - 第10-11行：后序遍历，先递归获取左右子树的查找结果
 * - 第12-13行：左右均非 null → 当前节点是 LCA
 * - 第14行：仅一侧非 null → 返回该侧结果（向上传递）
 *
 * 【注意】
 * - 第9行：条件 root==p||root==q 的优先级高于递归搜索
 *   这意味着如果 p 是 q 的祖先（或反过来），会提前在 p 处返回，不会继续搜索 q
 *   但这个行为是正确的——p 就是最近公共祖先
 * - 第9行：用 == 比较引用而非 .equals()，因为题目保证 p、q 是树中实际节点
 * - 第14行：当 left 和 right 同时为 null 时，返回 null（当前子树中无 p 和 q）
 *   但这行写的是 `left==null?right:left`，等价于 `left!=null?left:right`
 *   三目运算的正确性：
 *     left==null → true → return right（right 可能为 null 或非 null）
 *     left==null → false → return left（left 一定非 null）
 *   即：谁非 null 就返回谁，都为 null 返回 null ✓
 */
public class LC_236_lowestCommonAncestor_2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null||root==p||root==q){
            return root;
        }
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
        if(left!=null&&right!=null){
            return root;
        }
        return left==null?right:left;
    }

}
