package 滑动窗口;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 3. 无重复字符的最长子串
 * 
 * 【题目描述】
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 * 
 * 【示例】
 * 输入: s = "abcabcbb"
 * 输出: 3 
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 
 * 【解题思路】
 * 核心思想：使用队列维护滑动窗口，遇到重复字符时收缩窗口
 * 
 * 1. 用 Queue 作为滑动窗口，存储当前无重复的子串
 * 2. 遍历字符串每个字符：
 *    - 如果字符不在队列中：直接入队，更新最大长度
 *    - 如果字符已在队列中：
 *      · 从队头逐个出队，直到移除那个重复字符
 *      · 将当前字符入队
 * 3. 记录队列大小的最大值
 * 
 * 【执行示例】s = "abcabcbb"
 * i=0: 'a' 不在队列 → queue=[a], max=1
 * i=1: 'b' 不在队列 → queue=[a,b], max=2
 * i=2: 'c' 不在队列 → queue=[a,b,c], max=3
 * i=3: 'a' 在队列 → 出队'a' → queue=[b,c] → 入队'a' → queue=[b,c,a], max=3
 * i=4: 'b' 在队列 → 出队'b','c' → queue=[a] → 入队'b' → queue=[a,b], max=3
 * ...
 * 
 * 【时间复杂度】O(n²)
 * - 外层循环 O(n)
 * - queue.contains() 是 O(n)，while 循环最坏也是 O(n)
 * - 总复杂度 O(n²)
 * 
 * 【空间复杂度】O(min(m,n)) - m 为字符集大小，队列最多存储 min(m,n) 个字符
 * 
 * 【优化建议】
 * - 用 HashMap<Character, Integer> 存储 {字符: 索引}，可将 contains 降到 O(1)
 * - 遇到重复时直接跳到重复字符的下一个位置，避免逐个出队
 * - 优化后时间复杂度可降至 O(n)
 * 
 * 【关键点】
 * - 第18-20行的 while 循环：移除重复字符及其之前的所有字符
 * - 保证队列中始终是无重复字符的子串
 */
public class LC_3_lengthOfLongestSubstring_1 {
    public static int lengthOfLongestSubstring(String s) {
        Queue<Character> queue=new ArrayDeque<>();
        int max=0;
        for (int i = 0; i < s.length(); i++) {
            if(!queue.contains(s.charAt(i))){
                // 当前字符不在窗口中，直接加入
                queue.add(s.charAt(i));
                if(queue.size()>max)
                    max= queue.size();
            }else {
                // 当前字符已存在，移除窗口左侧直到重复字符
                char c=queue.poll();
                while (c!=s.charAt(i)&& !queue.isEmpty()){
                    c=queue.poll();
                }
                // 此时重复字符已被移除，加入当前字符
                queue.add(s.charAt(i));
            }

        }
        return max;
    }
}
