package 链表;

/**
 * LeetCode 142. 环形链表 II
 * 
 * 【题目描述】
 * 给定一个链表的头节点 head ，返回链表开始入环的第一个节点。如果链表无环，则返回 null。
 * 
 * 【示例】
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 
 * 【解题思路】
 * 核心思想：快慢指针找到相遇点，然后双指针找入口
 * 
 * 数学推导：
 * - 设链表头到环入口距离为 a
 * - 环入口到相遇点距离为 b
 * - 相遇点到环入口距离为 c
 * - 环的长度 = b + c
 * 
 * - slow 走的距离：a + b
 * - fast 走的距离：a + b + k(b+c)，k 为 fast 绕环的圈数
 * - 因为 fast 速度是 slow 的 2 倍：2(a+b) = a + b + k(b+c)
 * - 化简得：a = (k-1)(b+c) + c
 * - 结论：从 head 和相遇点同时出发，两者会在环入口相遇
 * 
 * 算法流程：
 * 1. 快慢指针找到相遇点
 * 2. 将 head 和 slow 同时向前移动，每次 1 步
 * 3. 两者相遇的位置即为环入口
 * 
 * 【执行示例】链表：3 -> 2 -> 0 -> -4 -> 2(环)
 * 
 * 第1阶段：找相遇点
 * 初始：slow=3, fast=3
 * 第1步：slow=2, fast=0
 * 第2步：slow=0, fast=2
 * 第3步：slow=-4, fast=-4 → 相遇
 * 
 * 第2阶段：找环入口
 * head=3, slow=-4
 * 第1步：head=2, slow=2 → 相遇！返回节点 2 ✓
 * 
 * 【时间复杂度】O(n)
 * - 第1阶段：最多 O(n) 找到相遇点
 * - 第2阶段：最多 O(n) 找到入口
 * 
 * 【空间复杂度】O(1) - 只使用常数额外空间
 * 
 * 【关键点】
 * - 第7-10行：定义了辅助变量
 * - 第13-25行：快慢指针找相遇点
 * - 第19-22行：找到相遇点后，head 和 slow 同步前进找入口
 * - 第23行：返回环入口节点
 */
public class LC_142_detectCycle_1 {
    public static ListNode detectCycle(ListNode head) {
        if(head==null)
            return null;
        int countS=0;
        int countF=0;
        int cycleLen=0;
        int startLen=0;
        ListNode slow=head;
        ListNode fast=head;
        while (fast!=null&&fast.next!=null){
            slow=slow.next;
            countS++;
            fast=fast.next.next;
            countF+=2;
            if(slow==fast){
                // 找到相遇点，head 和 slow 同步前进找环入口
                while (head!=slow){
                    head=head.next;
                    slow=slow.next;
                }
                return slow;  // 返回环入口
            }
        }
        return null;  // 无环
    }
}
