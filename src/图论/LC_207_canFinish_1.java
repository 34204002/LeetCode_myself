package 图论;

import java.util.*;

/**
 * LeetCode 207. 课程表
 * 
 * 【题目描述】
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。先修课程按数组 prerequisites 给出，
 * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则必须先学习课程 bi 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * 
 * 【示例】
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 
 * 【解题思路】
 * 核心思想：拓扑排序（Kahn算法）
 * 
 * 算法步骤：
 * 1. 构建邻接表表示图的依赖关系
 * 2. 计算每个节点的入度（有多少门前置课程）
 * 3. 将所有入度为0的节点加入队列（没有前置课程的课程）
 * 4. BFS处理：每次取出一个入度为0的节点，将其邻居节点入度减1
 * 5. 如果邻居节点入度变为0，则加入队列
 * 6. 最后检查是否所有节点都被处理（入度都为0）
 * 
 * 【时间复杂度】O(V + E) - V是课程数，E是依赖关系数
 * 【空间复杂度】O(V + E) - 存储图和入度数组
 */
public class LC_207_canFinish_1 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. 构建邻接表
        List<List<Integer>> list = new ArrayList<>();
        int[] arr=new int[numCourses];  // 记录每个节点的入度
        Queue<Integer> queue=new ArrayDeque<>();  // 存储入度为0的节点

        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }
        
        // 2. 初始化图的依赖关系  节点-出边->
        for (int[] prerequisite : prerequisites) {
            arr[prerequisite[0]]+=1;  // 目标课程入度+1
            list.get(prerequisite[1]).add(prerequisite[0]);  // 建立从先修课到后续课的边
        }

        // 3. 将所有入度为0的节点加入队列
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==0)
                queue.add(i);
        }
        
        // 4. BFS拓扑排序
        while (!queue.isEmpty()){
            int i=queue.poll();  // 取出一个入度为0的节点
            for (Integer integer : list.get(i)) {  // 遍历其所有邻居
                arr[integer]-=1;  // 邻居节点入度减1
                if(arr[integer]==0)  // 如果入度变为0，加入队列
                    queue.add(integer);
            }
        }

        // 5. 检查是否所有节点都被处理
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]!=0) {  // 存在入度不为0的节点，说明有环
                return false;
            }
        }
        
        return true;  // 所有节点都能被处理，无环
    }
}
