package 二叉树;

/**
 * 二叉树节点定义
 * 
 * 【结构说明】
 * - val：节点存储的整数值
 * - left：指向左子节点的指针
 * - right：指向右子节点的指针
 * 
 * 【构造函数】
 * - TreeNode()：创建空节点
 * - TreeNode(int val)：创建值为 val 的节点，左右子节点为 null
 * - TreeNode(int val, TreeNode left, TreeNode right)：创建完整节点
 * 
 * 【使用示例】
 * TreeNode root = new TreeNode(1);
 * root.left = new TreeNode(2);
 * root.right = new TreeNode(3);
 *     1
 *    / \
 *   2   3
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
