package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 131. 分割回文串 - 解法一：回溯算法
 *
 * 【题目描述】
 * 给你一个字符串 s，请你将 s 分割成一系列回文子串，并返回所有可能的分割方案。
 * 回文串是正着读和反着读都一样的字符串。
 *
 * 【示例】
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 *
 * 【解题思路】
 * 核心思想：回溯算法（深度优先搜索 + 回文判断）
 *
 * 算法流程：
 * 1. 从索引0开始，枚举所有可能的切割位置i
 * 2. 截取子串 s[index, i]，判断是否为回文
 * 3. 如果是回文，加入当前路径，递归处理剩余子串 s[i+1, end]
 * 4. 当index到达字符串末尾时，找到一种完整的分割方案
 * 5. 回溯：撤销当前切割，尝试其他切割位置
 *
 * 【执行示例】s = "aab"
 *
 * 第1层：i=0, 子串="a" (回文)
 *   第2层：i=1, 子串="a" (回文)
 *     第3层：i=2, 子串="b" (回文)
 *       第4层：index=3 == s.length() → ["a","a","b"] ✓
 *   第2层：i=2, 子串="ab" (不是回文) → 跳过 ✗
 * 第1层：i=1, 子串="aa" (回文)
 *   第2层：i=2, 子串="b" (回文)
 *     第3层：index=3 == s.length() → ["aa","b"] ✓
 * 第1层：i=2, 子串="aab" (不是回文) → 跳过 ✗
 *
 * 结果：[["a","a","b"],["aa","b"]] ✓
 *
 * 【时间复杂度】O(n×2^n) - 最坏情况下有2^(n-1)种分割方案，每个方案需要O(n)复制
 * 【空间复杂度】O(n) - 递归栈深度O(n)，不包括结果存储空间
 *
 * 【关键点】
 * - 第7行：创建结果列表存储所有分割方案
 * - 第8行：启动DFS，从索引0开始，初始路径为空
 * - 第12-14行：终止条件 - index到达字符串末尾，保存当前路径的副本
 * - 第15行：枚举所有可能的切割位置，从index到s.length()-1
 * - 第16行：截取子串 s[index, i]
 * - 第17行：判断子串是否为回文，不是回文则跳过
 * - 第18行：做选择 - 将回文子串加入路径
 * - 第19行：递归处理剩余部分，索引跳至i+1
 * - 第20行：撤销选择 - 回溯，移除最后一个子串
 * - 第24-34行：双指针法判断回文串，左右向内收缩比较字符
 *
 * 【注意】
 * - 第13行：必须创建新的ArrayList副本，否则后续修改会影响已添加的结果
 * - 第15行：i从index开始（不是0），i是切割终点位置
 * - 第19行：递归传入i+1而非index+1，确保不重复切割
 * - 第20行：使用removeLast()移除最后一个元素，实现状态回溯
 */
public class LC_131_partition_1 {
    public List<List<String>> partition(String s) {
        List<List<String>> list=new ArrayList<>();
        dfs(s,0,new ArrayList<>(),list);
        return list;
    }
    private static void dfs(String s,int index,List<String> list,List<List<String>> res){
        if(index==s.length()){
            res.add(new ArrayList<>(list));
        }
        for (int i = index; i < s.length(); i++) {
            String sub=s.substring(index,i+1);
            if(isPar(sub)) {
                list.add(sub);
                dfs(s, i + 1, list, res);
                list.removeLast();
            }
        }
    }
    private static boolean isPar(String s){
        int left=0;
        int right=s.length()-1;
        while (left<right){
            if(s.charAt(left)!=s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
