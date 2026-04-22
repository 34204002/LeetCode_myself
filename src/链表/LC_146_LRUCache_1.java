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
 * 输入：
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出：[null, null, null, 1, null, -1, null, -1, 3, 4]
 * 
 * 【解题思路】
 * 核心思想：哈希表 + 双向链表
 * 
 * 数据结构设计：
 * 1. HashMap<Integer, NodeD>：提供 O(1) 的查找能力
 *    - key：缓存的键
 *    - value：指向双向链表中对应节点的引用
 * 
 * 2. 双向链表：维护访问顺序，支持 O(1) 的插入和删除
 *    - 链表头部：最近使用的节点（most recently used）
 *    - 链表尾部：最久未使用的节点（least recently used）
 *    - dummy 哨兵节点：简化边界处理，形成循环双向链表
 * 
 * 算法流程：
 * - get(key)：
 *   1. 如果 key 存在，获取值并将节点移到链表头部（标记为最近使用）
 *   2. 如果 key 不存在，返回 -1
 * 
 * - put(key, value)：
 *   1. 如果 key 已存在，更新值并将节点移到链表头部
 *   2. 如果 key 不存在：
 *      - 创建新节点，加入 HashMap 和链表头部
 *      - 如果超出容量，移除链表尾部节点（最久未使用）及其在 HashMap 中的记录
 * 
 * 【执行示例】capacity = 2
 * 
 * put(1, 1)：链表: [1]，HashMap: {1→Node(1,1)}
 * put(2, 2)：链表: [2, 1]，HashMap: {1→Node(1,1), 2→Node(2,2)}
 * get(1)：返回 1，链表: [1, 2]（1 移到头部）
 * put(3, 3)：超出容量，移除尾部节点 2
 *           链表: [3, 1]，HashMap: {1→Node(1,1), 3→Node(3,3)}
 * get(2)：返回 -1（已被逐出）
 * put(4, 4)：超出容量，移除尾部节点 1
 *           链表: [4, 3]，HashMap: {3→Node(3,3), 4→Node(4,4)}
 * get(1)：返回 -1（已被逐出）
 * get(3)：返回 3，链表: [3, 4]
 * get(4)：返回 4，链表: [4, 3]
 * 
 * 【时间复杂度】
 * - get: O(1) - HashMap 查找 + 双向链表移动
 * - put: O(1) - HashMap 操作 + 双向链表插入/删除
 * 
 * 【空间复杂度】O(capacity) - HashMap 和双向链表最多存储 capacity 个节点
 * 
 * 【关键点】
 * - 第21-23行：dummy 哨兵节点初始化为自循环，简化边界处理
 * - 第28-32行：get 操作中，找到节点后需移到链表头部（标记为最近使用）
 * - 第37-40行：put 操作中，key 已存在时更新值并移到头部
 * - 第42-43行：key 不存在时，先加入 HashMap，再插入链表头部
 * - 第44-48行：检查容量，超出时移除尾部节点（最久未使用）
 * - 第51-56行：remove 方法，从双向链表中移除节点
 * - 第57-62行：pushFront 方法，将节点插入到链表头部（dummy 之后）
 * 
 * 【为什么使用双向链表？】
 * - 需要快速删除任意节点（当节点被访问时需从原位置删除）
 * - 需要快速访问尾部节点（淘汰最久未使用的节点）
 * - 单向链表无法高效实现这些操作
 * 
 * 【为什么使用 dummy 哨兵节点？】
 * - 避免空指针判断，简化头尾节点的操作
 * - 形成循环结构，统一处理所有节点的插入和删除
 */
public class LC_146_LRUCache_1 {
    class NodeD {
        int key,value;
        NodeD last,next;
        public NodeD(int key, int val){
            this.key=key;
            this.value=val;
        }
    }
    HashMap<Integer, NodeD> hashMap;  // 提供 O(1) 查找
    NodeD dummy;                       // 哨兵节点，简化边界处理
    int capacity;                      // 缓存容量
    
    public LC_146_LRUCache_1(int capacity) {
        this.capacity=capacity;
        hashMap=new HashMap<>();
        dummy=new NodeD(-1,-1);
        dummy.next=dummy;
        dummy.last=dummy;  // 初始化为自循环的双向链表
    }

    public int get(int key) {
        int v=-1;
        if(hashMap.containsKey(key)) {
            v = hashMap.get(key).value;
            remove(hashMap.get(key));      // 从原位置移除
            pushFront(hashMap.get(key));   // 移到链表头部（最近使用）
        }
        return v;
    }

    public void put(int key, int value) {
        if(hashMap.containsKey(key)){
            hashMap.get(key).value=value;  // 更新值
            remove(hashMap.get(key));      // 从原位置移除
            pushFront(hashMap.get(key));   // 移到链表头部
        }else {
            hashMap.put(key,new NodeD(key,value));  // 加入 HashMap
            pushFront(hashMap.get(key));            // 插入链表头部
            if(hashMap.size()>capacity){            // 检查是否超出容量
                NodeD p=dummy.last;                 // 获取最久未使用的节点（尾部）
                remove(p);                          // 从链表移除
                hashMap.remove(p.key);              // 从 HashMap 移除
            }
        }
    }
    
    /**
     * 从双向链表中移除指定节点
     * 注意：调用前确保节点已在链表中（last 和 next 不为 null）
     */
    private void remove(NodeD node){
        node.last.next=node.next;   // 前驱节点跳过当前节点
        node.next.last=node.last;   // 后继节点跳过当前节点
        node.next=null;             // 断开当前节点的连接
        node.last=null;
    }
    
    /**
     * 将节点插入到链表头部（dummy 节点之后）
     * 表示该节点是最近使用的
     */
    private void pushFront(NodeD node){
        node.next=dummy.next;       // 新节点指向原第一个节点
        node.last=dummy;            // 新节点指向前驱 dummy
        dummy.next.last=node;       // 原第一个节点的前驱指向新节点
        dummy.next=node;            // dummy 的后继指向新节点
    }
}
