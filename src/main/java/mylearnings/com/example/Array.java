package mylearnings.com.example;

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
    // retur the maximum sum of all subarrays
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

}
