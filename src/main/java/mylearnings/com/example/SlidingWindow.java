package mylearnings.com.example;

public class SlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int left =0;
        int right = 0;
        int[] res = new int[nums.length];
        int count = 0;
        while(right < nums.length){
            if((right -left +1) == k){
                int max = Integer.MIN_VALUE;
                int len = left + k;
                for(int i=left; i<len; i++){
                    if(nums[i] > max){
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
        for(int i =0; i < count; i++){
            ans[i] = res[i];
        }
        System.gc();
        return ans;

    }
    
}
