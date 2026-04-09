package 链表;

/**
 * LeetCode 23. 合并 K 个升序链表
 * 
 * 【题目描述】
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 
 * 【示例】
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 
 * 【解题思路】
 * 核心思想：分治法，两两合并
 * 
 * 算法流程：
 * 1. 将 k 个链表分成两半
 * 2. 递归合并左半部分和右半部分
 * 3. 合并两个有序链表
 * 
 * 【执行示例】lists = [[1,4,5],[1,3,4],[2,6]]
 * 
 * 第1层递归：
 * - 左半部分：[[1,4,5],[1,3,4]]
 * - 右半部分：[[2,6]]
 * 
 * 左半部分递归：
 * - 合并 [1,4,5] 和 [1,3,4] → [1,1,3,4,4,5]
 * 
 * 右半部分递归：
 * - 只有一个链表 [2,6]，直接返回
 * 
 * 最终合并：
 * - 合并 [1,1,3,4,4,5] 和 [2,6] → [1,1,2,3,4,4,5,6]
 * 
 * 结果：1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6 ✓
 * 
 * 【时间复杂度】O(n log k)
 * - n 为所有节点总数，k 为链表数量
 * - 每层合并 O(n)，共 log k 层
 * 
 * 【空间复杂度】O(log k) - 递归栈深度
 * 
 * 【关键点】
 * - 第4-23行：mergeS 方法，合并两个有序链表
 * - 第24-26行：mergeKLists 方法，入口函数
 * - 第27-37行：mergeA 方法，分治合并
 *   · 第28-29行：计算中点
 *   · 第30-33行：递归终止条件
 *   · 第34-36行：递归合并左右两部分
 */
public class LC_23_mergeS_1 {
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
    
    // 合并 K 个有序链表
    public static ListNode mergeKLists(ListNode[] lists) {
        return mergeA(lists,0, lists.length-1);
    }
    
    // 分治合并
    public static ListNode mergeA(ListNode[] lists,int l,int r) {
        int len=r-l+1;
        int mid=l+(len-1)/2;
        if(len==0)
            return null;
        if(len==1)
            return lists[l];
        ListNode left=mergeA(lists,l,mid);      // 递归合并左半部分
        ListNode right=mergeA(lists,mid+1,r);   // 递归合并右半部分
        return mergeS(left,right);              // 合并两个结果
    }
}
