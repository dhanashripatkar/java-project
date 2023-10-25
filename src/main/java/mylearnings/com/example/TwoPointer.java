package mylearnings.com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TwoPointer {

    public static void main(String args[]) {
        TwoPointer twoPointer = new TwoPointer();
        int[] arr = { 1000000000, 1000000000, 1000000000, 1000000000 };
        int[] nums = { 1, 0, -1, 0, -2, 2 };
        List<List<Integer>> res = twoPointer.fourSum(nums, 0);
        System.out.println(res);

    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < len - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int left = i + 1;
                int right = len - 1;
                int target = 0 - nums[i];
                while (left < right) {
                    if ((nums[left] + nums[right]) == target) {
                        List<Integer> sol = new ArrayList<>();
                        sol.add(nums[left]);
                        sol.add(nums[right]);
                        sol.add(nums[i]);
                        ans.add(sol);
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }

        }
        return ans;
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> prevMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int diff = target - num;

            if (prevMap.containsKey(diff)) {
                return new int[] { prevMap.get(diff), i };
            }

            prevMap.put(num, i);
        }

        return new int[] {};
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        // time complexity = O(n^3)
        // this is valid for any k sum problem
        Arrays.sort(nums);
        List<Integer> quad = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        ksum(4, 0, target, nums, quad, res);
        return res;
    }

    public void ksum(int k, int start, long targetb, int[] nums, List<Integer> quad, List<List<Integer>> result) {
        if (k != 2) {
            for (int i = start; i < nums.length - k + 1; i++) {
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }
                quad.add(nums[i]);
                ksum(k - 1, i + 1, (long) (targetb - nums[i]), nums, quad, result);
                quad.remove(quad.size() - 1);
            }
            return;
        }
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            long sum = (long) (nums[left] + nums[right]);
            if (sum > targetb) {
                right--;
            } else if (sum < targetb) {
                left++;
            } else {
                quad.add(nums[left]);
                quad.add(nums[right]);
                result.add(new ArrayList<>(quad));
                quad.remove(quad.size() - 1);
                quad.remove(quad.size() - 1);

                left++;
                right--;
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
            }
        }
    }

    /**
     * Example 1:
     * 
     * Input: s = "A man, a plan, a canal: Panama"
     * Output: true
     * Explanation: "amanaplanacanalpanama" is a palindrome.
     * 
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left <= right) {
            Character start = s.charAt(left);
            Character end = s.charAt(right);
            if (!Character.isLetterOrDigit(start)) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(end)) {
                right--;
                continue;
            }
            if (Character.toLowerCase(start) != Character.toLowerCase(end)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    /**
     * container with most water
     * Input: height = [1,8,6,2,5,4,8,3,7]
     * Output: 49
     * Explanation: The above vertical lines are represented by array
     * [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the
     * container can contain is 49.
     * 
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int res = 0;
        int area = 0;

        int left = 0;
        int right = height.length - 1;

        while (left <= right) {
            area = Math.max(area, (right - left) * Math.min(height[left], height[right]));
            if (height[right] < height[left]) {
                right--;
            } else {
                left++;
            }
        }
        return area;

    }

    /**
     * Trapping Rain Water
     * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * Output: 6
     * Explanation: The above elevation map (black section) is represented by array
     * [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section)
     * are being trapped.
     * 
     * @param height
     * @return
     */
    public int trap(int[] height) {
        // time -> O(n);
        // space -> O(1);
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];
        int res = 0;

        while (left < right) {
            // imp first increment or decrement left and right then add to result
            if (leftMax < rightMax) {
                left++;
                leftMax = Math.max(leftMax, height[left]);
                res += leftMax - height[left]; // it wont be -ve as we have stored max value in above line already
            } else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
                res += rightMax - height[right];// it wont be -ve as we have stored max value in above line already
            }
        }
        return res;

    }

    // with time O(n) but O(1) space

    public int trap2(int[] heights) {
        int left[] = new int[heights.length], right[] = new int[heights.length], max =
            heights[0], c = 0;

        for (int i = 0; i < heights.length; i++) {
            left[i] = Math.max(heights[i], max);
            max = left[i];
        }

        max = heights[heights.length - 1];
        for (int i = heights.length - 1; i >= 0; i--) {
            right[i] = Math.max(heights[i], max);
            max = right[i];
        }

        for (int i = 0; i < heights.length; i++) {
            c = c + Math.min(left[i], right[i]) - heights[i];
        }
        return c;
    }

}
