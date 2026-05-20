package 回溯;

/**
 * LeetCode 79. 单词搜索 - 解法一：回溯算法
 * 
 * 【题目描述】
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word。
 * 如果 word 存在于网格中，返回 true；否则，返回 false。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中"相邻"单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * 
 * 【示例】
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 
 * 【解题思路】
 * 核心思想：回溯算法（深度优先搜索 + 状态重置）
 * 
 * 算法流程：
 * 1. 遍历网格中的每个单元格，找到与单词首字母匹配的位置作为起点
 * 2. 从起点开始进行DFS搜索，向四个方向（上下左右）探索
 * 3. 使用visited数组标记已访问的单元格，避免重复使用
 * 4. 如果当前路径能完整匹配单词，返回true
 * 5. 回溯时恢复visited状态，尝试其他路径
 * 
 * 【执行示例】board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 
 * 起点(0,0)='A' → 匹配word[0]
 *   向右到(0,1)='B' → 匹配word[1]
 *     向右到(0,2)='C' → 匹配word[2]
 *       向下到(1,2)='C' → 匹配word[3]
 *         向下到(2,2)='E' → 匹配word[4]
 *           向左到(2,1)='D' → 匹配word[5] → 找到完整单词 ✓
 * 
 * 结果：true ✓
 * 
 * 【时间复杂度】O(m×n×3^L) - m×n个起点，每个位置最多3个方向（排除回退），L为单词长度
 * 【空间复杂度】O(m×n) - visited数组空间，递归栈深度O(L)
 * 
 * 【关键点】
 * - 第4行：静态布尔变量has，用于标记是否找到目标单词
 * - 第7-8行：初始化has为false，准备开始搜索
 * - 第8-17行：双重循环遍历网格，寻找与单词首字母匹配的起点
 * - 第10-12行：找到首字母匹配的位置，启动DFS搜索
 * - 第13-15行：如果已找到结果，提前返回true，优化性能
 * - 第23-25行：剪枝条件 - 已经找到结果，直接返回
 * - 第28-30行：剪枝条件 - 当前字符不匹配，直接返回
 * - 第33-36行：终止条件 - 找到完整单词，设置has为true
 * - 第39行：标记当前单元格已访问
 * - 第43-60行：向四个方向（上、下、左、右）进行DFS搜索
 * - 第63行：回溯 - 恢复当前单元格的未访问状态
 * 
 * 【注意】
 * - 第4行：使用静态变量has可以在找到结果后快速终止所有递归
 * - 第11行：每次从新的起点开始时，visited数组会被重新初始化为全0
 * - 第43-60行：边界检查确保不会越界，visited检查确保不重复访问
 * - 第63行：回溯时必须恢复visited状态，以便其他路径可以使用该单元格
 */
public class LC_79_exist_1 {
    static boolean has;
    
    public boolean exist(char[][] board, String word) {
        has = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    dfs(board, new int[board.length][board[0].length], word, i, j, 0);
                }
                if (has) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static void dfs(char[][] board, int[][] visited, String word, int i, int j, int count) {
        // 剪枝：已经找到结果
        if (has) {
            return;
        }
        
        // 剪枝：当前字符不匹配
        if (board[i][j] != word.charAt(count)) {
            return;
        }
        
        // 终止条件：找到完整单词
        if (count == word.length() - 1) {
            has = true;
            return;
        }
        
        // 标记当前节点已访问
        visited[i][j] = 1;
        
        // 向四个方向搜索
        // 上
        if (i - 1 >= 0 && visited[i - 1][j] == 0) {
            dfs(board, visited, word, i - 1, j, count + 1);
        }
        
        // 下
        if (i + 1 < board.length && visited[i + 1][j] == 0) {
            dfs(board, visited, word, i + 1, j, count + 1);
        }
        
        // 左
        if (j - 1 >= 0 && visited[i][j - 1] == 0) {
            dfs(board, visited, word, i, j - 1, count + 1);
        }
        
        // 右
        if (j + 1 < board[0].length && visited[i][j + 1] == 0) {
            dfs(board, visited, word, i, j + 1, count + 1);
        }
        
        // 回溯：恢复状态
        visited[i][j] = 0;
    }
}
