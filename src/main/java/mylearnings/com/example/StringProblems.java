package mylearnings.com.example;

public class StringProblems {

    // https://leetcode.com/problems/string-compression/submissions/1071728222/
    /**
     * Example 1:

    Input: chars = ["a","a","b","b","c","c","c"]
    Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
    Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
        * @param chars
     * @return
     */
    public int compress(char[] chars) {

        int left= 0;
        int right =0;
        int len = chars.length;
       
        while(right < len){
            int count = 1;
            while(right < len-1 && chars[right] == chars[right+1]){
                count++;
                right++;
            }
            chars[left] = chars[right];
            left++;
            if(count > 1){
                char[] digits = String.valueOf(count).toCharArray();
                for(char n : digits){
                    chars[left] = n;
                    left++;
                }
            }
            right++;
        }
        return left;
        
    }
    
}
