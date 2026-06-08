package 二分查找;

/**
 * LeetCode 153. 寻找旋转排序数组中的最小值 - 解法一：二分查找（寻找旋转点）
 *
 * 【题目描述】
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次旋转后，得到输入数组。
 * 例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 *   - 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 *   - 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]（恢复原状）
 * 给你一个元素值互不相同的数组 nums，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。
 * 请你找出并返回数组中的最小元素。你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 【示例】
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 *
 * 【解题思路】
 * 核心思想：二分查找定位旋转点（数组中唯一满足 nums[i] > nums[i+1] 的位置）
 *
 * 旋转后的数组分为两段升序区间：
 *   - 左半区（较大值）：nums[0] ~ nums[k-1]，所有元素 >= nums[0]
 *   - 右半区（较小值）：nums[k] ~ nums[n-1]，所有元素 < nums[0]
 * 最小值即右半区的第一个元素（旋转点 k 处的元素）。
 *
 * 算法流程：
 * 1. 初始化 left=0, right=nums.length-1
 * 2. 循环执行二分查找，mid = left + (right-left)/2
 * 3. 检查 nums[mid+1] <= nums[mid]（取模处理边界），若是则 mid+1 处即为最小值
 * 4. 若 nums[mid] >= nums[0]，说明 mid 在左半区，最小值在右边，left=mid+1
 * 5. 若 nums[mid] < nums[0]，说明 mid 在右半区，最小值在左边（含当前），right=mid-1
 *
 * 【执行示例】nums = [4,5,6,7,0,1,2]
 *
 * left=0, right=6
 *   第1轮：mid=3, nums[3]=7, nums[4]=0 <= 7 → return nums[4]=0 ✓
 *
 * 【执行示例】nums = [3,4,5,1,2]
 *
 * left=0, right=4
 *   第1轮：mid=2, nums[2]=5 >= nums[0]=3 → mid在左半区 → left=3
 *   第2轮：mid=3, nums[3]=1, nums[4]=2 > 1（不满足 nums[4]<=nums[3]）
 *          nums[3]=1 < nums[0]=3 → mid在右半区 → right=2
 * left(3) > right(2) → 退出循环，但不会执行到这里因为有提前返回
 *
 * 等等，重新执行：
 * left=0, right=4
 *   第1轮：mid=2, nums[2]=5 >= nums[0]=3 → left=3
 *   第2轮：mid=3, nums[3]=1, nums[4]=2, nums[4] <= nums[3]? 2 <= 1? false
 *          nums[3]=1 < nums[0]=3 → right=2
 * left(3) > right(2) → 退出循环, return -1?
 *
 * 这个 corner case 有点问题... 实际上对于 [3,4,5,1,2]：
 * left=0, right=4, mid=2
 * nums[mid]=5 >= nums[0]=3 → left=3
 * left=3, right=4, mid=3
 * nums[4] <= nums[3]? nums[4]=2, nums[3]=1, 2 <= 1? false
 * nums[mid]=1 < nums[0]=3 → right=2
 * left(3) > right(2) → 循环结束，return -1 ← BUG!
 *
 * 但实际上代码能 work，因为第9行检查的是 `nums[(mid+1)%nums.length]<=nums[mid]`
 * 当 mid=3 时，(mid+1)%5=4, nums[4]=2, nums[3]=1, 2<=1 为 false
 * 当 mid=2 时，(mid+1)%5=3, nums[3]=1, nums[2]=5, 1<=5 为 true, return 1 ✓
 *
 * 所以对于 [3,4,5,1,2]，第1轮 mid=2 时就返回了！
 *
 * 【关键点】
 * - 第9行：nums[(mid+1)%n] <= nums[mid] 检测旋转点（唯一一个后小于前的位置）
 * - 第9行：取模运算确保当 mid=n-1 时比较 nums[0]<=nums[n-1]，处理未旋转的情况
 * - 第12行：nums[mid] >= nums[0] 判断 mid 在左半区，最小值在右边
 * - 第14行：mid 在右半区，最小值在左边（可能是 mid 本身，但通过步骤3已排除）
 *
 * 【时间复杂度】O(log n) - 标准二分查找，每次区间减半
 * 【空间复杂度】O(1) - 只使用常数个变量
 *
 * 【注意】
 * - 第9行：取模运算 (mid+1)%n 保证数组越界时回绕到索引0
 * - 第9行：比较用 <= 而非 <，因为相等时说明找到了旋转点
 * - 第15行：right=mid-1 而非 right=mid，因为 mid 不可能是最小值（否则第9行已返回）
 * - 当数组完全未旋转（升序）时，第9行在 mid=n-1 时捕获并返回 nums[0]
 */
public class LC_153_findMin_1 {
    public int findMin(int[] nums) {
        int left=0;
        int right=nums.length-1;
        while (left<=right){
            int mid=left+(right-left)/2;
            if(nums[(mid+1)%nums.length]<=nums[mid]){
                return nums[(mid+1)%nums.length];
            }
            if(nums[mid]>=nums[0]){//mid在左半边
                left=mid+1;
            }else {//mid在右半边
                right=mid-1;
            }

        }
        return -1;
    }
}
