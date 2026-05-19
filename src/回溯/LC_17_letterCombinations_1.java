package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 17. 电话号码的字母组合 - 解法一：回溯算法
 * 
 * 【题目描述】
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 答案可以按任意顺序返回。
 * 数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 
 * 【示例】
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 
 * 【解题思路】
 * 核心思想：回溯算法（深度优先搜索 + 状态重置）
 * 
 * 算法流程：
 * 1. 将数字字符串转换为对应的字母列表（如"2"→"abc"）
 * 2. 从第一个数字开始，依次选择每个数字对应的字母
 * 3. 当路径长度等于数字个数时，找到一个完整组合
 * 4. 回溯：撤销选择，尝试其他字母
 * 
 * 【执行示例】digits = "23"
 * 
 * 预处理：list = ["abc", "def"]
 * 
 * 第1层：处理数字'2'（对应"abc"）
 *   选择'a'
 *     第2层：处理数字'3'（对应"def"）
 *       选择'd' → "ad" ✓
 *       选择'e' → "ae" ✓
 *       选择'f' → "af" ✓
 *   选择'b'
 *     第2层：处理数字'3'
 *       选择'd' → "bd" ✓
 *       选择'e' → "be" ✓
 *       选择'f' → "bf" ✓
 *   选择'c'
 *     第2层：处理数字'3'
 *       选择'd' → "cd" ✓
 *       选择'e' → "ce" ✓
 *       选择'f' → "cf" ✓
 * 
 * 结果：9种组合 ✓
 * 
 * 【时间复杂度】O(3^m × 4^n) - m个对应3个字母的数字，n个对应4个字母的数字
 * 【空间复杂度】O(m+n) - 递归栈深度，不包括结果存储空间
 * 
 * 【关键点】
 * - 第8-22行：将数字字符转换为对应的字母字符串列表
 * - 第23行：启动DFS，从第0个数字开始处理
 * - 第27-30行：终止条件 - 路径长度等于数字个数，找到完整组合
 * - 第31行：遍历当前数字对应的所有字母
 * - 第32行：做选择 - 将字母加入路径
 * - 第33行：递归处理下一个数字
 * - 第34行：撤销选择 - 回溯，删除最后一个字符
 * 
 * 【注意】
 * - 第23行：空输入时，list为空，dfs会直接返回空结果
 * - 第34行：使用deleteCharAt()移除最后一个字符，实现回溯
 * - 使用StringBuilder便于字符串的追加和删除操作
 */
public class LC_17_letterCombinations_1 {
    public List<String> letterCombinations(String digits) {
        char[] charArray = digits.toCharArray();
        List<String> list=new ArrayList<>();
        List<String> res=new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            switch (charArray[i]){
                case '2':list.add("abc");break;
                case '3':list.add("def");break;
                case '4':list.add("ghi");break;
                case '5':list.add("jkl");break;
                case '6':list.add("mno");break;
                case '7':list.add("pqrs");break;
                case '8':list.add("tuv");break;
                case '9':list.add("wxyz");break;
            }
        }
        dfs(list,res,new StringBuilder(),0);
        return res;
    }
    private static void dfs(List<String> list,List<String> res,StringBuilder path,int count){
        if(path.length()==list.size()){
            res.add(path.toString());
            return;
        }
        for (int i = 0; i < list.get(count).length(); i++) {
            path.append(list.get(count).charAt(i));
            dfs(list,res,path,count+1);
            path.deleteCharAt(path.length()-1);
        }
    }
}
