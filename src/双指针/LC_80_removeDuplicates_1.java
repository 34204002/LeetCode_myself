package 双指针;

/**
 * LeetCode 80. 删除有序数组中的重复项 II - 解法一：双指针计数
 *
 * 【题目描述】
 * 给你一个有序数组 nums，请你原地删除重复出现的元素，使得每个元素最多出现两次，
 * 返回删除后数组的新长度。不要使用额外的数组空间。
 *
 * 【示例】
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 *
 * 【解题思路】
 * 核心思想：双指针 + 计数，p1 写、p2 读，count 记录已连续写入相同值的次数
 *
 * 算法流程：
 * 1. p1 指向写入位置，p2 指向读取位置，count 记录当前值的已写入次数
 * 2. 每次迭代：从 p2 复制一个元素到 p1，p1++，count++
 * 3. 若 count == 2（当前值已写满两次）：跳过后续所有相同元素
 * 4. 若 count < 2：p2 正常前移，但若遇到新值则重置 count=0
 * 5. 返回 p1（新长度）
 *
 * 【执行示例】nums = [1,1,1,2,2,3]
 *
 * p1=0, p2=0, count=0
 * ──────────────────────────
 * 第1轮: nums[0]=nums[0]=1, count=1, p1=1
 *   count<2 → p2=1, nums[1]==nums[0](1==1) 不重置 count → count=1
 * 第2轮: nums[1]=nums[1]=1, count=2, p1=2
 *   count==2 → 跳过: nums[2]==nums[1](1==1) p2=3; nums[3]!=nums[2](2!=1) 停止
 *   count=0
 * 第3轮: nums[2]=nums[3]=2, count=1, p1=3
 *   count<2 → p2=4, nums[4]==nums[3](2==2) 不重置 → count=1
 * 第4轮: nums[3]=nums[4]=2, count=2, p1=4
 *   count==2 → 跳过: nums[5]=3!=2 停止（p2=5）→ count=0
 * 第5轮: nums[4]=nums[5]=3, count=1, p1=5
 *   count<2 → p2=6, p2越界不判断
 * p1=5, p2=6 越界 → 退出循环
 * return 5 ✓
 *
 * ├─ 最终数组前5位：[1,1,2,2,3]
 *
 * 【时间复杂度】O(n) - 每个元素最多访问一次
 * 【空间复杂度】O(1) - 原地修改
 *
 * 【关键点】
 * - 第6行：p1 写指针，p2 读指针，count 记录已连续写同值的次数
 * - 第11-12行：每轮从一个元素复制到 p1 位置
 * - 第13-16行：已写满 2 次后，跳过连续的相同值直到遇到新值
 * - 第17-21行：未满 2 次时正常推进，若遇到新值则重置 count
 *
 * 【注意】
 * - 第8行：count 初始 0，写入第一个元素后变为 1
 * - 第14行：跳过时 p2 从当前相同段的末尾继续，避免重复处理
 * - 第19-20行：nums[p2] != nums[p2-1] 检测到新值时重置 count
 * - 返回 p1 而非 p1+1，因为 p1 在最后一次复制后自增了一次
 */
public class LC_80_removeDuplicates_1 {

    public int removeDuplicates(int[] nums) {
        int p1 = 0;//应该放的地方
        int p2 = 0;
        int count=0;
        while (p1 < nums.length && p2 < nums.length) {
            nums[p1] = nums[p2];
            count++;
            p1++;
            if(count==2){
                while (p2<nums.length&&nums[p2]==nums[p2-1])
                    p2++;
                count=0;
            }else {
                p2++;
                if(p2<nums.length&&nums[p2]!=nums[p2-1]){
                    count=0;
                }
            }
        }
        return p1;
    }
}
