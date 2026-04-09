package 链表;

/**
 * LeetCode 160. 相交链表
 * 
 * 【题目描述】
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表不存在相交节点，返回 null 。
 * 
 * 【示例】
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5]
 * 输出：Intersected at '8'
 * 解释：两个链表在值为 8 的节点处相交
 * 
 * 【解题思路】
 * 核心思想：对齐链表长度后同步遍历
 * 
 * 关键观察：
 * - 如果两个链表相交，从交点到末尾的部分完全相同
 * - 如果长度不同，需要先让长链表先走差值步，使两者对齐
 * 
 * 算法流程：
 * 1. 分别计算两个链表的长度 lengthA 和 lengthB
 * 2. 让较长的链表先走 |lengthA - lengthB| 步
 * 3. 两个链表同步前进，第一个相同的节点即为交点
 * 
 * 【执行示例】
 * A: 4 -> 1 -> 8 -> 4 -> 5
 *              ↑
 * B: 5 -> 6 -> 1 -> 8 -> 4 -> 5
 * 
 * lengthA = 4, lengthB = 5
 * B 比 A 长 1，B 先走 1 步：B 指向 6
 * 
 * 同步遍历：
 * A=4, B=6 → 不同
 * A=1, B=1 → 不同（值相同但不是同一节点）
 * A=8, B=8 → 相同！返回节点 8 ✓
 * 
 * 【时间复杂度】O(m+n)
 * - 计算长度 O(m+n)
 * - 同步遍历 O(min(m,n))
 * 
 * 【空间复杂度】O(1) - 只使用常数指针
 * 
 * 【关键点】
 * - 第5-16行：计算两个链表的长度
 * - 第17-18行：恢复 headA 和 headB 到链表头部
 * - 第19-28行：长度相等时直接同步遍历
 * - 第29-40行：A 较长时，A 先走差值步
 * - 第41-53行：B 较长时，B 先走差值步
 */
public class LC_160_getIntersectionNode_1 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lengthA = 0;
        int lengthB = 0;
        ListNode A=headA;
        ListNode B=headB;
        // 计算链表 A 的长度
        while (headA.next != null) {
            lengthA++;
            headA = headA.next;
        }
        // 计算链表 B 的长度
        while (headB.next != null) {
            lengthB++;
            headB = headB.next;
        }
        headA=A;  // 恢复到链表头部
        headB=B;
        // 长度相等，直接同步遍历
        if(lengthA==lengthB){
            while (true) {
                if(headA==headB)
                    return headA;
                if(headA.next==null)
                    return null;
                headA = headA.next;
                headB = headB.next;
            }
        }
        // A 较长，A 先走差值步
        else if (lengthA > lengthB) {
            for (int i = 0; i < lengthA - lengthB; i++) {
                headA = headA.next;
            }
            while (true) {
                if(headA!=null&&headA==headB)
                    return headA;
                if(headA.next==null)
                    return null;
                headA = headA.next;
                headB = headB.next;
            }
        }
        // B 较长，B 先走差值步
        else {
            for (int i = 0; i < lengthB - lengthA; i++) {
                headB = headB.next;
            }
            while (true) {
                if(headA!=null&&headA==headB)
                    return headA;
                if(headA.next==null)
                    return null;
                headA = headA.next;
                headB = headB.next;
            }
        }
    }
}
