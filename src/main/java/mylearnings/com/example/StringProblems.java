package mylearnings.com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StringProblems {

    // https://leetcode.com/problems/string-compression/submissions/1071728222/
    /**
     * Example 1:
     * 
     * Input: chars = ["a","a","b","b","c","c","c"]
     * Output: Return 6, and the first 6 characters of the input array should be:
     * ["a","2","b","2","c","3"]
     * Explanation: The groups are "aa", "bb", and "ccc". This compresses to
     * "a2b2c3".
     * 
     * @param chars
     * @return
     */
    public int compress(char[] chars) {

        int left = 0;
        int right = 0;
        int len = chars.length;

        while (right < len) {
            int count = 1;
            while (right < len - 1 && chars[right] == chars[right + 1]) {
                count++;
                right++;
            }
            chars[left] = chars[right];
            left++;
            if (count > 1) {
                char[] digits = String.valueOf(count).toCharArray();
                for (char n : digits) {
                    chars[left] = n;
                    left++;
                }
            }
            right++;
        }
        return left;

    }

    // greedy
    /**
     * Input: s = "ababcbacadefegdehijhklij"
     * Output: [9,7,8]
     * Explanation:
     * The partition is "ababcbaca", "defegde", "hijhklij".
     * This is a partition so that each letter appears in at most one part.
     * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it
     * splits s into less parts.
     * 
     * @param s
     * @return
     */
    public List<Integer> partitionLabels(String s) {

        final HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
        }

        int end = 0;
        int length = 0;
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, map.get(s.charAt(i)));
            length++;
            if (end == i) {
                ans.add(length);
                length = 0;
            }
        }
        return ans;

        // List<Integer> list = new ArrayList<>();
        // HashMap<Character, Integer> map = new HashMap<>();
        // for (int i = 0; i < s.length(); i++) {
        // map.put(s.charAt(i), i);
        // }
        // int end = 0;
        // int i = 0;
        // while (i < s.length()) {
        // int length = 0;
        // end = Math.max(end, map.get(s.charAt(i)));
        // while (i <= end) {
        // end = Math.max(end, map.get(s.charAt(i)));
        // i++;
        // length++;
        // }
        // list.add(length);
        // }
        // return list;

    }

    /**
     * Given a string s, return the longest
     * palindromic substring in s.
     * Example 1:
     * 
     * Input: s = "babad"
     * Output: "bab"
     * Explanation: "aba" is also a valid answer.
     * 
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int resultLen = 0;
        String res = "";

        for (int i = 0; i < s.length(); i++) {
            // for odd length
            int left = i;
            int right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (resultLen < (right - left + 1)) {
                    resultLen = right - left + 1;
                    res = s.substring(left, right + 1);
                }
                left--;
                right++;
            }

            // for even length
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (resultLen < (right - left + 1)) {
                    resultLen = right - left + 1;
                    res = s.substring(left, right + 1);
                }
                left--;
                right++;
            }
        }
        return res;

    }

    /**
     * Given two strings s and t, return true if t is an anagram of s, and false
     * otherwise.
     * 
     * An Anagram is a word or phrase formed by rearranging the letters of a
     * different word or phrase, typically using all the original letters exactly
     * once.
     * Example 1:
     * 
     * Input: s = "anagram", t = "nagaram"
     * Output: true
     * 
     * Example 2:
     * 
     * Input: s = "rat", t = "car"
     * Output: false
     * 
     * 
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] fre = new int[26];// count of each character

        for (int i = 0; i < s.length(); i++) {
            fre[s.charAt(i) - 'a']++;
            fre[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (fre[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Given an array of strings strs, group the anagrams together.
     * Example 1:
     * 
     * Input: strs = ["eat","tea","tan","ate","nat","bat"]
     * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * 
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // Time Complexity: O(N * K * log(K))
        // Space Complexity: O(N)

        HashMap<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);

            String sordtedWord = new String(chars);
            if (!map.containsKey(sordtedWord)) {
                map.put(sordtedWord, new ArrayList<>());
            }
            map.get(sordtedWord).add(word);
        }
        return new ArrayList<>(map.values());
    }

}
