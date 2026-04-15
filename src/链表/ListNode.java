package 链表;

/**
 * 链表节点定义
 * 
 * 【结构说明】
 * - val：节点存储的整数值
 * - next：指向下一个节点的指针
 * 
 * 【构造函数】
 * - ListNode(int x)：创建值为 x 的节点，next 默认为 null
 * 
 * 【使用示例】
 * ListNode node1 = new ListNode(1);
 * ListNode node2 = new ListNode(2);
 * node1.next = node2;  // 1 -> 2
 */
public class ListNode {
    public int val;        // 节点值
    public ListNode next;  // 下一个节点指针

    public ListNode(int x) {
        val = x;
        next = null;
    }
}

