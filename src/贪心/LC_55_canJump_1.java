package 贪心;

/**
 * LeetCode 55. 跳跃游戏 - 解法一：贪心（维护最远可达位置）
 *
 * 【题目描述】
 * 给你一个非负整数数组 nums，你最初位于数组的第一个下标。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true；否则，返回 false。
 *
 * 【示例】
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：0→1 (3) → 4 (终点)，可以到达
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：最远只能到位置3，无法跨越值为0的位置到达位置4
 *
 * 【解题思路】
 * 核心思想：贪心地维护当前能到达的最远位置 max，边走边扩张
 *
 * 遍历数组时维护 max = 从起点出发能到达的最远下标：
 *   - 在位置 i，最远可跳到 i + nums[i]
 *   - 不断用更大值更新 max
 *   - 若某个时刻 max >= n-1，说明可以到达终点，返回 true
 *   - 若走到 i == max 且无法继续前进（max 未更新），说明卡住了
 *
 * 算法流程：
 * 1. 初始化 max = 0
 * 2. 遍历 i 从 0 到 n-1：
 *    a. 若 i + nums[i] > max，更新 max = i + nums[i]
 *    b. 若 max >= n-1，立即返回 true
 *    c. 若 i == max（无法再前进），退出循环返回 false
 * 3. 循环结束返回 false
 *
 * 【执行示例】nums = [2,3,1,1,4]
 *
 * max=0
 * i=0: i+nums[0]=2 > max → max=2, max(2)>=4? 否, i(0)==max(2)? 否
 * i=1: i+nums[1]=4 > max → max=4, max(4)>=4? 是 → return true ✓
 *
 * 【执行示例】nums = [3,2,1,0,4]
 *
 * max=0
 * i=0: i+nums[0]=3 > max → max=3, max(3)>=4? 否, i(0)==max(3)? 否
 * i=1: i+nums[1]=3 > max(3)? 否, max(3)>=4? 否, i(1)==max(3)? 否
 * i=2: i+nums[2]=3 > max(3)? 否, max(3)>=4? 否, i(2)==max(3)? 否
 * i=3: i+nums[3]=3 > max(3)? 否, max(3)>=4? 否, i(3)==max(3)? 是 → break
 * return false ✓
 *
 * 【时间复杂度】O(n) - 一次遍历，最多 n 步
 * 【空间复杂度】O(1) - 只使用一个变量
 *
 * 【关键点】
 * - 第5行：max 始终记录从起点出发能到达的最远位置
 * - 第7-8行：贪心更新最远可达距离
 * - 第10-11行：一旦发现可以到达终点，立即返回（剪枝）
 * - 第12-13行：i==max 时说明"走到尽头"且无法前进，类似"死胡同"
 *
 * 【注意】
 * - 第5行：max 初始化为 0（起点本身），而非 nums[0]
 * - 第7行：比较用 > 而非 >=（等于时 max 不变，不更新也没关系）
 * - 第12行：i==max 的条件必须放在更新 max 之后检查，否则可能误判
 * - 与 LC 45（跳跃游戏 II）的区别：本题只需判断可行性，不需计算最小步数
 */
public class LC_55_canJump_1 {
    public boolean canJump(int[] nums) {
        int max=0;
        for (int i = 0; i < nums.length; i++) {
            if(i+nums[i]>max){
                max=i+nums[i];
            }
            if(max>=nums.length-1)
                return true;
            if(i==max)
                break;
        }
        return false;
    }
}
