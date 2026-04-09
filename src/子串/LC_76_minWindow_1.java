package 子串;

import java.util.*;

/**
 * LeetCode 76. 最小覆盖子串
 * 
 * 【题目描述】
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 
 * 注意：
 * - 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * - 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * 
 * 【示例】
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含 'A'、'B'、'C' 各至少一次
 * 
 * 【解题思路】
 * 核心思想：滑动窗口 + 预处理优化 + 双指针动态收缩
 * 
 * 算法流程：
 * 1. 统计 t 中每个字符的出现次数（TMap）
 * 
 * 2. 预处理优化：提取 s 中属于 t 的字符及其位置
 *    - chList：存储 s 中属于 t 的字符
 *    - pList：存储这些字符在 s 中的原始位置
 *    - 目的：跳过无关字符，提升滑动效率
 * 
 * 3. 滑动窗口在 chList/pList 上操作：
 *    - right 指针扩展窗口：将 chList[right] 加入窗口计数
 *    - 当窗口满足条件（包含 t 的所有字符且数量足够）：
 *      · 计算当前窗口在原字符串 s 中的实际长度
 *      · 更新最小窗口
 *      · left 指针收缩窗口：移除 chList[left]，left++
 *    - 重复上述过程直到 right 遍历完 chList
 * 
 * 4. 判断条件：windowCount[c] >= TMap[c]（窗口中字符 c 的数量 ≥ t 中的数量）
 * 
 * 【执行示例】s = "ADOBECODEBANC", t = "ABC"
 * TMap: {A:1, B:1, C:1}
 * 
 * 预处理后：
 * chList = [A, D, O, B, E, C, O, D, E, B, A, N, C]
 *          过滤后：[A, B, C, B, A, C]
 * pList  = [0, 3, 5, 9, 10, 12]
 * 
 * 滑动窗口过程（在过滤后的列表上）：
 * right=0: window={A:1}, 不满足
 * right=1: window={A:1,B:1}, 不满足
 * right=2: window={A:1,B:1,C:1}, 满足！
 *          窗口长度 = pList[2]-pList[0]+1 = 5-0+1 = 6, minLen=6, minLeft=0, minRight=5
 *          收缩 left: 移除 A → window={B:1,C:1}, 不满足
 * right=3: window={B:2,C:1}, 不满足（缺 A）
 * right=4: window={A:1,B:2,C:1}, 满足！
 *          窗口长度 = pList[4]-pList[1]+1 = 10-3+1 = 8 > 6, 不更新
 *          收缩 left: 移除 B → window={A:1,B:1,C:1}, 仍满足！
 *          窗口长度 = pList[4]-pList[2]+1 = 10-5+1 = 6 = 6, 不更新
 *          收缩 left: 移除 C → window={A:1,B:1}, 不满足
 * right=5: window={A:1,B:1,C:1}, 满足！
 *          窗口长度 = pList[5]-pList[3]+1 = 12-9+1 = 4 < 6, 更新！
 *          minLen=4, minLeft=9, minRight=12
 *          收缩 left: 移除 B → window={A:1,C:1}, 不满足
 * 
 * 结果：s.substring(9, 13) = "BANC" ✓
 * 
 * 【时间复杂度】O(m·n + |t|)
 * - m 为 s 中属于 t 的字符数量，n 为 t 的不同字符数量
 * - isSatisfied 每次遍历 TMap 需要 O(|t|)
 * - 最坏情况下每次 right 移动都触发 while 循环
 * 
 * 【空间复杂度】O(m + |t|)
 * - chList 和 pList 存储过滤后的字符及位置
 * - TMap 和 windowCount 存储字符计数
 * 
 * 【优化建议】
 * - 用计数器替代 isSatisfied 函数：
 *   · 维护一个变量 formed，记录已满足条件的字符种类数
 *   · 窗口中加入字符时，如果该字符数量达到要求，formed++
 *   · formed == TMap.size() 时即满足条件，O(1) 判断
 * - 优化后时间复杂度可降至 O(m)
 * 
 * 【关键点】
 * - 第7-10行：统计 t 中字符出现次数
 * - 第13-21行：预处理优化，过滤 s 中无关字符
 * - 第29-46行：双指针滑动窗口
 *   · right 扩展窗口，left 收缩窗口
 *   · 第35行：通过 pList 计算原字符串中的实际窗口长度
 * - 第54-62行：isSatisfied 判断窗口是否包含 t 的所有字符且数量足够
 *   · 关键：使用 < 而非 !=，允许窗口中字符数量多于 t 中的数量
 * 
 * 【注意】
 * - 第31行使用 'A' 作为基准，假设字符为大写字母
 * - 如果包含小写字母或其他字符，应改用其他映射方式（如 HashMap）
 * - 你的代码中有注释标记【修改点1/2/3】，说明这是经过优化的版本
 */
public class LC_76_minWindow_1 {
    public static String minWindow(String s, String t) {
        // 统计 t 中每个字符的出现次数
        HashMap<Character, Integer> TMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            TMap.put(c, TMap.getOrDefault(c, 0) + 1);
        }

        // 预处理：只保留 s 中属于 t 的字符及其位置（优化滑动效率）
        List<Character> chList = new ArrayList<>();
        List<Integer> pList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (TMap.containsKey(c)) {
                chList.add(c);
                pList.add(i);
            }
        }

        int minLen = Integer.MAX_VALUE;
        int minLeft = -1, minRight = -1;
        int[] windowCount = new int[128]; // 记录当前窗口内字符数量
        int left = 0;

        // 双指针动态滑动窗口
        for (int right = 0; right < chList.size(); right++) {
            char rightChar = chList.get(right);
            windowCount[rightChar - 'A']++;

            // 当窗口满足条件时，尝试收缩左边界以找到更小窗口
            while (isSatisfied(windowCount, TMap)) {
                // 计算当前窗口在原字符串 s 中的实际长度
                int currentLen = pList.get(right) - pList.get(left) + 1;
                if (currentLen < minLen) {
                    minLen = currentLen;
                    minLeft = pList.get(left);
                    minRight = pList.get(right);
                }

                // 收缩左边界
                char leftChar = chList.get(left);
                windowCount[leftChar - 'A']--;
                left++;
            }
        }

        // 返回最小覆盖子串
        return minLeft == -1 ? "" : s.substring(minLeft, minRight + 1);
    }

    // 判断窗口是否包含 t 的所有字符且数量足够
    private static boolean isSatisfied(int[] windowCount, HashMap<Character, Integer> tMap) {
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            char c = entry.getKey();
            // 关键：使用 < 而非 !=，允许窗口中字符数量多于 t 中的数量
            if (windowCount[c - 'A'] < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

}
