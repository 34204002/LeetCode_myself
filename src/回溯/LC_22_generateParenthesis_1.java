package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 22. 括号生成 - 解法一：回溯算法
 * 
 * 【题目描述】
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。
 * 
 * 【示例】
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 
 * 【解题思路】
 * 核心思想：回溯算法（深度优先搜索 + 状态重置）
 * 
 * 算法流程：
 * 1. 使用StringBuilder构建括号字符串
 * 2. 维护左括号数量count1和右括号数量count2
 * 3. 每次可以选择添加左括号或右括号
 * 4. 剪枝条件：左括号数量不能超过n，右括号数量不能超过左括号数量
 * 5. 当左右括号都达到n个时，找到一个有效组合
 * 
 * 【执行示例】n = 3
 * 
 * 选择'(' → count1=1, count2=0
 *   选择'(' → count1=2, count2=0
 *     选择'(' → count1=3, count2=0
 *       选择')' → count1=3, count2=1
 *         选择')' → count1=3, count2=2
 *           选择')' → count1=3, count2=3 → "((()))" ✓
 *     选择')' → count1=2, count2=1
 *       选择'(' → count1=3, count2=1
 *         选择')' → count1=3, count2=2
 *           选择')' → count1=3, count2=3 → "(()())" ✓
 *       选择')' → count1=2, count2=2
 *         选择'(' → count1=3, count2=2
 *           选择')' → count1=3, count2=3 → "(())()" ✓
 *   选择')' → count1=1, count2=1
 *     选择'(' → count1=2, count2=1
 *       选择'(' → count1=3, count2=1
 *         选择')' → count1=3, count2=2
 *           选择')' → count1=3, count2=3 → "()(())" ✓
 *       选择')' → count1=2, count2=2
 *         选择'(' → count1=3, count2=2
 *           选择')' → count1=3, count2=3 → "()()()" ✓
 * 
 * 结果：5种有效组合 ✓
 * 
 * 【时间复杂度】O(4^n/√n) - 第n个卡特兰数的渐近上界
 * 【空间复杂度】O(n) - 递归栈深度，最多2n层
 * 
 * 【关键点】
 * - 第8行：创建结果列表存储所有有效括号组合
 * - 第9行：启动DFS，初始左右括号计数都为0
 * - 第13-14行：剪枝条件 - 右括号数量超过左括号数量，无效组合
 * - 第15-16行：剪枝条件 - 左括号数量超过n，无效组合
 * - 第17-19行：终止条件 - 左右括号都达到n个，找到有效组合
 * - 第20-32行：循环尝试两种选择：左括号(i=0)和右括号(i=1)
 * - 第21-24行：根据i的值添加对应的括号到路径中
 * - 第25-30行：根据i的值递归调用DFS，更新对应的计数器
 * - 第31行：回溯 - 删除最后添加的括号，恢复状态
 * 
 * 【注意】
 * - 第13行：右括号数量不能超过左括号数量，这是保证括号有效性的关键
 * - 第18行：使用path.toString()创建字符串副本，避免后续修改影响已添加的结果
 * - 第31行：必须在循环末尾执行回溯操作，撤销本次选择
 * - 第20-32行：使用循环简化代码，也可以用两次独立的递归调用实现
 */
public class LC_22_generateParenthesis_1 {
    public List<String> generateParenthesis(int n) {
        List<String> list=new ArrayList<>();
        dfs(list,new StringBuilder(),0,0,n);
        return list;
    }
    private static void dfs(List<String> list,StringBuilder path,int count1,int count2,int n){
        if(count1<count2)
            return;
        if(count1>n)
            return;
        if(count1==n&&count2==n){
            list.add(path.toString());
        }
        for (int i = 0; i < 2; i++) {
            if(i==0)
                path.append('(');
            if(i==1)
                path.append(')');
            if(i==0) {
                dfs(list, path, count1 + 1, count2, n);
            }
            if(i==1) {
                dfs(list, path, count1, count2 + 1, n);
            }
            path.deleteCharAt(path.length()-1);
        }
    }
}
