package 链表;

import java.util.Stack;

/**
 * LeetCode 25. K 个一组翻转链表 - 解法一：栈辅助分组翻转
 *
 * 【题目描述】
 * 给你链表的头节点 head，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，
 * 那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 【示例】
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 * 【解题思路】
 * 核心思想：每 k 个元素压入栈，利用栈的 LIFO 特性实现翻转，再串联各组
 *
 * 将链表按 k 个一组切分，每组用栈翻转，各组之间串联：
 *   - 维护当前组的前驱 befStart 和后继 aftEnd
 *   - 每遍历 k 个节点，将它们压入栈
 *   - 若最后一组不足 k 个，保持原序（flag=false 退出）
 *   - 弹出栈中元素，重新链接（栈弹出顺序即为翻转后的顺序）
 *
 * 算法流程：
 * 1. 创建虚拟头节点 re（处理边界），befStart 指向已翻转部分的尾部
 * 2. 外层循环，每次处理一组 k 个节点：
 *    a. start 指向当前组的头
 *    b. 遍历 k 次，将节点依次压入栈，end 指向组内最后一个节点
 *       - 若遍历过程中遇到 null（不够 k 个），flag=false 退出
 *    c. 记录 aftEnd = 下一组的头（p 已移动到下一组起点）
 *    d. 弹出栈中 k 个节点，重新链接：
 *       - 前 k-1 个节点：每个节点的 next 指向下一个弹出的节点
 *       - 第 k 个节点（最后弹出的）：next 指向 aftEnd
 *    e. 将前一组的尾部 befStart.next 指向翻转后的头（end）
 *    f. 更新 befStart 为 start（当前组的原始头部变为尾部）
 * 3. 返回 re.next
 *
 * 【执行示例】head = 1→2→3→4→5, k = 2
 *
 * 初始化：re=0, befStart=0, p=1
 *
 * 第1组：
 *   start=1, 压入栈：1→2
 *   栈：[1,2]（底→顶）
 *   aftEnd=3
 *   弹出：2.next=1, 1.next=3
 *   befStart.next=2, befStart=1
 *   中间状态：0→2→1→3→4→5
 *
 * 第2组：
 *   start=3, 压入栈：3→4
 *   栈：[3,4]
 *   aftEnd=5
 *   弹出：4.next=3, 3.next=5
 *   befStart.next=4, befStart=3
 *   中间状态：0→2→1→4→3→5
 *
 * 第3组：
 *   start=5, 压入栈：5（k=2，但只剩一个）
 *   p=null, flag=false → 退出
 *
 * return re.next = 2→1→4→3→5 ✓
 *
 * 【时间复杂度】O(n) - 每个节点入栈一次、出栈一次
 * 【空间复杂度】O(k) - 栈中最多同时存放 k 个节点
 *
 * 【关键点】
 * - 第14-15行：虚拟头节点 befStart/re 简化边界处理
 * - 第17-25行：每 k 个一组压入栈，不足 k 个时 flag=false 退出
 * - 第28-33行：出栈并重新链接，第一个弹出的是翻转后的新头
 * - 第34-35行：befStart.next 连接本组翻转后的头部，更新 befStart
 *
 * 【注意】
 * - 第18行：p==null 时说明不足 k 个，flag=false 后 break 跳出 for 循环
 * - 第26行：!flag 后 break 跳出 while 循环，不再处理后面的组
 * - 第30-31行：前 k-1 个弹出的节点 next 指向下一个弹出的节点
 * - 第32-33行：最后一个弹出的节点 next 指向 aftEnd（下一组头部）
 * - 第34行：befStart.next 必须指向 end 而非 start，因为 end 是翻转后的新头
 * - 第35行：befStart 更新为 start（原始头部在翻转后变为尾部）
 */
public class LC_25_reverseKGroup_1 {
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start=head;
        ListNode end=head;
        ListNode p=head;
        ListNode befStart=new ListNode(0);
        ListNode re=befStart;
        ListNode aftEnd;
        boolean flag=true;
        Stack<ListNode> stack=new Stack<>();
        while (true){
            start=p;
            for (int i = 0; i < k; i++) {
                if(p==null) {
                    flag = false;
                    break;
                }
                stack.add(p);
                end=p;
                p = p.next;
            }
            if(!flag)
                break;
            aftEnd = p;  //  在翻转前保存下一个组的起始点
            for (int i = 0; i < k; i++) {
                if(i!=k-1)
                    stack.pop().next=stack.peek();
                else
                    stack.pop().next = aftEnd;
            }
            befStart.next=end;
            befStart=start;
        }
        return re.next;
    }
}
