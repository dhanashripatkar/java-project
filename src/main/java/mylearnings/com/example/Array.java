package mylearnings.com.example;

import java.util.ArrayList;

public class Array {

    public static void main(String args[]) {
        Array array = new Array();
        int[] arr = { 1, 2, 3, 4 };
        int[] res = array.productExceptSelf(arr);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];

        // int current=0;
        // while(current < len){
        // int product = 1;
        // for(int i=0; i< len; i++){
        // if(i != current){
        // product = product * nums[i];
        // }
        // }
        // res[current] = product;
        // current++;

        // }
        // return res;

        int prefix = 1;
        for (int i = 0; i < len; i++) {
            res[i] = prefix;
            prefix = prefix * nums[i];
        }
        int postfix = 1;
        for (int i = len - 1; i >= 0; i--) {
            res[i] = res[i] * postfix;
            postfix = postfix * nums[i];
        }
        return res;
    }

    // https://leetcode.com/submissions/detail/1061285280/
    // return the maximum sum of all subarrays
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int res = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (sum < 0) {
                sum = 0;
            }
            sum += nums[i];
            res = Math.max(res, sum);
        }
        return res;
    }

    public int findDuplicate(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int element = nums[i];
            int chair = element - 1;
            if (nums[chair] < 0) {
                return -1 * nums[chair];
            }
            if (element != nums[chair]) {
                int temp = nums[chair];
                nums[chair] = element;
                nums[i] = temp;
            } else {
                nums[i] = -1 * nums[i];
                i++;
            }
        }
        return -1;
    }

    public int[] getMissingAndRepeatingNo(ArrayList<Integer> list) {
        int[] hash = new int[list.size() + 1];

        for (int i = 0; i < list.size(); i++) {
            hash[list.get(i)]++;
        }
        int missing = -1;
        int repeat = -1;

        for (int i = 1; i <= list.size(); i++) {
            if (hash[i] == 2) {
                repeat = i;
            } else if (hash[i] == 0) {
                missing = i;
            }
            if (missing != -1 && repeat != -1) {
                break;
            }
        }
        int[] res = { missing, repeat };
        return res;
    }

    // gas stations
    /**
     * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
     * Output: 3
     * Explanation:
     * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 +
     * 4 = 4
     * Travel to station 4. Your tank = 4 - 1 + 5 = 8
     * Travel to station 0. Your tank = 8 - 2 + 1 = 7
     * Travel to station 1. Your tank = 7 - 3 + 2 = 6
     * Travel to station 2. Your tank = 6 - 4 + 3 = 5
     * Travel to station 3. The cost is 5. Your gas is just enough to travel back to
     * station 3.
     * Therefore, return 3 as the starting index.
     * 
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i];
        }
        int costSum = 0;
        for (int i = 0; i < cost.length; i++) {
            costSum += cost[i];
        }
        // gas sum has to greater than or equal to cost sum
        if (sum < costSum) {
            return -1;
        }
        int res = 0;
        int total = 0;
        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
            if (total < 0) {
                total = 0;
                res = i + 1;
            }
        }
        return res;

    }

}
