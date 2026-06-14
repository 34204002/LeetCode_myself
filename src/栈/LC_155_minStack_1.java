package 栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 155. 最小栈 - 解法一：双端队列 + 元素-最小值对
 *
 * 【题目描述】
 * 设计一个支持 push、pop、top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类：
 *   - MinStack() 初始化栈对象
 *   - void push(int val) 将元素 val 推入栈
 *   - void pop() 删除栈顶部的元素
 *   - int top() 获取栈顶部的元素
 *   - int getMin() 获取栈中的最小元素
 * 要求：每个函数的调用时间复杂度为 O(1)。
 *
 * 【示例】
 * 输入：["MinStack","push","push","push","getMin","pop","top","getMin"]
 *       [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：[null,null,null,null,-3,null,0,-2]
 * 解释：push(-2) → push(0) → push(-3) → getMin()=-3 → pop() → top()=0 → getMin()=-2
 *
 * 【解题思路】
 * 核心思想：栈中每个位置同时存储「元素值」和「从栈底到当前元素的最小值」
 *
 * 使用 Deque（双端队列）模拟栈，每个栈帧是一个 int[2]：
 *   - [0]：当前元素的值
 *   - [1]：从栈底到当前元素的区间最小值（包含当前元素）
 *
 * 算法流程：
 * 1. 构造方法：初始化空的双端队列
 * 2. push(val)：
 *    - 若栈为空，存入 [val, val]（val 自身即最小）
 *    - 若栈非空，比较 val 与当前栈顶记录的最小值：
 *      val 更小则存 [val, val]，否则存 [val, 原最小值]
 * 3. pop()：移除栈顶元素（pollFirst）
 * 4. top()：返回栈顶的 [0]（元素值）
 * 5. getMin()：返回栈顶的 [1]（当前最小值），O(1) ✓
 *
 * 【执行示例】push(-2), push(0), push(-3), getMin(), pop(), top(), getMin()
 *
 * push(-2): 栈空 → [( -2, -2 )]
 * push(0):  0 >= -2 → [( 0, -2 ), ( -2, -2 )]
 * push(-3): -3 < -2 → [( -3, -3 ), ( 0, -2 ), ( -2, -2 )]
 *
 * getMin(): 栈顶[1] = -3 ✓
 * pop():    移除栈顶 → [( 0, -2 ), ( -2, -2 )]
 * top():    栈顶[0] = 0 ✓
 * getMin(): 栈顶[1] = -2 ✓
 *
 * 【时间复杂度】所有操作均为 O(1)
 * 【空间复杂度】O(n) - 每个元素存储一个长度为2的数组
 *
 * 【关键点】
 * - 第14-15行：栈为空时，当前元素即最小值
 * - 第18-19行：val < 当前最小值时，更新最小值记录
 * - 第20-22行：val >= 当前最小值时，保持原最小值不变
 * - 第34-36行：getMin() 直接读取栈顶的 [1] 字段，无需遍历
 *
 * 【注意】
 * - 第18行比较条件是 < 而非 <=（相等时复用原最小值即可，不影响正确性）
 * - pop 后历史最小值自动"回溯"——因为每个栈帧独立记录了各自时刻的最小值
 * - 另一种常见解法是使用辅助栈，本解法将最小值与元素存在同一栈帧中
 */
public class LC_155_minStack_1 {
    private Deque<int[]> queue;
    public LC_155_minStack_1() {
        this.queue=new ArrayDeque<>();
    }

    public void push(int value) {
        if(queue.isEmpty()) {
            queue.addFirst(new int[]{value, value});
        }
        else {
            if(value<queue.peekFirst()[1]){
                queue.addFirst(new int[]{value,value});
            }else {
                queue.addFirst(new int[]{value,queue.peekFirst()[1]});
            }
        }
    }

    public void pop() {
        queue.pollFirst();
    }

    public int top() {
        return queue.peekFirst()[0];
    }

    public int getMin() {
        return queue.peekFirst()[1];
    }
}
