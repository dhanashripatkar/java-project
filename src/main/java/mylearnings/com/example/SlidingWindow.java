package mylearnings.com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SlidingWindow {

    public static void main(String[] args) {
        SlidingWindow slidingWindow = new SlidingWindow();
        int arr[] = { 10, 25, -12, -6, 20, 20 };
        int[] res = slidingWindow.maxSlidingWindow(arr, 2);
    }

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
        System.out.println("count " + count);
        int[] ans = new int[count];
        for (int i = 0; i < count; i++) {
            ans[i] = res[i];
        }
        System.gc();
        return ans;

    }

    // Given a string, find the longest substring having exactly k unique
    // characters.
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

}
