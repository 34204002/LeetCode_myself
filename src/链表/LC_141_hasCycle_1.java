package 链表;

/**
 * LeetCode 141. 环形链表
 * 
 * 【题目描述】
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 
 * 【示例】
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 
 * 【解题思路】
 * 核心思想：快慢指针（Floyd 判圈算法）
 * 
 * 算法流程：
 * 1. 初始化 slow 和 fast 都指向 head
 * 2. slow 每次走 1 步，fast 每次走 2 步
 * 3. 如果链表有环，fast 最终会追上 slow（相遇）
 * 4. 如果 fast 遇到 null，说明无环
 * 
 * 【为什么快慢指针能检测环？】
 * - 如果无环：fast 会先到达链表末尾（null）
 * - 如果有环：
 *   · fast 进入环后，slow 也进入环
 *   · fast 在环内以相对速度 1 追赶 slow
 *   · 最多绕环一圈就能追上 slow
 * 
 * 【执行示例】有环链表：3 -> 2 -> 0 -> -4 -> 2(环)
 * 初始：slow=3, fast=3
 * 第1步：slow=2, fast=0
 * 第2步：slow=0, fast=2
 * 第3步：slow=-4, fast=-4 → 相遇，返回 true ✓
 * 
 * 【时间复杂度】O(n)
 * - 无环：fast 遍历完链表 O(n)
 * - 有环：slow 最多走 n 步，fast 最多走 2n 步
 * 
 * 【空间复杂度】O(1) - 只使用两个指针
 * 
 * 【关键点】
 * - 第5-6行：空链表特殊处理
 * - 第9行：循环条件检查 fast 和 fast.next 是否为 null
 * - 第10-11行：slow 走 1 步，fast 走 2 步
 * - 第12-14行：相遇则说明有环
 */
public class LC_141_hasCycle_1 {
    public static boolean hasCycle(ListNode head) {
        if(head==null)
            return false;
        ListNode slow=head;
        ListNode fast=head;
        while (fast!=null&&fast.next!=null){
            slow=slow.next;        // 慢指针走 1 步
            fast=fast.next.next;   // 快指针走 2 步
            if(slow==fast){
                return true;       // 相遇，有环
            }
        }
        return false;  // fast 遇到 null，无环
    }
}
