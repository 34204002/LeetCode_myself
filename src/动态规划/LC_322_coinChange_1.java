package 动态规划;

/**
 * LeetCode 322. 零钱兑换 - 解法一：动态规划
 *
 * 【题目描述】
 * 给你一个整数数组 coins，表示不同面额的硬币；以及一个整数 amount，表示总金额。
 * 计算并返回可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的。
 *
 * 【示例】
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 【解题思路】
 * 核心思想：一维 DP，dp[i] 表示凑成金额 i 所需的最少硬币数
 *
 * 状态定义：
 * - dp[i]：凑足金额 i 需要的最少硬币个数
 *
 * 状态转移：
 * - 对于每个金额 i，尝试每种硬币 coins[j]：
 *   - 若 i == coins[j]，dp[i] = 1
 *   - 若 i > coins[j] 且 dp[i-coins[j]] != -1，则 dp[i] = min(dp[i], dp[i-coins[j]] + 1)
 * - 若遍历后 dp[i] 仍为 MAX_VALUE，设为 -1（不可达）
 *
 * 边界条件：
 * - dp[0] = 0
 *
 * 算法流程：
 * 1. dp[0]=0，dp[1..amount] 初始 MAX_VALUE
 * 2. 外层遍历金额 i，内层遍历硬币面额
 * 3. 返回 dp[amount]
 *
 * 【执行示例】coins = [1, 2, 5], amount = 11
 *
 * dp[0]=0
 * i=1: j=0(i==1)→dp[1]=1 → dp[1]=1
 * i=2: j=1(i==2)→dp[2]=1 → dp[2]=1
 * i=3: j=0(dp[2]!=-1)→dp[3]=min(MAX, 1+1=2)=2; j=1(dp[1]!=-1)→dp[3]=min(2,1+1=2)=2 → dp[3]=2
 * ...
 * i=11: j=0(dp[10]!=-1)→dp[11]=min(MAX, 3+1=4)=4; j=1(dp[9]!=-1)→min(4,3+1=4)=4;
 *        j=2(dp[6]!=-1)→min(4,2+1=3)=3 → dp[11]=3 ✓（11=5+5+1）
 *
 * 【时间复杂度】O(amount × len(coins))
 * 【空间复杂度】O(amount)
 *
 * 【关键点】
 * - 第9行：dp[0]=0 是最小单位边界
 * - 第12行：i == coins[j] 说明一枚硬币就可凑成，dp[i] 直接为 1
 * - 第14行：i > coins[j] 且子问题有解时才参与最小值比较
 * - 第16-17行：遍历完所有硬币仍为 MAX_VALUE 则不可达，设为 -1
 */
public class LC_322_coinChange_1 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i == coins[j])
                    dp[i] = 1;
                if (i > coins[j] && dp[i - coins[j]] != -1)
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
            if (dp[i] == Integer.MAX_VALUE)
                dp[i] = -1;
        }
        return dp[amount];
    }
}
