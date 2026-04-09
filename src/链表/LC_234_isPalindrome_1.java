package 链表;

/**
 * LeetCode 234. 回文链表
 * 
 * 【题目描述】
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。
 * 如果是，返回 true ；否则，返回 false 。
 * 
 * 【示例】
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 
 * 输入：head = [1,2]
 * 输出：false
 * 
 * 【解题思路】
 * 核心思想：找到中点，反转后半部分，双指针比较
 * 
 * 算法流程：
 * 1. 遍历链表，计算长度并找到尾节点
 * 2. 找到中间节点（第 length/2+1 个节点）
 * 3. 反转从中间节点开始的后半部分
 * 4. 用 start 和 end 双指针从两端向中间比较
 * 
 * 【执行示例】1 -> 2 -> 2 -> 1
 * 
 * 第1步：计算长度 length=4, end 指向最后一个节点 1
 * 
 * 第2步：middleLen = 4/2+1 = 3, mid 指向第 3 个节点（值为 2）
 * 
 * 第3步：反转后半部分
 * 原链表：1 -> 2 -> 2 -> 1
 * 反转后：1 -> 2 -> 1 -> 2
 * start 指向 1, end 指向 1（原来的尾节点，现在是后半部分的头）
 * 
 * 第4步：双指针比较
 * i=0: start.val=1, end.val=1 → 相同
 * i=1: start.val=2, end.val=2 → 相同
 * 
 * 结果：true ✓
 * 
 * 【时间复杂度】O(n)
 * - 计算长度 O(n)
 * - 找中点 O(n)
 * - 反转后半部分 O(n/2)
 * - 比较 O(n/2)
 * 
 * 【空间复杂度】O(1) - 原地操作
 * 
 * 【关键点】
 * - 第4-18行：reverseList 方法，反转链表
 * - 第20-28行：计算链表长度并找到尾节点
 * - 第30-34行：找到中间节点
 * - 第35行：反转后半部分链表
 * - 第36-41行：双指针从两端向中间比较
 */
public class LC_234_isPalindrome_1 {
    // 反转链表
    public static ListNode reverseList(ListNode head) {
        if(head==null||head.next==null)
            return head;
        ListNode p;
        ListNode p1=null;
        ListNode p2=head;
        while (p2.next!=null){
            p=p2;
            p2=p2.next;
            p.next=p1;
            p1=p;
        }
        p2.next=p1;
        return p2;
    }
    
    // 判断是否为回文链表
    public static boolean isPalindrome(ListNode head) {
        int length=0;
        ListNode start=head;
        ListNode end=null;
        // 计算长度并找到尾节点
        while (head!=null){
            length++;
            if(head.next==null)
                end=head;
            head=head.next;
        }
        head=start;  // 恢复到链表头部
        int middleLen=length/2+1;  // 中间位置
        ListNode mid=start;
        // 找到中间节点
        for (int i = 0; i < middleLen-1; i++) {
            mid=mid.next;
        }
        reverseList(mid);  // 反转后半部分
        // 双指针从两端向中间比较
        for (int i = 0; i <= (length - 1) / 2; i++) {
            if(start.val!=end.val)
                return false;
            start=start.next;
            end=end.next;
        }
        return true;
    }

}
