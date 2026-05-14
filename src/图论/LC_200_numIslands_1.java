package 图论;

/**
 * LeetCode 200. 岛屿数量
 * 
 * 【题目描述】
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * 
 * 【示例】
 * 输入：grid = [
 *   ['1','1','0','0','0'],
 *   ['1','1','0','0','0'],
 *   ['0','0','1','0','0'],
 *   ['0','0','0','1','1']
 * ]
 * 输出：3
 * 解释：有三座岛屿（左上角2x2、中间单独一个、右下角两个）
 * 
 * 【解题思路】
 * 核心思想：深度优先搜索（DFS）+  visited 标记数组
 * 
 * 算法流程：
 * 1. 初始化计数器 count = 0
 * 2. 创建 arrived 数组标记已访问的位置（'1'表示已访问，'0'表示未访问）
 * 3. 预处理：将 grid 中的 '0'（水）在 arrived 中标记为 '1'（已访问），避免重复处理水域
 * 4. 双重循环遍历整个网格：
 *    - 如果遇到未访问的陆地（arrived[i][j] == '0'）
 *    - 调用 DFS 遍历整座岛屿，将所有相连的陆地标记为已访问
 *    - DFS 返回 true 表示发现新岛屿，count++
 * 5. 返回岛屿数量 count
 * 
 * DFS 递归逻辑：
 * - 边界检查：越界或已访问则返回 flag
 * - 标记当前位置为已访问（arrived[i][j] = '1'）
 * - 设置 flag = true（表示这是一个有效的岛屿部分）
 * - 递归搜索四个方向：上、下、左、右
 * - 返回 flag，表示是否发现了陆地
 * 
 * 【执行示例】grid = [['1','1','0'],['1','1','0'],['0','0','1']]
 * 
 * 第1步：预处理，将水域标记为已访问
 *        arrived初始全'0'，然后grid中'0'的位置在arrived中标为'1'
 * 
 * 第2步：从(0,0)开始，发现未访问陆地，启动DFS
 *        DFS访问(0,0)→(0,1)→(1,0)→(1,1)，全部标记为已访问
 *        count = 1（第一座岛屿）
 * 
 * 第3步：继续遍历，(0,2),(1,2)已是水域（已访问），跳过
 * 
 * 第4步：到达(2,2)，发现未访问陆地，启动DFS
 *        DFS访问(2,2)，标记为已访问
 *        count = 2（第二座岛屿）
 * 
 * 结果：2座岛屿 ✓
 * 
 * 【时间复杂度】O(m×n) - m为行数，n为列数，每个位置最多访问一次
 * 【空间复杂度】O(m×n) - arrived数组的空间 + DFS递归栈的深度
 * 
 * 【关键点】
 * - 第6行：使用静态变量count记录岛屿数量
 * - 第7-14行：预处理步骤，将水域直接标记为已访问，简化后续判断
 * - 第17-21行：遇到未访问的陆地时启动DFS，发现新岛屿则计数+1
 * - 第26-27行：DFS边界检查，越界或已访问则停止递归
 * - 第30行：设置flag=true，表示找到了陆地
 * - 第31行：标记当前位置为已访问，避免重复访问
 * - 第32-35行：递归搜索四个方向，探索整片岛屿
 * 
 * 【注意事项】
 * - 第4行：count使用static变量，每次调用numIslands时需要重置为0
 * - 第10-13行：巧妙地将水域预标记，避免DFS中额外判断是否为水
 * - 第18行：flag参数用于标识是否找到有效陆地，初始传false
 * - 第26行：dfs方法必须是static的，因为numIslands是static方法
 * 
 * 【优化思路】
 * - 可以直接修改原grid数组，将访问过的'1'改为'0'，节省arrived数组空间
 * - 也可以使用BFS（广度优先搜索）替代DFS，避免递归栈溢出风险
 */
public class LC_200_numIslands_1 {
    static int count;
    
    /**
     * 计算岛屿数量
     * @param grid 由'1'（陆地）和'0'（水）组成的二维网格
     * @return 岛屿的数量
     */
    public static int numIslands(char[][] grid) {
        count=0;
        char[][] arrived=new char[grid.length][grid[0].length];
        
        // 预处理：将水域标记为已访问
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j]=='0'){
                    arrived[i][j]='1';
                }else
                    arrived[i][j]='0';
            }
        }
        
        // 遍历网格，发现未访问的陆地则启动DFS
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(arrived[i][j]=='0'){
                     boolean flag = dfs(grid,arrived,i,j,false);
                     if(flag)
                         count++;
                }
            }
        }
        return count;
    }
    
    /**
     * 深度优先搜索，遍历整座岛屿
     * @param grid 原始网格
     * @param arrived 访问标记数组
     * @param i 当前行索引
     * @param j 当前列索引
     * @param flag 是否找到陆地的标志
     * @return true表示找到了陆地，false表示无效路径
     */
    private static boolean dfs(char[][] grid,char[][] arrived,int i,int j, boolean flag){
        // 边界检查：越界或已访问则返回
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||arrived[i][j]=='1'){
            return flag;
        }
        flag=true;              // 标记找到陆地
        arrived[i][j]='1';      // 标记为已访问
        
        // 递归搜索四个方向
        dfs(grid,arrived,i-1,j,flag);  // 上
        dfs(grid,arrived,i+1,j,flag);  // 下
        dfs(grid,arrived,i,j-1,flag);  // 左
        dfs(grid,arrived,i,j+1,flag);  // 右
        return flag;
    }

}
