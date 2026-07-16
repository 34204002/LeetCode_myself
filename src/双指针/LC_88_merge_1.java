package 双指针;

/**
 * LeetCode 88. 合并两个有序数组 - 解法一：双指针（含边界问题分析）
 *
 * 【题目描述】
 * 给你两个按非递减顺序排列的整数数组 nums1 和 nums2，另有 m 和 n 分别表示
 * nums1 和 nums2 中的元素个数。请你合并 nums2 到 nums1 中，使合并后的数组同样按非递减顺序排列。
 * 注意：nums1 的长度为 m + n，其中前 m 个元素为待合并元素，后 n 个元素为 0 占位。
 *
 * 【示例】
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 *
 * 【解题思路】
 * 核心思想：双指针同时遍历两个有序数组，每次取较小的放入临时数组
 *
 * 算法流程：
 * 1. 创建临时数组 sorted，长度 m+n
 * 2. 双指针 p1、p2 分别遍历 nums1 和 nums2，取较小值放入 sorted
 * 3. 一个数组遍历完后，将另一个数组的剩余元素直接拷贝进来
 * 4. 将 sorted 整体拷贝回 nums1
 *
 * 【当前代码边界问题（2处）】
 * 注意：核心 while 逻辑正确（p1<m && p2<n + 数组耗尽兜底），但开头有边界隐患
 *
 * 问题 1 — 第9行：nums1.length == 0 判断有误
 *   nums1 长度固定为 m+n，即使 m=0 时长度也可能 >0（尾部有 n 个占位 0）
 *   应改为 if (m == 0)，此时将 nums2 的前 n 个元素直接拷贝到 nums1 即可
 *
 * 问题 2 — 第10行：nums1 = nums2 赋值无效
 *   Java 是值传递，修改局部变量 nums1 的指向不影响调用者的引用
 *   应改为 System.arraycopy(nums2, 0, nums1, 0, n)
 *   并删去 return（后续拷贝 sorted 的 for 循环仍会执行）
 *
 * 【优化建议】
 * 以上两处问题仅影响 m==0 的边界场景，不影响 m>0 时的正常流程。
 * 更简洁的写法是去掉 if-else 分支，直接在 while 循环中处理所有情况（见下）
 *
 * 【标准解法（双指针 + 临时数组，无视边界）】
 *   int[] sorted = new int[m + n];
 *   int p1 = 0, p2 = 0, i = 0;
 *   while (p1 < m && p2 < n)
 *       sorted[i++] = nums1[p1] <= nums2[p2] ? nums1[p1++] : nums2[p2++];
 *   while (p1 < m) sorted[i++] = nums1[p1++];
 *   while (p2 < n) sorted[i++] = nums2[p2++];
 *   System.arraycopy(sorted, 0, nums1, 0, m + n);
 *
 * 【最优解（逆向双指针，无需临时数组）】
 *   利用 nums1 尾部空闲空间，从后往前放最大的元素：
 *   int i = m + n - 1, p1 = m - 1, p2 = n - 1;
 *   while (p1 >= 0 && p2 >= 0)
 *       nums1[i--] = nums1[p1] >= nums2[p2] ? nums1[p1--] : nums2[p2--];
 *   while (p2 >= 0) nums1[i--] = nums2[p2--];
 *   O(1) 额外空间
 *
 * 【时间复杂度】O(m+n)
 * 【空间复杂度】O(m+n)（当前实现），可优化至 O(1)
 */
public class LC_88_merge_1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] sorted = new int[nums1.length];
        int p1 = 0;
        int p2 = 0;
        if (nums1.length == 0) {
            nums1 = nums2;
            return;
        } else if (nums2.length == 0) {
            return;
        } else {
            int count = 0;
            while (p1 < m && p2 < n) {
                if (nums1[p1] < nums2[p2])
                    sorted[count++] = nums1[p1++];
                else if (nums1[p1] >= nums2[p2])
                    sorted[count++] = nums2[p2++];
            }
            if(p1==m){
                while (p2<n){
                    sorted[count++] = nums2[p2++];
                }
            }else if(p2==n){
                while (p1<m){
                    sorted[count++] = nums1[p1++];
                }
            }
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = sorted[i];
        }
    }


}
