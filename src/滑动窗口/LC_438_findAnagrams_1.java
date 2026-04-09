package 滑动窗口;

import java.util.*;

/**
 * LeetCode 438. 找到字符串中所有字母异位词
 * 
 * 【题目描述】
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的异位词的子串，返回这些子串的起始索引。
 * 异位词指由相同字母重排列形成的字符串（包括相同的字符串）。
 * 
 * 【示例】
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * 
 * 【解题思路】
 * 核心思想：固定大小滑动窗口 + 字符计数比较
 * 
 * 1. 统计 p 中每个字符的出现次数（pChar 数组）
 * 2. 用队列维护大小为 p.length() 的滑动窗口
 * 3. 遍历字符串 s：
 *    - 窗口未满：继续添加字符
 *    - 窗口已满：
 *      · 统计窗口内字符出现次数（queueChar 数组）
 *      · 比较 pChar 和 queueChar 是否相等
 *      · 相等则记录起始索引 i - p.length()
 *      · 窗口滑动：poll() 队头，add() 新字符
 * 
 * 【执行示例】s="cbaebabacd", p="abc"
 * pChar: [1,1,1,0,...] (a:1, b:1, c:1)
 * 
 * i=3: queue=[c,b,a], queueChar=[1,1,1,0,...] → 相等 → list.add(0)
 * i=4: queue=[b,a,e] → 不等
 * i=5: queue=[a,e,b] → 不等
 * i=6: queue=[e,b,a] → 不等
 * i=7: queue=[b,a,b] → 不等
 * i=8: queue=[a,b,a] → 不等
 * i=9: queue=[b,a,c] → queueChar=[1,1,1,0,...] → 相等 → list.add(6)
 * 
 * 【时间复杂度】O(n·m·k)
 * - n 为 s 的长度，m 为 p 的长度，k 为字符集大小(26)
 * - 每次比较数组需要 O(26)，共 n 次
 * - 统计 queueChar 需要 O(m)
 * - 总复杂度较高，可优化
 * 
 * 【空间复杂度】O(m) - 队列存储窗口字符
 * 
 * 【优化建议】
 * - 不用每次都重新统计 queueChar，维护一个动态更新的计数数组
 * - 窗口滑动时：旧字符计数-1，新字符计数+1
 * - 优化后时间复杂度可降至 O(n)
 * 
 * 【关键点】
 * - 第11-14行：预处理 p 的字符计数
 * - 第22-26行：每次窗口满时重新统计字符计数（性能瓶颈）
 * - 第28行：Arrays.equals 比较两个计数数组
 */
public class LC_438_findAnagrams_1 {
    public static List<Integer> findAnagrams(String s, String p) {
        if(s.length()<p.length()) {
            return new ArrayList<>();
        }

        // 统计 p 中每个字符的出现次数
        char[] pChar=new char[26];
        for (int j = 0; j < p.length(); j++) {
            pChar[p.charAt(j)-'a']+=1;
        }

        Queue<Character> queue=new ArrayDeque<>();
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <= s.length(); i++) {
            if(queue.size()<p.length()){
                // 窗口未满，继续添加
                queue.add(s.charAt(i));
            }else {
                // 窗口已满，统计当前窗口的字符计数
                char[] queueChar=new char[26];

                for (Character c : queue) {
                    queueChar[c-'a']+=1;
                }

                // 比较两个计数数组是否相等
                if (Arrays.equals(pChar,queueChar)){
                    list.add(i-p.length());  // 记录起始索引
                }
                // 窗口滑动：移除队头，添加新字符
                if(i<s.length()){
                    queue.poll();
                    queue.add(s.charAt(i));
                }
            }
        }
        return list;
    }
}
