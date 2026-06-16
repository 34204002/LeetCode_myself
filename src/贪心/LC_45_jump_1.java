package 贪心;

/**
 * LeetCode 45. 跳跃游戏 II - 解法一：贪心（BFS按层跳跃）
 *
 * 【题目描述】
 * 给定一个长度为 n 的整数数组 nums。初始位置为 0。
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。
 * 换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i+j] 处，其中 0 <= j <= nums[i] 且 i+j < n。
 * 返回到达 nums[n-1] 的最小跳跃次数。题目保证可以到达。
 *
 * 【示例】
 * 输入：nums = [2,3,1,1,4]
 * 输出：2
 * 解释：0→1 (跳1步到3) → 4 (跳3步到终点)，共2步
 *
 * 【解题思路】
 * 核心思想：贪心 BFS——每次在"当前可达范围"内选择能跳得最远的位置
 *
 * 将问题建模为 BFS 按层遍历：
 *   - curEnd：当前这一步能到达的最远边界（当前层的右边界）
 *   - nextEnd：遍历过程中发现的"下一跳"能到达的最远位置
 *   - 当 i 走到 curEnd 时，说明当前层遍历完毕，必须再跳一步（count++），
 *     同时将 curEnd 更新为 nextEnd
 *
 * 算法流程：
 * 1. 初始化 count=0, curEnd=0, nextEnd=0
 * 2. 遍历 i 从 0 到 n-2（到达倒数第二个位置即可）：
 *    a. 更新 nextEnd = max(nextEnd, i + nums[i])
 *    b. 若 i == curEnd（当前层走完）：跳跃次数+1，curEnd = nextEnd
 * 3. 返回 count
 *
 * 【执行示例】nums = [2,3,1,1,4]
 *
 * count=0, curEnd=0, nextEnd=0
 *
 * i=0: nextEnd=max(0,0+2)=2, i==curEnd(0==0) → count=1, curEnd=2
 *      含义：从位置0出发，最远到位置2；但最优可能是跳到位置1(值为3)
 *
 * i=1: nextEnd=max(2,1+3)=4, i!=curEnd(1!=2)
 * i=2: nextEnd=max(4,2+1)=4, i==curEnd(2==2) → count=2, curEnd=4
 *
 * 然后：i=3时 i != curEnd(3!=4)，且 i<4=n-1，继续
 * i=3: nextEnd=max(4,3+1)=4, i==curEnd(3!=4)
 *
 * 循环结束（i 到 n-2=3），return count=2 ✓
 *
 * 【时间复杂度】O(n) - 一次遍历
 * 【空间复杂度】O(1) - 只使用三个变量
 *
 * 【关键点】
 * - 第8行：循环到 n-2 即可，因为到最后一个位置时不需要再跳
 * - 第9行：nextEnd 贪心地记录当前层内能跳到的最远位置
 * - 第10-12行：i==curEnd 时表示当前层探索完毕，必须跳一步
 * - 第13行：curEnd 更新为 nextEnd，进入下一层
 *
 * 【注意】
 * - 第8行：循环条件 i<nums.length-1 是因为到达最后一个元素时不需要再跳跃
 * - 第9行：nextEnd 用 Math.max 而非直接赋值，因为可能有多个位置都能跳得很远
 * - 第10行：之所以在 i==curEnd 时才 count++，是因为这是贪心策略：
 *   在当前可达范围内，我们不急于跳跃，而是先探索完整个范围找到最好的起跳点
 */
public class LC_45_jump_1 {
    public int jump(int[] nums) {
        int count=0;
        int curEnd=0;
        int nextEnd=0;
        for (int i = 0; i < nums.length-1; i++) {
            nextEnd=Math.max(i+nums[i],nextEnd);
            if(i==curEnd) {
                count++;
                curEnd=nextEnd;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new LC_45_jump_1().jump(new int[]{2,3,1,1,4}));
    }
}
