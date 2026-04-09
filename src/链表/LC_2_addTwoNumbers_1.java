package 链表;

/**
 * LeetCode 2. 两数相加
 * 
 * 【题目描述】
 * 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，
 * 并且每个节点只能存储一位数字。请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 
 * 【示例】
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807
 * 
 * 【解题思路】
 * 核心思想：直接在 l1 上修改，动态补零处理不等长链表
 * 
 * 算法流程：
 * 1. 逐位相加，处理进位
 * 2. 如果一个链表较短，动态补充值为 0 的节点
 * 3. 如果最后还有进位，继续补充节点
 * 
 * 【执行示例】l1 = 2->4->3, l2 = 5->6->4
 * 
 * 第1轮：sum=2+5+0=7, l1.val=7, addNum=0
 * 第2轮：sum=4+6+0=10, l1.val=0, addNum=1
 * 第3轮：sum=3+4+1=8, l1.val=8, addNum=0
 * l1.next==null && l2.next==null && addNum==0 → break
 * 结果：7->0->8 ✓ (342 + 465 = 807)
 * 
 * 【进位示例】l1 = 9->9, l2 = 1
 * 
 * 第1轮：sum=9+1+0=10, l1.val=0, addNum=1
 * 第2轮：l2.next==null, 补零 → l2.next=0
 *       sum=9+0+1=10, l1.val=0, addNum=1
 * 第3轮：l1.next==null && l2.next==null
 *       addNum==1 → 补零节点
 *       sum=0+0+1=1, l1.val=1, addNum=0
 *       break
 * 结果：0->0->1 ✓ (99 + 1 = 100)
 * 
 * 【时间复杂度】O(max(m,n)) - m 和 n 分别为两个链表的长度
 * 【空间复杂度】O(1) - 直接在 l1 上修改，不计输出链表
 * 
 * 【关键点】
 * - 第5行：addNum 记录进位
 * - 第6行：start 记录结果链表头部（即 l1）
 * - 第8-15行：逐位相加，处理进位
 * - 第16-25行：动态补零处理不等长链表和最终进位
 *   · 第16-17行：l1 较短，给 l1 补零
 *   · 第18-19行：l2 较短，给 l2 补零
 *   · 第20-24行：两个链表都到末尾，如有进位则补零继续
 */
public class LC_2_addTwoNumbers_1 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int addNum=0;        // 进位
        ListNode start=l1;   // 记录结果链表头部
        while (true){
            int sum=l1.val+l2.val+addNum;
            if(sum>=10){
                addNum=1;
                sum%=10;
            }else {
                addNum=0;
            }
            l1.val=sum;      // 直接在 l1 上修改
            // 处理不等长链表：动态补零
            if(l1.next==null&&l2.next!=null){
                l1.next=new ListNode(0);
            }else if(l2.next==null&&l1.next!=null){
                l2.next=new ListNode(0);
            }else if (l1.next==null&&l2.next==null){
                if(addNum==0)
                    break;
                // 还有进位，补充节点
                l1.next=new ListNode(0);
                l2.next=new ListNode(0);
            }
            l1=l1.next;
            l2=l2.next;
        }
        return start;
    }
}
