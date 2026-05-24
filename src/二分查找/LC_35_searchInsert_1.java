package 二分查找;

/**
 * LeetCode 35. 搜索插入位置 - 解法一：二分查找
 *
 * 【题目描述】
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 * 【示例】
 * 输入：nums = [1,3,5,6], target = 5
 * 输出：2
 * 输入：nums = [1,3,5,6], target = 2
 * 输出：1
 *
 * 【解题思路】
 * 核心思想：二分查找（标准模板 + 插入位置记录）
 *
 * 算法流程：
 * 1. 初始化 left=0, right=nums.length-1, p=0 记录插入位置
 * 2. 循环执行二分查找，mid = left + (right-left)/2
 * 3. 若 nums[mid] == target，直接返回 mid
 * 4. 若 nums[mid] < target，target应在右边，left=mid+1，p=mid+1
 * 5. 若 nums[mid] > target，target应在左边，right=mid-1，p=mid
 * 6. 循环结束后未找到，返回 p 作为插入位置
 *
 * 【执行示例】nums = [1,3,5,6], target = 2
 *
 * left=0, right=3, p=0
 *   第1轮：mid=1, nums[1]=3 > 2 → right=0, p=1
 *   第2轮：mid=0, nums[0]=1 < 2 → left=1, p=1
 * left(1) > right(0) → 退出循环，return p=1 ✓
 *
 * 插入后数组：[1,2,3,5,6]，2在索引1 ✓
 *
 * 【执行示例】nums = [1,3,5,6], target = 5
 *
 *   第1轮：mid=1, nums[1]=3 < 5 → left=2, p=2
 *   第2轮：mid=2, nums[2]=5 == 5 → return 2 ✓
 *
 * 【时间复杂度】O(log n) - 标准二分查找，每次区间减半
 * 【空间复杂度】O(1) - 只使用常数个变量
 *
 * 【关键点】
 * - 第7行：p 变量记录未找到时的插入位置
 * - 第8行：while 条件 left<=right，确保区间内至少有一个元素
 * - 第9行：使用 left+(right-left)/2 避免整数溢出
 * - 第13-14行：nums[mid] < target 时，插入位置至少是 mid+1
 * - 第17-18行：nums[mid] > target 时，插入位置可能就是 mid
 *
 * 【注意】
 * - 第8行：循环条件必须是 left<=right（不是 left<right），否则区间最后一个元素可能未被检查
 * - 第9行：mid 计算不能用 (left+right)/2，大数组会整数溢出
 * - 第14行和第18行：p 的赋值不同，反映了插入位置的正确语义
 */
public class LC_35_searchInsert_1 {
    public int searchInsert(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        int p=0;
        while (left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target)
                return mid;
            if(nums[mid]<target){
                left=mid+1;
                p=mid+1;
            }
            if(nums[mid]>target){
                right=mid-1;
                p=mid;
            }
        }
        return p;
    }
}
