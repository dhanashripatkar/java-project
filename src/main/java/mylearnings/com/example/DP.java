package mylearnings.com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DP {

    public static void main(String args[]) {
        DP dp = new DP();
        List<String> list = new ArrayList<>();
        list.add("cats");
        list.add("dog");
        list.add("sand");
        list.add("and");
        list.add("cat");
        dp.wordBreak("catsandog", list);
    }

    /**
     * Input: s = "leetcode", wordDict = ["leet","code"]
     * Output: true
     * Explanation: Return true because "leetcode" can be segmented as "leet code".
     * 
     * /**
     * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
     * Output: false
     * 
     */

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;

        for (int i = s.length() - 1; i >= 0; i--) {
            for (String w : wordDict) {
                if ((i + w.length()) <= s.length() && s.startsWith(w, i)) {
                    dp[i] = dp[i + w.length()];
                }
                if (dp[i]) {
                    break;
                }
            }
        }
        return dp[0];
    }

    /**
     * You are climbing a staircase. It takes n steps to reach the top.
     * 
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can
     * you climb to the top?
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: n = 2
     * Output: 2
     * Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     * 
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[n] = 1;
        dp[n - 1] = 1;

        for (int i = n - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] + dp[i + 2];
        }
        return dp[0];

        // alternate way
        // if(n == 1) {
        // return 1;
        // }

        // final int[] f = new int[n+2];

        // f[0] = 0;
        // f[1] = 1;

        // for(int i = 2; i <= n+1; i++) {
        // f[i] = f[i-1] + f[i-2];
        // }

        // return f[n+1];
        // =================================
        // optimal

        // int a = 1;
        // int b = 1;
        // int c;

        // for (int i = 0; i < n - 1; i++) {
        // c = a + b;
        // a = b;
        // b = c;
        // }
        // return b;

    }

    /**
     * You are given an integer array cost where cost[i] is the cost of ith step on
     * a staircase. Once you pay the cost, you can either climb one or two steps.
     * 
     * You can either start from the step with index 0, or the step with index 1.
     * 
     * Return the minimum cost to reach the top of the floor.
     *
     * 
     * Example 1:
     * 
     * Input: cost = [10,15,20]
     * Output: 15
     * Explanation: You will start at index 1.
     * - Pay 15 and climb two steps to reach the top.
     * The total cost is 15.
     * 
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int one = 0;
        int two = 0;

        for (int i = cost.length - 1; i >= 0; i--) {
            cost[i] += Math.min(one, two);
            one = two;
            two = cost[i];
        }
        return Math.min(cost[0], cost[1]);

        // int n =cost.length;
        // int[] dp = new int[n];
        // dp[n-1] = cost[n-1];
        // dp[n-2] = cost[n-2];

        // for(int i=n-3; i >=0; i--){
        // dp[i] = cost[i] + Math.min(dp[i+1], dp[i+2]);
        // }
        // return Math.min(dp[0], dp[1]);

    }

    /**
     * Example 1:
     * 
     * Input: coins = [1,2,5], amount = 11
     * Output: 3
     * Explanation: 11 = 5 + 5 + 1
     * 
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return dp[amount] != amount + 1 ? dp[amount] : -1;

    }

    /**
     * Example 1:
     * 
     * Input: nums = [2,3,-2,4]
     * Output: 6
     * Explanation: [2,3] has the largest product 6.
     * Example 2:
     * 
     * Input: nums = [-2,0,-1]
     * Output: 0
     * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
     * 
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        // time -> O(n)
        // space -> O(1)

        if (nums.length == 1) {
            return nums[0];
        }
        int res = nums[0];
        int min = 1;
        int max = 1;

        for (int i = 0; i < nums.length; i++) {
            int temp = max * nums[i];
            max = Math.max(nums[i], Math.max(temp, min * nums[i]));
            min = Math.min(nums[i], Math.min(temp, min * nums[i]));
            res = Math.max(res, max);
        }
        return res;

    }

}
