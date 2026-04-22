package 双指针;

/**
 * LeetCode 581. 最短无序连续子数组
 * 
 * 【题目描述】
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 请你找出符合题解的 最短 子数组，并输出它的长度。
 * 
 * 【示例】
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 
 * 【解题思路】
 * 核心思想：一次遍历确定无序子数组的左右边界
 * 
 * 关键观察：
 * - 从左到右遍历时，如果当前元素 < 左边最大值，说明当前位置需要被排序
 * - 从右到左遍历时，如果当前元素 > 右边最小值，说明当前位置需要被排序
 * 
 * 算法流程：
 * 1. 从左到右遍历，维护 leftMax（左边遇到的最大值）：
 *    - 如果 nums[i] >= leftMax：更新 leftMax
 *    - 如果 nums[i] < leftMax：更新 right 边界（该位置需要排序）
 * 
 * 2. 从右到左遍历，维护 rightMin（右边遇到的最小值）：
 *    - 如果 nums[i] <= rightMin：更新 rightMin
 *    - 如果 nums[i] > rightMin：更新 left 边界（该位置需要排序）
 * 
 * 3. 如果 right > left，返回 right - left + 1；否则返回 0（已有序）
 * 
 * 【执行示例】nums = [2,6,4,8,10,9,15]
 * 从左到右：
 * i=0: nums[0]=2>=MIN → leftMax=2
 * i=1: nums[1]=6>=2 → leftMax=6
 * i=2: nums[2]=4<6 → right=2（位置2需要排序）
 * i=3: nums[3]=8>=6 → leftMax=8
 * i=4: nums[4]=10>=8 → leftMax=10
 * i=5: nums[5]=9<10 → right=5（位置5需要排序）
 * i=6: nums[6]=15>=10 → leftMax=15
 * 
 * 从右到左：
 * i=6: nums[6]=15<=MAX → rightMin=15
 * i=5: nums[5]=9<=15 → rightMin=9
 * i=4: nums[4]=10>9 → left=4（位置4需要排序）
 * i=3: nums[3]=8<=9 → rightMin=8
 * i=2: nums[2]=4<=8 → rightMin=4
 * i=1: nums[1]=6>4 → left=1（位置1需要排序）
 * i=0: nums[0]=2<=4 → rightMin=2
 * 
 * 结果：right=5, left=1 → 长度 = 5-1+1 = 5
 * 
 * 【时间复杂度】O(n) - 两次遍历数组
 * 【空间复杂度】O(1) - 只使用常数变量
 * 
 * 【关键点】
 * - 第13行：当 nums[i] < leftMax 时，更新 right 而非立即返回
 * - 第20行：当 nums[i] > rightMin 时，更新 left 而非立即返回
 * - 第23行：判断 right > left 确保数组确实无序
 */
public class LC_581_findUnsortedSubarray_1 {
    public int findUnsortedSubarray(int[] nums) {
        int left=0;           // 无序子数组的左边界
        int right=nums.length-1; // 无序子数组的右边界
        int leftMax=Integer.MIN_VALUE;  // 从左到右遍历时的最大值
        int rightMin=Integer.MAX_VALUE; // 从右到左遍历时的最小值
        
        // 从左到右遍历，确定右边界
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]>=leftMax){
                // 当前元素 >= 左边最大值，更新最大值
                leftMax=nums[i];
            }else {
                // 当前元素 < 左边最大值，说明位置i需要排序，更新右边界
                left=i;
            }
        }
        
        // 从右到左遍历，确定左边界
        for (int i = nums.length-1; i >= 0; i--) {
            if(nums[i]<=rightMin){
                // 当前元素 <= 右边最小值，更新最小值
                rightMin=nums[i];
            }else{
                // 当前元素 > 右边最小值，说明位置i需要排序，更新左边界
                right=i;
            }
        }
        
        // 如果右边界在左边界右侧，说明存在无序子数组
        if(right<left){
            return left-right+1;
        }
        // 数组已经有序
        return 0;
    }
}
