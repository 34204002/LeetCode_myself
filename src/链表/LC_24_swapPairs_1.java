package 链表;

/**
 * LeetCode 24. 两两交换链表中的节点
 * 
 * 【题目描述】
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * 
 * 【示例】
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 
 * 【解题思路】
 * 核心思想：四指针操作，逐对交换
 * 
 * 指针说明：
 * - p1：前一对的尾节点（或虚拟头节点）
 * - p2：当前对的第一个节点
 * - p3：当前对的第二个节点
 * - p4：下一对的第一个节点
 * 
 * 交换过程：
 * 1. p1.next = p3（前一对指向当前对的第二个节点）
 * 2. p3.next = p2（第二个节点指向第一个节点）
 * 3. p2.next = p4（第一个节点指向下一对）
 * 4. 移动指针到下一对
 * 
 * 【执行示例】1 -> 2 -> 3 -> 4
 * 
 * 初始：p1=0(虚拟), p2=1, p3=2, p4=3
 * 
 * 第1轮交换：
 * p1.next = 2 → 0 -> 2
 * p3.next = 1 → 2 -> 1
 * p2.next = 3 → 1 -> 3
 * 结果：0 -> 2 -> 1 -> 3 -> 4
 * 移动：p1=1, p2=3, p3=4, p4=null
 * 
 * 第2轮交换：
 * p1.next = 4 → 1 -> 4
 * p3.next = 3 → 4 -> 3
 * p2.next = null → 3 -> null
 * 结果：0 -> 2 -> 1 -> 4 -> 3 -> null
 * 
 * 返回 start.next：2 -> 1 -> 4 -> 3 ✓
 * 
 * 【时间复杂度】O(n) - 遍历一次链表
 * 【空间复杂度】O(1) - 只使用常数指针
 * 
 * 【关键点】
 * - 第5-6行：空链表或单节点直接返回
 * - 第7-11行：初始化四个指针
 * - 第14-17行：交换当前对的两个节点
 * - 第19-20行：检查是否还有下一对
 * - 第21-24行：移动指针到下一对
 */
public class LC_24_swapPairs_1 {
    public static ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null)
            return head;
        ListNode p1=new ListNode(0);  // 虚拟头节点
        ListNode start=p1;
        ListNode p2=head;
        ListNode p3=head.next;
        ListNode p4;
        while (true){
            // 交换 p2 和 p3
            p1.next = p3;
            p4 = p3.next;
            p3.next = p2;
            p2.next = p4;

            // 检查是否还有下一对
            if(p2.next==null||p2.next.next==null)
                break;
            // 移动指针到下一对
            p1=p2;
            p2=p1.next;
            p3=p2.next;
            p4=p3.next;
        }
        return start.next;
    }
}
