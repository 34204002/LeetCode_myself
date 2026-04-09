package 双指针;

/**
 * LeetCode 42. 接雨水
 * 
 * 【题目描述】
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 
 * 【示例】
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水。
 * 
 * 【解题思路】
 * 核心思想：双指针 + 维护左右最大高度
 * 
 * 关键观察：
 * - 对于位置 i，能接的雨水量 = min(左边最高, 右边最高) - height[i]
 * - 如果知道 leftMax 和 rightMax，就能立即计算当前位置的雨水量
 * 
 * 算法流程：
 * 1. 初始化 left=0, right=n-1, maxLeftHeight=0, maxRightHeight=0
 * 2. 当 left < right 时：
 *    - 更新 maxLeftHeight 和 maxRightHeight
 *    - 如果 height[left] <= height[right]：
 *      · 说明左边是瓶颈，处理 left 位置
 *      · 如果 height[left] < maxLeftHeight，累加雨水
 *      · left++
 *    - 否则：
 *      · 说明右边是瓶颈，处理 right 位置
 *      · 如果 height[right] < maxRightHeight，累加雨水
 *      · right--
 * 
 * 【为什么这样是对的？】
 * - 当 height[left] <= height[right] 时：
 *   · maxLeftHeight 是 left 左边的最大值（已知）
 *   · maxRightHeight ≥ height[right] ≥ height[left]
 *   · 所以 min(maxLeftHeight, maxRightHeight) = maxLeftHeight
 *   · 只需根据 maxLeftHeight 计算 left 位置的雨水
 * 
 * 【执行示例】height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 过程省略，最终 sumSize = 6
 * 
 * 【时间复杂度】O(n) - 双指针各遍历一次
 * 【空间复杂度】O(1) - 只使用常数变量
 * 
 * 【对比其他方法】
 * - 动态规划：预计算每个位置的左右最大值，O(n) 空间
 * - 单调栈：按层计算雨水，较复杂
 * - 你的方法：双指针最优解，O(1) 空间
 */
public class LC_42_trap_1 {
    public static int trap(int[] height) {
        int sumSize=0;
        int left=0;
        int right=height.length-1;
        int maxLeftHeight=0;  // left 左边的最大高度
        int maxRightHeight=0; // right 右边的最大高度
        while (left<right){
            // 更新左右两边的最大高度
            if(height[left]>maxLeftHeight)
                maxLeftHeight=height[left];
            if(height[right]>maxRightHeight)
                maxRightHeight=height[right];
            if(height[left]<=height[right]){
                // 左边是瓶颈，处理 left 位置
                left++;
                if(height[left]<maxLeftHeight) {
                    // 当前位置低于左边最大值，能接雨水
                    sumSize += maxLeftHeight - height[left];
                }
            }else{
                // 右边是瓶颈，处理 right 位置
                right--;
                if(height[right]<maxRightHeight){
                    // 当前位置低于右边最大值，能接雨水
                    sumSize+=maxRightHeight-height[right];
                }
            }
        }
        return sumSize;
    }
}
