package 链表;

/**
 * LeetCode 148. 排序链表
 * 
 * 【题目描述】
 * 给你链表的头结点 head ，请将其按升序排列并返回排序后的链表。
 * 要求时间复杂度 O(n log n)，空间复杂度 O(1)。
 * 
 * 【示例】
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 
 * 【解题思路】
 * 核心思想：归并排序（分治法）
 * 
 * 算法流程：
 * 1. 找到链表中点，将链表分成两半
 * 2. 递归排序左半部分和右半部分
 * 3. 合并两个有序链表
 * 
 * 【执行示例】4 -> 2 -> 1 -> 3
 * 
 * 第1层递归：
 * - 找中点：middle=2，分割成 [4,2] 和 [1,3]
 * - 递归排序 [4,2]：
 *   · 找中点：middle=4，分割成 [4] 和 [2]
 *   · 合并 [4] 和 [2] → [2,4]
 * - 递归排序 [1,3]：
 *   · 找中点：middle=1，分割成 [1] 和 [3]
 *   · 合并 [1] 和 [3] → [1,3]
 * - 合并 [2,4] 和 [1,3] → [1,2,3,4]
 * 
 * 结果：1 -> 2 -> 3 -> 4 ✓
 * 
 * 【时间复杂度】O(n log n)
 * - 每次分割 O(n)，共 log n 层
 * 
 * 【空间复杂度】O(log n) - 递归栈深度
 * 
 * 【关键点】
 * - 第4-17行：getMiddle 方法，快慢指针找中点
 * - 第18-30行：sortList 方法，归并排序主逻辑
 *   · 第22-24行：找到中点并分割链表
 *   · 第26-27行：递归排序左右两部分
 *   · 第29行：合并两个有序链表
 * - 第32-51行：mergeS 方法，合并两个有序链表
 */
public class LC_148_sortList_1 {
    // 快慢指针找中点
    public static ListNode getMiddle(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;
        while (true){
            if(fast.next==null){
                return slow;
            }else if(fast.next.next==null){
                return slow;
            }
            slow=slow.next;
            fast=fast.next.next;
        }
    }
    
    // 归并排序
    public static ListNode sortList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode p=getMiddle(head).next;  // 右半部分起点
        ListNode middle=getMiddle(head);  // 左半部分终点
        middle.next=null;  // 断开链表
        middle=p;
        head=sortList(head);      // 递归排序左半部分
        middle=sortList(middle);  // 递归排序右半部分
        return mergeS(head,middle);  // 合并
    }

    // 合并两个有序链表
    private static ListNode mergeS(ListNode l1, ListNode l2) {
        ListNode h=new ListNode(0);  // 虚拟头节点
        ListNode p=h;
        while (l1!=null&&l2!=null){
            if(l1.val>l2.val){
                p.next=l2;
                l2=l2.next;
            }else {
                p.next=l1;
                l1=l1.next;
            }
            p=p.next;
        }
        if(l1!=null){
            p.next=l1;
        }else {
            p.next=l2;
        }
        return h.next;
    }

}
