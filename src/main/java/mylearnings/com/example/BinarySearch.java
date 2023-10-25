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
        int r = piles[piles.length - 1];

        while (l < r) {
            int k = l + (r - l) / 2;
            int hours = 0;
            for (int i = 0; i < piles.length; i++) {
                if (k != 0) {
                    if (piles[i] % k != 0) {
                        hours += (piles[i] / k) + 1;
                    } else {
                        hours += (piles[i] / k);
                    }
                }
            }

            if (hours <= h) {
                r = k;
            } else {
                l = k + 1;
            }

        }

        return r;

    }

    // https://leetcode.com/problems/find-peak-element/submissions/1072482164/?envType=study-plan-v2&envId=leetcode-75
    /**
     * Example 1:
     * A peak element is an element that is strictly greater than its neighbors.
     * 
     * Input: nums = [1,2,3,1]
     * Output: 2
     * Explanation: 3 is a peak element and your function should return the index
     * number 2.
     * 
     * @param nums
     * @return
     */

    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                right = mid - 1;
            } else if (mid < nums.length - 1 && nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return 0;

        // // with O(n)
        // boolean found = false;
        // for(int i=1; i < nums.length-1; i++){

        // if(nums[i] > nums[i+1] && nums[i] > nums[i-1]){
        // found = true;
        // return i;
        // }
        // }
        // if(!found && nums.length > 1){
        // if(nums[0] > nums[1]){
        // return 0;
        // }else if(nums[nums.length-1] > nums[nums.length-2]){
        // return nums.length-1;
        // }
        // }
        // return 0;

    }

    /**
     * Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
     * Output: [4,0,3]
     * Explanation:
     * - 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
     * - 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
     * - 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
     * Thus, [4,0,3] is returned.
     * 
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int len1 = spells.length;
        int len2 = potions.length;
        Arrays.sort(potions);
        int[] res = new int[len1];
        for (int i = 0; i < len1; i++) {
            int left = 0;
            int right = len2 - 1;
            int count = len2;
            while (left <= right) {
                int mid = (left + right) / 2;
                Long prod = (long) spells[i] * potions[mid];
                if (prod >= success || prod < 0) {
                    count = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            res[i] = len2 - count;
        }
        return res;

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // time com -> O(n+m)
        // space com -> O(1)
        // suppose m is of short length
        int m = nums1.length;
        int n = nums2.length;

        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0;
        int right = m;
        int total = m + n;
        int half = (total + 1) / 2; // imp
        double result = 0.0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int j = half - mid; // imp

            int left1 = (mid > 0) ? nums1[mid - 1] : Integer.MIN_VALUE;
            int right1 = (mid < m) ? nums1[mid] : Integer.MAX_VALUE;
            int left2 = (j > 0) ? nums2[j - 1] : Integer.MIN_VALUE;
            int right2 = (j < n) ? nums2[j] : Integer.MAX_VALUE;

            // correct condition
            if (left1 <= right2 && left2 <= right1) {
                // odd
                if (total % 2 == 1) {
                    result = Math.max(left1, left2);
                } else {
                    // even
                    result = (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                }
                break;
            } else if (left1 > right2) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }
        return result;

    }

    /**
     * You are given an m x n integer matrix matrix with the following two
     * properties:
     * 
     * Each row is sorted in non-decreasing order.
     * The first integer of each row is greater than the last integer of the
     * previous row.
     * Given an integer target, return true if target is in matrix or false
     * otherwise.
     * 
     * You must write a solution in O(log(m * n)) time complexity.
     * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
     * Output: true
     * 
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // time -> O(n)
        // space -> O(1)
        int rows = matrix.length;
        int columns = matrix[0].length;
        int low = 0;
        int high = rows * columns - 1;

        while (low <= high) {
            int mid = (low + high) / 2; // normal as matrix max size is 100
            if (matrix[mid / columns][mid % columns] == target) {
                return true;
            } else if (matrix[mid / columns][mid % columns] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}
