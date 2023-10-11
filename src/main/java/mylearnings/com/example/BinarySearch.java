package mylearnings.com.example;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String args[]) {
        BinarySearch binarySearch = new BinarySearch();
        int[] arr = { 4, 5, 6, 7, 1, 2, 3 };
        int target = 3;
        int ans = binarySearch.search(arr, target);

        int ans2 = binarySearch.findMin(arr);

        System.out.println(ans + "       " + ans2);

    }

    // find target in rotated sorted array
    // https://leetcode.com/submissions/detail/1045325613/
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] <= nums[mid]) {
                if (target > nums[mid] || target < nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target < nums[mid] || target > nums[right]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            }
        }
        return -1;
    }

    // find min in rotated sorted array
    // https://leetcode.com/submissions/detail/1044976704/
    public int findMin(int[] nums) {

        int first = 0;
        int last = nums.length - 1;
        int res = nums[0];

        while (first <= last) {
            // this is where the array is already sorted
            if (nums[first] <= nums[last]) {
                res = Math.min(res, nums[first]);
                break;
            }

            int mid = first + (last - first) / 2;
            if (nums[mid] >= nums[first]) {
                res = Math.min(res, nums[mid]);
                first = mid + 1;
            } else {
                last = mid;
            }
        }

        return res;
    }

    // koko eating banans
    // https://leetcode.com/submissions/detail/1044875810/
    public int minEatingSpeed(int[] piles, int h) {

        // k will lies in [1 2 3 ..... max[piles]]

        Arrays.sort(piles);
        int l = 1;
        int r = piles[piles.length -1];

        while(l < r) {
            int k = l + (r-l)/2;
            int hours = 0;
            for (int i= 0; i < piles.length ; i++){
                if(k != 0){
                if(piles[i] % k != 0){
                    hours += (piles[i]/k) + 1;
                 } else { 
                     hours += (piles[i]/k);
                  }
                } 
            }
            
            if(hours <= h){
                r = k;
            } else {
                l = k+1;
            }
            
        }

        return r;
        
    }
// https://leetcode.com/problems/find-peak-element/submissions/1072482164/?envType=study-plan-v2&envId=leetcode-75
    /**
     * Example 1:
     * A peak element is an element that is strictly greater than its neighbors.

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
     * @param nums
     * @return
     */

    public int findPeakElement(int[] nums) {
        int left =0; int right = nums.length-1;

        while(left <= right){
            int mid = left + (right-left)/2;
            if(mid >0 && nums[mid] < nums[mid-1]){
                right = mid-1;
            } else if(mid < nums.length -1 && nums[mid] < nums[mid+1]){
                left = mid+1;
            } else {
                return mid;
            }
        }
        return 0;

        // // with O(n)
        // boolean found = false;
        // for(int i=1; i < nums.length-1; i++){
        
        //     if(nums[i] > nums[i+1] && nums[i] > nums[i-1]){
        //         found = true;
        //         return i;
        //     }
        // }
        // if(!found && nums.length > 1){
        //     if(nums[0] > nums[1]){
        //         return 0;
        //     }else if(nums[nums.length-1] > nums[nums.length-2]){
        //         return nums.length-1;
        //     }
        // }
        // return 0;
        
    }

}
