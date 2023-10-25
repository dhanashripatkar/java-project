package mylearnings.com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SlidingWindow {

    public static void main(String[] args) {
        SlidingWindow slidingWindow = new SlidingWindow();
        int arr[] = { 10, 25, -12, -6, 20, 20 };
        int price[] = { 7, 1, 5, 3, 6, 4 };
        // int[] res = slidingWindow.maxSlidingWindow(arr, 2);
        int ans = slidingWindow.maxProfit(price);
        System.out.println(ans);
    }

    // Sliding Window Maximum
    /**
     * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
     * Output: [3,3,5,5,6,7]
     * 
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // time limit exceed
        // Time C = O(n.k);
        int left = 0;
        int right = 0;
        int[] res = new int[nums.length];
        int count = 0;
        while (right < nums.length) {
            if ((right - left + 1) == k) {
                int max = Integer.MIN_VALUE;
                int len = left + k;
                for (int i = left; i < len; i++) {
                    if (nums[i] > max) {
                        max = nums[i];
                    }
                }
                res[count] = max;
                count++;
                left++;
            }
            right++;
        }
        // System.out.println("count " + count);
        int[] ans = new int[count];
        for (int i = 0; i < count; i++) {
            ans[i] = res[i];
        }
        // System.gc();
        // return ans;
        // make sure to uncomment return statement
        // =================================================================
        // time com = O(n);
        int n = nums.length;
        int index = 0;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>(); // queue of indexes
        // maintain queue as decreasing one
        // stack -> pop and push from last
        // queue -> add and remove from first
        // deque -> pop push from last + pop push from first

        for (int i = 0; i < n; i++) {
            // 1. check first element is out of bound or not
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.pollFirst();
            }
            // 2. remove all the smaller elements from last // > or >= both are fine
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }

            // 3. insert the index in queue
            deque.offer(i);

            // 4. check if the valid window size if yes then get the first element as its
            // decresing queue
            if (i >= k - 1) {
                result[index++] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    // Given a string, find the longest substring having exactly k unique
    // characters.
    // aabbb -> -1
    static int kUnique(int k, String str) {

        HashMap<Character, List<Integer>> map = new HashMap<>();

        int start = 0;
        int length = Integer.MIN_VALUE;

        for (int i = 0; i < str.length(); i++) {

            Character c = str.charAt(i);

            // this list will maintain index occurance of particular character in string
            List<Integer> list;

            if (map.size() == k) {
                // update the length of string when we have exactly k characters in map
                length = Math.max(length, i - start);
            }

            if (!map.containsKey(c)) {
                list = new ArrayList<>();
                list.add(i);

            } else {
                list = map.get(c);
                list.add(i);
            }
            map.put(c, list);

            while (map.size() > k && start < str.length()) {

                // get list value corresponding to character at start index
                list = map.get(str.charAt(start));

                if (!list.isEmpty()) {
                    // remove 1st element from list
                    list.remove(0);

                    if (list.isEmpty())
                        // if list is empty which means there is 0 occurance of that
                        // character in current window so remove that from the map
                        map.remove(str.charAt(start));

                    else
                        map.put(str.charAt(start), list);

                }

                start++;
            }

        }

        // case where number of unique characters in the entire string are less than k
        if (length == Integer.MIN_VALUE)
            return -1;

        return length + 1;
    }

    // best time to buy and sell
    /**
     * Input: prices = [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit =
     * 6-1 = 5.
     * Note that buying on day 2 and selling on day 1 is not allowed because you
     * must buy before you sell.
     * 
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int left = 0;
        int right = 0;
        int res = 0;

        while (right < prices.length) {
            while (prices[right] - prices[left] < 0) {
                left++;
            }
            res = Math.max(res, prices[right] - prices[left]);
            right++;
        }
        return res;
    }

    // 424. Longest Repeating Character Replacement

    /**
     * Input: s = "ABAB", k = 2
     * Output: 4
     * Explanation: Replace the two 'A's with two 'B's or vice versa.
     * 
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int[] hash = new int[26];
        int left = 0;
        int right = 0;
        int max = 0;
        int res = 0;

        while (right < s.length()) {
            hash[s.charAt(right) - 'A']++;
            max = Math.max(max, hash[s.charAt(right) - 'A']);
            // window - maxFreq <= k => Valid condition
            while (right - left + 1 - max > k) {
                hash[s.charAt(left) - 'A']--;
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;

    }

    /**
     * Given a binary array nums and an integer k, return the maximum number of
     * consecutive 1's in the array if you can flip at most k 0's.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
     * Output: 6
     * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
     * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
     * 
     * @param nums
     * @param k
     * @return
     */

    public int longestOnes(int[] nums, int k) {
        // Time Complexity: O(N)
        // Space Complexity: O(1)
        int left = 0;
        int right = 0;
        int sum = 0;
        int res = 0;
        while (right < nums.length) {
            sum += nums[right];

            while ((right - left + 1) > sum + k) {
                sum -= nums[left];
                left++;
            }
            res = Math.max(res, (right - left + 1));
            right++;
        }
        return res;

    }

    /**
     * Given a binary array nums, you should delete one element from it.
     * 
     * Return the size of the longest non-empty subarray containing only 1's in the
     * resulting array. Return 0 if there is no such subarray.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,1,0,1]
     * Output: 3
     * Explanation: After deleting the number in position 2, [1,1,1] contains 3
     * numbers with value of 1's.
     * 
     * @param nums
     * @return
     */
    public int longestSubarray(int[] nums) {
        // time -> O(n)
        // space -> O(1)
        int left = 0;
        int right = 0;
        int sum = 0;
        int res = 0;

        while (right < nums.length) {
            sum += nums[right];

            while ((right - left + 1) > sum + 1) {
                sum -= nums[left];
                left++;
            }
            res = Math.max(res, sum);
            right++;
        }

        if (sum == nums.length) {
            return sum - 1;
        }
        return res;
    }

    /**
     * Given a string s and an integer k, return the maximum number of vowel letters
     * in any substring of s with length k.
     * 
     * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
     * Example 1:
     * 
     * Input: s = "abciiidef", k = 3
     * Output: 3
     * Explanation: The substring "iii" contains 3 vowel letters.
     * 
     * @param s
     * @param k
     * @return
     */
    public int maxVowels(String s, int k) {
        // time -> O(n)
        // space -> O(1)
        int vowel = 0;
        int maxVowel = 0;
        for (int i = 0; i < k; i++) {
            if (isVowel(s.charAt(i))) {
                vowel++;
            }
        }
        maxVowel = vowel;
        for (int i = k; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) {
                vowel++;
            }
            if (isVowel(s.charAt(i - k))) {
                vowel--;
            }
            maxVowel = Math.max(maxVowel, vowel);
        }
        return maxVowel;

    }

    public boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    /**
     * Given two strings s1 and s2, return true if s2 contains a permutation of s1,
     * or false otherwise.
     * 
     * In other words, return true if one of s1's permutations is the substring of
     * s2.
     * Example 1:
     * 
     * Input: s1 = "ab", s2 = "eidbaooo"
     * Output: true
     * Explanation: s2 contains one permutation of s1 ("ba").
     * 
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        // time -> O(n)
        int n = s1.length();
        int m = s2.length();
        int[] fre = new int[26];
        int[] fre2 = new int[26];

        if (n > m) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            fre[s1.charAt(i) - 'a']++;
        }

        for (int i = 0; i < m; i++) {
            fre2[s2.charAt(i) - 'a']++;
            if (i >= n) {
                fre2[s2.charAt(i - n) - 'a']--;
            }
            if (Arrays.equals(fre, fre2)) {
                return true;
            }
        }
        return false;
    }

}
