package 链表;

/**
 * 带随机指针的链表节点定义（用于 LC_138）
 * 
 * 【结构说明】
 * - val：节点存储的整数值
 * - next：指向下一个节点的指针
 * - random：随机指针，可以指向链表中的任意节点或 null
 */
public class Node {
    public int val;
    public Node next;
    public Node random;  // 随机指针

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}