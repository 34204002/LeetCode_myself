package 二分查找;

/**
 * LeetCode 33. 搜索旋转排序数组 - 解法一：二分查找（分区判断）
 *
 * 【题目描述】
 * 整数数组 nums 按升序排列，数组中的值互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]。
 * 给你旋转后的数组 nums 和一个整数 target，如果 nums 中存在这个目标值 target，则返回它的索引，否则返回 -1。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 【示例】
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 *
 * 【解题思路】
 * 核心思想：先判断 target 在左半区还是右半区，再用二分查找时根据 mid 所在半区调整搜索方向
 *
 * 旋转后的数组被分为两个升序段：
 *   - 左半区（较大值）：nums[0] ~ nums[k-1]（包含 nums[0] 及之后不小于 nums[0] 的元素）
 *   - 右半区（较小值）：nums[k] ~ nums[n-1]（所有小于 nums[0] 的元素）
 *
 * 算法流程：
 * 1. 比较 nums[0] 和 target，确定 target 位于左半区还是右半区
 * 2. 若 target 在右半区（nums[0] > target）：
 *    - 若 mid 在左半区（nums[mid] >= nums[0]），向右移动 left=mid+1
 *    - 若 mid 在右半区（nums[mid] < nums[0]），执行标准二分查找
 * 3. 若 target 在左半区（nums[0] <= target）：
 *    - 若 mid 在左半区（nums[mid] >= nums[0]），执行标准二分查找
 *    - 若 mid 在右半区（nums[mid] < nums[0]），向左移动 right=mid-1
 *
 * 【执行示例】nums = [4,5,6,7,0,1,2], target = 0
 *
 * nums[0]=4 > target=0 → target 在右半区
 * left=0, right=6
 *   第1轮：mid=3, nums[3]=7 >= nums[0]=4 → mid在左半区 → left=4
 *   第2轮：mid=5, nums[5]=1 < nums[0]=4 → mid在右半区 → nums[5]=1 > 0 → right=4
 *   第3轮：mid=4, nums[4]=0 < nums[0]=4 → mid在右半区 → nums[4]=0 == 0 → return 4 ✓
 *
 * 【执行示例】nums = [4,5,6,7,0,1,2], target = 5
 *
 * nums[0]=4 <= target=5 → target 在左半区
 * left=0, right=6
 *   第1轮：mid=3, nums[3]=7 >= nums[0]=4 → mid在左半区 → nums[3]=7 > 5 → right=2
 *   第2轮：mid=1, nums[1]=5 >= nums[0]=4 → mid在左半区 → nums[1]=5 == 5 → return 1 ✓
 *
 * 【时间复杂度】O(log n) - 每次迭代区间减半，最多 log₂(n) 次迭代
 * 【空间复杂度】O(1) - 只使用常数个变量
 *
 * 【关键点】
 * - 第7行：通过 nums[0] 与 target 的比较，先确定 target 所在半区
 * - 第10行：nums[mid] >= nums[0] 判断 mid 是否在左半区
 * - 第13行：nums[mid] < nums[0] 判断 mid 是否在右半区
 * - 第8-21行：target 在右半区时，mid 在左半区则向右跳，mid 在右半区才做正常二分
 * - 第23-38行：target 在左半区时，mid 在右半区则向左跳，mid 在左半区才做正常二分
 *
 * 【注意】
 * - 第7行：nums[0] == target 时走 else 分支（target在左半区），能正确处理
 * - 第8行和第24行：两段 while 逻辑对称但方向相反
 * - 第10行：条件用 >= 而非 >，因为 nums[0] 本身属于左半区
 * - 第13行和第35行：条件用 < 而非 <=，因为小于 nums[0] 的元素都在右半区
 * - 数组中不存在重复元素，不需要处理 nums[mid]==nums[left] 的退化情况
 */
public class LC_33_search_1 {
    public int search(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        if(nums[0]>target){//target在右边
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] >= nums[0]) {//mid在左边
                    left = mid + 1;
                }
                if (nums[mid] < nums[0]) {//mid在右边
                    if (nums[mid] < target) {
                        left = mid + 1;
                    } else if (nums[mid] > target) {
                        right = mid - 1;
                    } else {
                        return mid;
                    }
                }
            }
        }else {//target在左边
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] >= nums[0]) {//mid在左边
                    if (nums[mid] < target) {
                        left = mid + 1;
                    } else if (nums[mid] > target) {
                        right = mid - 1;
                    } else {
                        return mid;
                    }
                }
                if (nums[mid] < nums[0]) {//mid在右边
                    right=mid-1;
                }
            }
        }
        return -1;
    }
}
