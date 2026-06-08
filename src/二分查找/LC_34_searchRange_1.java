package 二分查找;

/**
 * LeetCode 34. 在排序数组中查找元素的第一个和最后一个位置 - 解法一：二分查找 + 线性扩展
 *
 * 【题目描述】
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。
 * 请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 【示例】
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 【解题思路】
 * 核心思想：二分查找定位 target + 左右线性扩展确定边界
 *
 * 算法流程：
 * 1. 初始化 leftBound=-1, rightBound=-1 表示未找到
 * 2. 标准二分查找，在数组中搜索 target
 * 3. 当 nums[mid] == target 时：
 *    - 将 leftBound 和 rightBound 都初始化为 mid
 *    - 向右扩展 rightBound，直到遇到不等于 target 的元素或数组边界
 *    - 向左扩展 leftBound，直到遇到不等于 target 的元素或数组边界
 *    - 跳出循环
 * 4. 返回 [leftBound, rightBound]（未找到则返回 [-1, -1]）
 *
 * 【执行示例】nums = [5,7,7,8,8,10], target = 8
 *
 * left=0, right=5
 *   第1轮：mid=2, nums[2]=7 < 8 → left=3
 *   第2轮：mid=4, nums[4]=8 == 8 → leftBound=4, rightBound=4
 *     向右扩展：rightBound++ → nums[5]=10 ≠ 8，停止，rightBound=4
 *     向左扩展：leftBound-- → nums[3]=8 == 8, leftBound-- → nums[2]=7 ≠ 8，停止，leftBound=3
 *   return [3,4] ✓
 *
 * 【时间复杂度】O(log n + k) - 二分查找 O(log n) + 左右扩展 O(k)，k 为 target 出现次数
 *   最坏情况（全数组等于 target）退化到 O(n)
 * 【空间复杂度】O(1) - 只使用常数个变量
 *
 * 【关键点】
 * - 第7-8行：leftBound 和 rightBound 初始化为 -1，表示未找到
 * - 第17-18行：找到 target 后先初始化两个边界为 mid
 * - 第19-20行：向右扩展 rightBound，循环条件是 nums[rightBound+1]==target 且不越界
 * - 第21-22行：向左扩展 leftBound，循环条件是 nums[leftBound-1]==target 且不越界
 * - 第24行：扩展完成后直接 break，不需要继续二分
 *
 * 【注意】
 * - 第9行：循环条件 left<=right，确保单元素区间也能检查
 * - 第19行：向右扩展时先检查 rightBound < nums.length-1 再访问 nums[rightBound+1]，防止越界
 * - 第21行：向左扩展时先检查 leftBound > 0 再访问 nums[leftBound-1]，防止越界
 * - 最坏情况下（所有元素都是 target）退化为 O(n)，不满足严格的 O(log n) 要求
 *   如需严格 O(log n)，可使用两次二分查找分别定位左边界和右边界
 */
public class LC_34_searchRange_1 {
    public int[] searchRange(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        int leftBound=-1;
        int rightBound=-1;
        while (left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]>target){
                right=mid-1;
            }
            if(nums[mid]<target){
                left=mid+1;
            }if(nums[mid]==target){
                rightBound=mid;
                leftBound=mid;
                while (rightBound<nums.length-1&&nums[rightBound+1]==target){
                    rightBound++;
                }while (leftBound>0&&nums[leftBound-1]==target){
                    leftBound--;
                }
                break;
            }
        }
        return new int[]{leftBound,rightBound};
    }
}
