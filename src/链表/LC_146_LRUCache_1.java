package 链表;

import java.util.HashMap;

/**
 * LeetCode 146. LRU 缓存
 * 
 * 【题目描述】
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存约束的数据结构。
 * 实现 LRUCache 类：
 * - LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * - int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1
 * - void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value；
 *   如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，
 *   则应该逐出最久未使用的关键字。
 * 
 * 【示例】
 * 输入：["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 *      [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出：[null, null, null, 1, null, -1, null, -1, 3, 4]
 * 
 * 【解题思路】
 * 核心思想：HashMap + 双向链表
 * 
 * - HashMap：提供 O(1) 的查找能力
 * - 双向链表：维护访问顺序，头部为最近使用，尾部为最久未使用
 * - 伪头部 dummy 和伪尾部 dummyTail：简化边界处理
 * 
 * 算法流程：
 * get(key)：
 * 1. 如果 key 不存在，返回 -1
 * 2. 如果存在，将对应节点移到链表头部（标记为最近使用）
 * 3. 返回节点值
 * 
 * put(key, value)：
 * 1. 如果 key 已存在，更新值并移到头部
 * 2. 如果 key 不存在：
 *    - 如果容量已满，删除尾部节点（最久未使用）
 *    - 创建新节点，加入 HashMap 和链表头部
 * 
 * 【辅助方法】
 * - removeNode(node)：从链表中移除节点
 * - addToHead(node)：将节点添加到链表头部
 * 
 * 【时间复杂度】O(1) - get 和 put 都是常数时间
 * 【空间复杂度】O(capacity) - HashMap 和链表最多存储 capacity 个节点
 * 
 * 【关键点】
 * - 第6-18行：ListNodeD 内部类，双向链表节点
 * - 第20-24行：初始化伪头部、伪尾部和 HashMap
 * - 第30-31行：连接伪头部和伪尾部
 * - 第35-38行：removeNode 通用逻辑
 * - 第41-46行：addToHead 通用逻辑
 * - 第48-57行：get 方法，查找并移到头部
 * - 第59-80行：put 方法，插入或更新
 */
public class LC_146_LRUCache_1 {
    class ListNodeD {
        int key;
        int val;
        ListNodeD next;
        ListNodeD last; // 前驱指针

        ListNodeD(int x, int y) {
            key = x;
            val = y;
            next = null;
            last = null;
        }
    }
    
    private ListNodeD dummy = new ListNodeD(-1, 0);      // 伪头部
    private ListNodeD dummyTail = new ListNodeD(-1, 0);  // 伪尾部
    private HashMap<Integer, ListNodeD> map = new HashMap<>();
    private int capacity;

    public LC_146_LRUCache_1(int capacity) {
        this.capacity = capacity;
        // 初始化双向链表，把伪头和伪尾连起来
        dummy.next = dummyTail;
        dummyTail.last = dummy;
    }

    // 从链表中移除节点
    private void removeNode(ListNodeD node) {
        node.last.next = node.next;
        node.next.last = node.last;
    }

    // 将节点添加到链表头部
    private void addToHead(ListNodeD node) {
        node.last = dummy;
        node.next = dummy.next;
        dummy.next.last = node;
        dummy.next = node;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        ListNodeD node = map.get(key);
        // 移到头部（标记为最近使用）
        removeNode(node);
        addToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNodeD node = map.get(key);
            node.val = value; // 更新值
            // 移到头部
            removeNode(node);
            addToHead(node);
            return;
        }

        // 如果容量满了，删除最久未使用的节点（伪尾部前面的节点）
        if (map.size() == capacity) {
            ListNodeD lastNode = dummyTail.last;
            map.remove(lastNode.key);
            removeNode(lastNode);
        }

        // 添加新节点到头部
        ListNodeD newNode = new ListNodeD(key, value);
        map.put(key, newNode);
        addToHead(newNode);
    }
}
