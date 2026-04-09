package 哈希;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode 49. 字母异位词分组
 * 
 * 【题目描述】
 * 给你一个字符串数组，请你将字母异位词组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词是由重新排列源单词的所有字母得到的新单词。
 * 
 * 【示例】
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 
 * 解释：
 * - "bat" 没有其他异位词，单独一组
 * - "nat" 和 "tan" 互为异位词，分为一组
 * - "ate", "eat", "tea" 互为异位词，分为一组
 * 
 * 【解题思路】
 * 核心思想：异位词排序后结果相同，用排序后的字符串作为 key
 * 
 * 1. 遍历每个字符串 str：
 *    - 将 str 转为字符数组并排序
 *    - 排序后的字符串作为 HashMap 的 key
 *    - 将原字符串 str 添加到对应 key 的 value 列表中
 * 
 * 2. 例如：
 *    - "eat" -> 排序后 "aet" -> map["aet"] = ["eat"]
 *    - "tea" -> 排序后 "aet" -> map["aet"] = ["eat", "tea"]
 *    - "ate" -> 排序后 "aet" -> map["aet"] = ["eat", "tea", "ate"]
 * 
 * 3. 最后返回 map 中所有的 value 列表
 * 
 * 【时间复杂度】O(n * k log k)
 * - n 为字符串数量，k 为字符串最大长度
 * - 每个字符串排序需要 O(k log k)，共 n 个字符串
 * 
 * 【空间复杂度】O(n * k) - 存储所有字符串
 * 
 * 【其他方法】
 * - 也可以用字符计数作为 key（26个字母的出现次数），避免排序
 * - 但排序方法代码更简洁，实际性能也较好
 * 
 * 【关键点】
 * - Arrays.toString(charArray) 将字符数组转为字符串作为 key
 * - 使用 Stream API 提取所有 value 组成结果列表
 */
public class LC_49_groupAnagrams_1 {
    public static List<List<String>> groupAnagrams(String[] strs) {
        // key: 排序后的字符串, value: 所有异位词的列表
        HashMap<String,List<String>> sortedStrAndStr=new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            // 如果该排序结果还未出现过，创建新的列表
            if(!sortedStrAndStr.containsKey(Arrays.toString(charArray))) {
                List<String> list=new ArrayList<>();
                list.add(str);
                sortedStrAndStr.put(Arrays.toString(charArray), list);
            }else{
                // 已存在，直接添加到对应列表
                sortedStrAndStr.get(Arrays.toString(charArray)).add(str);
            }
        }
        // 提取所有 value 组成结果列表
        Set<Map.Entry<String, List<String>>> entries = sortedStrAndStr.entrySet();
        return entries.stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }
}
