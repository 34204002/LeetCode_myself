package 链表;

import java.util.HashMap;

/**
 * LeetCode 138. 复制带随机指针的链表
 * 
 * 【题目描述】
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的深拷贝。深拷贝应该正好由 n 个全新节点组成，其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
 * 
 * 【示例】
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 
 * 【解题思路】
 * 核心思想：HashMap 建立原节点与新节点的映射关系
 * 
 * 算法流程：
 * 1. 第一次遍历：创建所有新节点，建立 {原节点: 新节点} 的映射
 * 2. 第二次遍历：根据映射关系设置新节点的 next 和 random 指针
 *    - hashMap.get(p).next = hashMap.get(p.next)
 *    - hashMap.get(p).random = hashMap.get(p.random)
 * 3. 返回 hashMap.get(head)，即新链表的头节点
 * 
 * 【执行示例】原链表：7 -> 13 -> 11 -> 10 -> 1
 *                    ↓     ↓          ↓
 *                   null   7         1
 * 
 * 第1次遍历：创建新节点并建立映射
 * hashMap = {原7:新7, 原13:新13, 原11:新11, 原10:新10, 原1:新1}
 * 
 * 第2次遍历：设置指针
 * 新7.next = hashMap.get(原7.next) = 新13
 * 新7.random = hashMap.get(原7.random) = hashMap.get(null) = null
 * 新13.next = 新11, 新13.random = 新7
 * ...
 * 
 * 结果：新链表结构与原链表完全相同 ✓
 * 
 * 【时间复杂度】O(n) - 两次遍历
 * 【空间复杂度】O(n) - HashMap 存储 n 个映射
 * 
 * 【关键点】
 * - 第7-13行：第一次遍历，创建新节点并建立映射
 * - 第14-20行：第二次遍历，设置新节点的 next 和 random 指针
 * - 第17-18行：只有当 p.random != null 时才设置 random 指针
 * - 第21行：返回新链表的头节点
 */
public class LC_138_copyRandomList_1 {
    public static Node copyRandomList(Node head) {
        Node p=head;
        HashMap<Node,Node> hashMap=new HashMap<>();
        // 第一次遍历：创建新节点并建立映射
        while (p!=null){
            Node p2=new Node(p.val);
            hashMap.put(p,p2);
            p=p.next;
        }
        p=head;
        // 第二次遍历：设置新节点的 next 和 random 指针
        while (p!=null){
            hashMap.get(p).next=hashMap.get(p.next);
            if(p.random!=null)
                hashMap.get(p).random=hashMap.get(p.random);
            p=p.next;
        }
        return hashMap.get(head);
    }
}
