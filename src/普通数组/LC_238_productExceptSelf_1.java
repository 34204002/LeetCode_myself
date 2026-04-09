package 普通数组;

/**
 * LeetCode 238. 除自身以外数组的乘积
 * 
 * 【题目描述】
 * 给你一个整数数组 nums，返回数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * 题目数据保证数组 nums 之中任意元素的全部前缀元素和后缀的乘积都在 32 位整数范围内。
 * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 
 * 【示例】
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 解释:
 * answer[0] = 2×3×4 = 24
 * answer[1] = 1×3×4 = 12
 * answer[2] = 1×2×4 = 8
 * answer[3] = 1×2×3 = 6
 * 
 * 【解题思路】
 * 核心思想：前缀积 × 后缀积
 * 
 * 对于每个位置 i：
 * - leftarr[i] = nums[0] × nums[1] × ... × nums[i-1]（左边所有元素的乘积）
 * - rightarr[i] = nums[i+1] × nums[i+2] × ... × nums[n-1]（右边所有元素的乘积）
 * - answer[i] = leftarr[i] × rightarr[i]
 * 
 * 算法流程：
 * 1. 从左到右计算前缀积：
 *    - leftarr[i] 存储 i 左边所有元素的乘积
 *    - 初始化 now=1，每次先赋值再累乘
 * 
 * 2. 从右到左计算后缀积：
 *    - rightarr[i] 存储 i 右边所有元素的乘积
 *    - 初始化 now=1，每次先赋值再累乘
 * 
 * 3. 合并结果：answer[i] = leftarr[i] × rightarr[i]
 * 
 * 【执行示例】nums = [1,2,3,4]
 * 
 * 计算前缀积：
 * i=0: leftarr[0]=1, now=1×1=1
 * i=1: leftarr[1]=1, now=1×2=2
 * i=2: leftarr[2]=2, now=2×3=6
 * i=3: leftarr[3]=6, now=6×4=24
 * leftarr = [1, 1, 2, 6]
 * 
 * 计算后缀积：
 * i=3: rightarr[3]=1, now=1×4=4
 * i=2: rightarr[2]=4, now=4×3=12
 * i=1: rightarr[1]=12, now=12×2=24
 * i=0: rightarr[0]=24, now=24×1=24
 * rightarr = [24, 12, 4, 1]
 * 
 * 合并结果：
 * answer[0] = 1×24 = 24
 * answer[1] = 1×12 = 12
 * answer[2] = 2×4 = 8
 * answer[3] = 6×1 = 6
 * answer = [24, 12, 8, 6] ✓
 * 
 * 【时间复杂度】O(n) - 三次遍历数组
 * 【空间复杂度】O(n) - 两个辅助数组
 * 
 * 【优化建议】
 * - 可以用输出数组代替 leftarr，再用一个变量动态计算后缀积
 * - 优化后空间复杂度可降至 O(1)（不计输出数组）
 * 
 * 【关键点】
 * - 第8-11行：计算前缀积，leftarr[i] 不包含 nums[i]
 * - 第12-16行：计算后缀积，rightarr[i] 不包含 nums[i]
 * - 第18-20行：合并前缀积和后缀积得到最终结果
 * 
 * 【为什么不用除法？】
 * - 如果数组中有 0，除法会出错
 * - 题目明确要求不使用除法
 */
public class LC_238_productExceptSelf_1 {
    public static int[] productExceptSelf(int[] nums) {
        int[] leftarr=new int[nums.length];   // 前缀积数组
        int[] rightarr=new int[nums.length];  // 后缀积数组
        int now=1;
        // 从左到右计算前缀积
        for (int i = 0; i < nums.length; i++) {
            leftarr[i]=now;      // 先赋值（不包含当前元素）
            now*=nums[i];        // 再累乘
        }
        now=1;
        // 从右到左计算后缀积
        for (int i = nums.length-1; i >= 0; i--) {
            rightarr[i]=now;     // 先赋值（不包含当前元素）
            now*=nums[i];        // 再累乘
        }
        // 合并前缀积和后缀积
        int[] arr=new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i]=leftarr[i]*rightarr[i];
        }
        return arr;
    }

}
