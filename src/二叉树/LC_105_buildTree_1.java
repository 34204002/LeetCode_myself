package 二叉树;

import java.util.Arrays;

/**
 * LeetCode 105. 从前序与中序遍历序列构造二叉树
 * 
 * 【题目描述】
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 
 * 【示例】
 * 输入：preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出：[3,9,20,null,null,15,7]
 * 
 * 【解题思路】
 * 核心思想：利用前序遍历确定根节点，中序遍历划分左右子树
 * 
 * 算法流程：
 * 1. 如果 preorder 或 inorder 为空，返回 null
 * 2. 取 preorder[0] 作为根节点（前序遍历的第一个元素是根）
 * 3. 在 inorder 中找到根节点的位置 mid
 * 4. 根据 mid 划分左右子树：
 *    - 左子树：inorder[0...mid-1]，preorder[1...mid]
 *    - 右子树：inorder[mid+1...end]，preorder[mid+1...end]
 * 5. 递归构建左右子树
 * 6. 返回根节点
 * 
 * 【执行示例】
 * preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 
 * 第1层：root=3, 在inorder中找到mid=1
 *        左子树：leftPre=[9], leftIn=[9]
 *        右子树：rightPre=[20,15,7], rightIn=[15,20,7]
 * 
 * 第2层左：root=9, mid=0 → 无左右子树
 * 第2层右：root=20, mid=1
 *         左子树：leftPre=[15], leftIn=[15]
 *         右子树：rightPre=[7], rightIn=[7]
 * 
 * 结果：    3
 *          / \
 *         9  20
 *           /  \
 *          15   7 ✓
 * 
 * 【时间复杂度】O(n²) - 每层需要遍历inorder找根节点位置，共n层
 * 【空间复杂度】O(n) - 每次递归创建新数组
 * 
 * 【关键点】
 * - 第7行：边界条件检查
 * - 第9行：前序遍历第一个元素是根节点
 * - 第11-14行：在中序遍历中查找根节点位置
 * - 第15-17行：构建左子树（注意preorder从1开始，长度为mid）
 * - 第19-21行：构建右子树（preorder从mid+1开始到末尾）
 */
public class LC_105_buildTree_1 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null||preorder.length==0||inorder==null||inorder.length==0)
            return null;
        TreeNode root=new TreeNode(preorder[0]);
        int mid=0;
        for (int i = 0; i < inorder.length; i++) {
            if(inorder[i]==root.val)
                mid=i;
        }
        int[] leftPre = Arrays.copyOfRange(preorder,1,mid+1)  ;
        int[] leftIn = Arrays.copyOfRange(inorder,0,mid)  ;
        root.left = buildTree(leftPre,leftIn);

        int[] rightPre = Arrays.copyOfRange(preorder,mid+1,preorder.length);
        int[] rightIn = Arrays.copyOfRange(inorder,mid+1,inorder.length);
        root.right = buildTree(rightPre,rightIn);
        return root;
    }
}
