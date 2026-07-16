package 双指针;

/**
 * LeetCode 27. 移除元素 - 解法一：双指针交换
 *
 * 【题目描述】
 * 给你一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素。
 * 元素的顺序可以发生改变。然后返回 nums 中与 val 不同的元素的数量。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改。
 *
 * 【示例】
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2,_,_]
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3,_,_,_]
 *
 * 【解题思路】
 * 核心思想：双指针遍历，将等于 val 的元素与后面不等于 val 的元素交换位置
 *
 * 算法流程：
 * 1. p1 遍历数组，p2 从 p1 后面寻找可用于替换的非 val 元素
 * 2. 当 nums[p1] == val 时：
 *    a. p2 从当前位置向后跳过所有 val，找到第一个非 val 元素
 *    b. 若 p2 越界则说明后面全是 val，直接跳出返回已处理长度
 *    c. 将 nums[p2] 交换到 p1 位置，nums[p2] 标记为 val
 * 3. p1++，p2++ 继续处理下一个位置
 * 4. 返回 p1（已处理完的位置即新长度）
 *
 * 【执行示例】nums = [3,2,2,3], val = 3
 *
 * p1=0, p2=1
 * nums[0]=3==val → 内层：nums[1]=2!=val，不进入循环
 * p2=1 != 4 → 交换：nums[0]=nums[1]=2, nums[1]=3 → [2,3,2,3]
 * p1=1, p2=2
 * nums[1]=3==val → 内层：nums[2]=2!=val，不进入
 * p2=2 != 4 → 交换：nums[1]=nums[2]=2, nums[2]=3 → [2,2,3,3]
 * p1=2, p2=3
 * nums[2]=3==val → 内层：nums[3]=3==val，p2=4；nums[4]越界退出
 * p2=4==len → break
 * return 2 ✓
 *
 * 【时间复杂度】O(n) - 每个元素最多被访问一次
 * 【空间复杂度】O(1) - 原地修改
 *
 * 【关键点】
 * - 第7行：p1 是遍历指针，也是最终返回值（非 val 元素个数）
 * - 第10行：内层 while 跳过连续的 val 元素
 * - 第12-13行：p2 越界意味着后半段全是 val，直接结束
 * - 第14-15行：将找到的非 val 元素交换到前面，并在原位置填入 val
 *
 * 【注意】
 * - 第14行：交换后 nums[p1] 变为非 val，nums[p2] 变为 val
 * - 第11行：p2 可能等于 p1+1（初始状态），但交换后 p2 位置可能小于 p1
 * - 返回的是 p1 而非 p1+1，因为 p1 在最后一次循环中自增后才退出
 * - 此解法不是最简方案，标准解法常用「快慢覆盖」更简洁
 */
public class LC_27_removeElement_1 {
    public int removeElement(int[] nums, int val) {
        int p1 = 0;
        int p2 = 1;
        while (p1 < nums.length) {
            if (nums[p1] == val) {
                while (p2 < nums.length && nums[p2] == val)
                    p2++;
                if (p2 == nums.length)
                    break;
                nums[p1] = nums[p2];
                nums[p2] = val;
            }
            p1++;
            p2++;
        }
        return p1;
    }

}
