package mylearnings.com.example;

public class BitProblem {
    /**
     * Given an array nums containing n distinct numbers in the range [0, n], return
     * the only number in the range that is missing from the array.

     * Example 1:
     * 
     * Input: nums = [3,0,1]
     * Output: 2
     * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range
     * [0,
     * 
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        // time O(n), space O(1)
        /**
         * int len = nums.length;
         * int res = len; //imp
         * 
         * for(int i =0; i < len; i++){
         * res += (i-nums[i]);
         * }
         * return res;
         */

        // basic approach
        // sum of (0 to n) range - sum of(nums)
        int len = nums.length;
        int expectedSum = 0;
        for (int i = 0; i < len + 1; i++) {
            expectedSum += i;
        }
        int actualSum = 0;
        for (int i = 0; i < len; i++) {
            actualSum += nums[i];
        }
        return expectedSum - actualSum;
    }
}
