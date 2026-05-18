package 图论;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode 994. 腐烂的橘子
 * 
 * 【题目描述】
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * - 值 0 代表空单元格；
 * - 值 1 代表新鲜橘子；
 * - 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子周围 4 个方向（上、下、左、右）上的新鲜橘子都会腐烂。
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 * 
 * 【示例】
 * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 
 * 【解题思路】
 * 核心思想：多源BFS（广度优先搜索）
 * 
 * 算法步骤：
 * 1. 将所有初始腐烂橘子的位置加入队列（作为BFS的起点）
 * 2. 按层进行BFS遍历，每一层代表一分钟
 * 3. 对于当前层的每个腐烂橘子，检查其四个方向的新鲜橘子
 * 4. 将新鲜橘子变为腐烂状态并加入下一层队列
 * 5. 每处理完一层，时间计数器加1
 * 6. 最后检查是否还有新鲜橘子剩余
 * 
 * 【时间复杂度】O(m * n) - 每个格子最多被访问一次
 * 【空间复杂度】O(m * n) - 队列最多存储所有格子
 */
public class LC_994_orangesRotting_1 {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new ArrayDeque<>();
        
        // 1. 初始化：将所有腐烂橘子加入队列
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        
        int num = queue.size();  // 当前层的节点数
        int count = 0;           // 经过的分钟数
        
        // 2. BFS遍历
        while (!queue.isEmpty()) {
            for (int n = 0; n < num; n++) {  // 处理当前层的所有节点
                int[] arr = queue.poll();
                int i = arr[0];
                int j = arr[1];
                
                // 检查四个方向的新鲜橘子
                if (i - 1 >= 0 && grid[i - 1][j] == 1) {  // 上方
                    grid[i-1][j]=2;
                    queue.add(new int[]{i - 1, j});
                }
                if (i + 1 < grid.length && grid[i + 1][j] == 1) {  // 下方
                    grid[i + 1][j]=2;
                    queue.add(new int[]{i + 1, j});
                }
                if (j - 1 >= 0 && grid[i][j - 1] == 1) {  // 左方
                    grid[i][j - 1]=2;
                    queue.add(new int[]{i, j - 1});
                }
                if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {  // 右方
                    grid[i][j + 1]=2;
                    queue.add(new int[]{i, j + 1});
                }
            }
            
            num = queue.size();  // 更新下一层的节点数
            if (num > 0) {       // 如果下一层有节点，则时间加1
                count++;
            }
        }
        
        // 3. 检查是否还有新鲜橘子
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j]==1)
                    return -1;  // 存在无法腐烂的新鲜橘子
            }
        }
        
        return count;  // 返回所需分钟数
    }
}
