package mylearnings.com.example;

import java.util.ArrayList;
import java.util.HashMap;
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
            System.out.println(ans[i]);
        }
        System.gc();
        return ans;

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

}
