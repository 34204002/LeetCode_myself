package 链表;

/**
 * LeetCode 19. 删除链表的倒数第 N 个结点
 * 
 * 【题目描述】
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 
 * 【示例】
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 
 * 【解题思路】
 * 核心思想：快慢指针，保持距离 n
 * 
 * 算法流程：
 * 1. fast 先走 n-1 步
 * 2. fast 和 dNode 同时前进，直到 fast 到达最后一个节点
 * 3. 此时 dNode 指向倒数第 n 个节点
 * 4. aheadDNode 记录 dNode 的前一个节点
 * 5. 删除 dNode 节点
 * 
 * 【执行示例】1 -> 2 -> 3 -> 4 -> 5, n = 2
 * 
 * 初始：fast=1, dNode=1, aheadDNode=1
 * fast 先走 1 步：fast=2
 * 
 * 同步前进：
 * fast=2, dNode=1
 * fast=3, dNode=2, aheadDNode=1（因为 fast.next.next==null）
 * fast=4, dNode=3
 * fast=5, dNode=4（循环结束）
 * 
 * 删除：aheadDNode.next = dNode.next → 3.next = 5
 * 结果：1 -> 2 -> 3 -> 5 ✓
 * 
 * 【时间复杂度】O(n) - 一次遍历
 * 【空间复杂度】O(1) - 只使用常数指针
 * 
 * 【关键点】
 * - 第5-6行：单节点链表直接返回 null
 * - 第11-13行：fast 先走 n-1 步
 * - 第14-19行：fast 和 dNode 同步前进
 * - 第15-16行：记录 dNode 的前一个节点
 * - 第20-24行：删除 dNode 节点
 *   · 第20-21行：正常情况，跳过 dNode
 *   · 第22-23行：删除的是头节点，head 前移
 */
public class LC_19_removeNthFromEnd_1 {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next==null)
            return null;
        ListNode dNode = head;
        ListNode aheadDNode = head;
        ListNode fast = head;
        int count = 0;
        // fast 先走 n-1 步
        for (int i = 0; i < n - 1; i++) {
            fast = fast.next;
        }
        // fast 和 dNode 同步前进
        while (fast.next != null) {
            if(fast.next.next==null)
                aheadDNode=dNode;  // 记录 dNode 的前一个节点
            fast = fast.next;
            dNode = dNode.next;
        }
        // 删除 dNode 节点
        if(aheadDNode.next==dNode){
            aheadDNode.next= dNode.next;
        }else {
            head=head.next;  // 删除的是头节点
        }
        return head;
    }
}
