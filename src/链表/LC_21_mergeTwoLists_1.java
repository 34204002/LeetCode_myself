package 链表;

/**
 * LeetCode 21. 合并两个有序链表
 * 
 * 【题目描述】
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 
 * 【示例】
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 
 * 【解题思路】
 * 核心思想：双指针归并，类似归并排序的 merge 操作
 * 
 * 算法流程：
 * 1. 创建虚拟头节点 start，简化边界处理
 * 2. 用 now 指针追踪新链表的尾部
 * 3. 比较 list1 和 list2 当前节点的值：
 *    - 较小的节点接入新链表
 *    - 对应链表指针前进一步
 * 4. 当一个链表遍历完后，将另一个链表剩余部分直接接入
 * 
 * 【执行示例】l1 = 1->2->4, l2 = 1->3->4
 * 
 * 初始：start=0, now=0
 * 
 * 第1轮：1==1, 选 l2 → now.next=1(l2), now=1, l2=3
 * 第2轮：1<3, 选 l1 → now.next=1(l1), now=1, l1=2
 * 第3轮：2<3, 选 l1 → now.next=2, now=2, l1=4
 * 第4轮：4>3, 选 l2 → now.next=3, now=3, l2=4
 * 第5轮：4==4, 选 l2 → now.next=4, now=4, l2=null
 * l2==null → now.next=l1(4)
 * 
 * 结果：0->1->1->2->3->4->4，返回 start.next ✓
 * 
 * 【时间复杂度】O(m+n) - 遍历两个链表各一次
 * 【空间复杂度】O(1) - 只使用常数额外空间（不计新链表）
 * 
 * 【关键点】
 * - 第5行：创建虚拟头节点，简化边界处理
 * - 第7-10行：处理空链表的特殊情况
 * - 第11-28行：主循环，归并两个链表
 *   · 第12-15行：list1 较小，接入 list1
 *   · 第16-19行：list2 较小或相等，接入 list2
 *   · 第21-27行：检查是否有一个链表已遍历完
 * - 第29行：返回 start.next（跳过虚拟头节点）
 */
public class LC_21_mergeTwoLists_1 {
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode start=new ListNode(0);  // 虚拟头节点
        ListNode now=start;              // 追踪新链表尾部
        if(list1==null)
            return list2;
        if(list2==null)
            return list1;
        while (true) {
            if(list1.val< list2.val){
                now.next=list1;     // 接入 list1
                now=list1;
                list1= list1.next;
            }else{
                now.next=list2;     // 接入 list2
                now=list2;
                list2= list2.next;
            }
            // 检查是否有一个链表已遍历完
            if(list1==null){
                now.next=list2;     // 接入 list2 剩余部分
                break;
            }else if(list2==null){
                now.next=list1;     // 接入 list1 剩余部分
                break;
            }
        }
        return start.next;  // 跳过虚拟头节点
    }
}
