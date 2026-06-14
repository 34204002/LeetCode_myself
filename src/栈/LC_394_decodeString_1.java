package 栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 394. 字符串解码 - 解法一：显式栈
 *
 * 【题目描述】
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为：k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
 * 注意 k 保证为正整数。你可以认为输入字符串总是有效的，即没有额外的空格，
 * 方括号总是匹配的等。输入中不包含数字，所有的数字只表示重复次数 k。
 *
 * 【示例】
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 *
 * 【解题思路】
 * 核心思想：用栈存储字符，遇到 ']' 时弹出并解码当前括号内的内容，再压回栈中
 *
 * 利用栈（Deque 作为双端栈）处理嵌套结构，从左到右扫描：
 *   - 普通字符（字母、数字、'['）直接压入栈顶
 *   - 遇到 ']' 时：
 *     1. 弹出字符直到遇到 '['，得到逆序的括号内字符串
 *     2. 弹出 '[' 本身
 *     3. 弹出数字字符直到非数字，得到逆序的数字字符串
 *     4. 将数字字符串反转后解析为整数 n
 *     5. 将括号内字符串重复 n 次，逆序压回栈中
 *
 * 解码全部完成后，栈中字符从底到顶即为结果。
 *
 * 算法流程：
 * 1. 遍历输入字符串 s 的每个字符
 * 2. 若字符不是 ']'：压入栈顶（addFirst）
 * 3. 若字符是 ']'：
 *    a. 弹出字符拼接到 str，直到遇到 '['，得到逆序的子串
 *    b. 弹出 '[' 本身
 *    c. 弹出数字字符拼接到 num，直到非数字或栈空，得到逆序的数字字符串
 *    d. 反转 num 并解析为整数 n
 *    e. 将 str 反转后（即原序）重复 n 次压回栈顶
 * 4. 遍历结束后，栈中字符从底到顶（pollLast）拼接输出
 *
 * 【执行示例】s = "3[a]2[bc]"
 *
 * 初始：[ ]
 * i=0 '3' → addFirst → [3]
 * i=1 '[' → addFirst → [[, 3]
 * i=2 'a' → addFirst → [a, [, 3]
 * i=3 ']' → 解码：
 *   str=逆序弹出 "a"→"a", 弹出'[' → [3]
 *   num=逆序弹出 "3"→"3", 栈空 → []
 *   n=3, str="a", 重复3次逆序压入 → [a, a, a]
 * i=4 '2' → addFirst → [2, a, a, a]
 * i=5 '[' → addFirst → [[, 2, a, a, a]
 * i=6 'b' → addFirst → [b, [, 2, a, a, a]
 * i=7 'c' → addFirst → [c, b, [, 2, a, a, a]
 * i=8 ']' → 解码：
 *   str=逆序弹出 "cb"→"cb", 弹出'[' → [2, a, a, a]
 *   num=逆序弹出 "2"→"2", 栈空 → [a, a, a]
 *   n=2, str="cb", 重复2次逆序压入 → [b, c, b, c, a, a, a]
 * 遍历结束，pollLast 拼接："aaabcbc" ✓
 *
 * 【执行示例】s = "3[a2[c]]"
 *
 * i=0 '3' → [3]
 * i=1 '[' → [[, 3]
 * i=2 'a' → [a, [, 3]
 * i=3 '2' → [2, a, [, 3]
 * i=4 '[' → [[, 2, a, [, 3]
 * i=5 'c' → [c, [, 2, a, [, 3]
 * i=6 ']' → str="c", num="2", n=2, 压入c,c → [c, c, a, [, 3]
 * i=7 ']' → str="acc"(逆序"cca"), num="3", n=3, 压入a,c,c重复3次
 *   → [a, c, c, a, c, c, a, c, c]
 * pollLast: "accaccacc" ✓
 *
 * 【时间复杂度】O(n × maxK) - n 为解码后字符串长度，maxK 为最大重复次数
 *   外层遍历输入字符串 O(|s|)，内层字符重复 O(解码后长度)
 * 【空间复杂度】O(n) - 栈中最多存储解码后所有字符
 *
 * 【关键点】
 * - 第12行：用双端队列 Deque<Character>，addFirst 压栈，pollFirst 弹栈
 * - 第14-16行：遇到 ']' 前一直弹字符，str 收集的是逆序的子串
 * - 第17行：丢弃匹配的 '['
 * - 第18-21行：继续弹数字字符（'0'~'9'），注意栈中数字是逆序的
 * - 第22行：Integer.parseInt(num.reverse().toString()) 反转后解析
 * - 第23-26行：将 str 逆序压回 n 次（从 str.length()-1 到 0），从而还原回原序
 * - 第33-35行：最终用 pollLast 从栈底取出，还原完整顺序
 *
 * 【注意】
 * - 第14行：条件用 peekFirst() != '[' 而非 peekFirst().equals('[')，因为栈存的是 Character
 * - 第19行：数字判断条件用 '0' <= ... <= '9'，需要同时检查 !isEmpty() 防止空栈
 * - 第22行：parseInt 前需要 reverse()，因为栈中弹出的数字是逆序的
 * - 第24-25行：str 字符逆序压入，抵消了步骤 a 中弹出导致的逆序
 * - 第33-35行：最终拼接必须从底到顶（pollLast），因为栈中顺序是解码后的逆序
 */
public class LC_394_decodeString_1 {
    public String decodeString(String s) {
        Deque<Character> deque=new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)==']'){
                StringBuilder str=new StringBuilder();
                while (deque.peekFirst()!='['){
                    str.append(deque.pollFirst());
                }
                deque.pollFirst();
                StringBuilder num=new StringBuilder();
                while (!deque.isEmpty()&&'0'<=deque.peekFirst()&&deque.peekFirst()<='9') {
                    num.append(deque.pollFirst());
                }
                int n=Integer.parseInt(num.reverse().toString());
                for (int j = 0; j < n; j++) {
                    for (int k = str.length()-1; k >= 0; k--) {
                        deque.addFirst(str.charAt(k));
                    }
                }
            }else {
                deque.addFirst(s.charAt(i));
            }
        }
        StringBuilder res=new StringBuilder();
        while (!deque.isEmpty()){
            res.append(deque.pollLast());
        }
        return res.toString();
    }

}
