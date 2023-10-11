package mylearnings.com.example;

class JumpGame {

    public static void main(String args[]) {
        JumpGame jumpGame = new JumpGame();
        int[] arr = { 3, 1, 2, 1, 7 };
        int result = jumpGame.isValidGame(arr);
        System.out.println("result : " + result);

    }
 
    // https://leetcode.com/problems/jump-game/submissions/1061612989/
    public int isValidGame(int[] arr) {
        int current = 0;
        int max = 0;

        while (current < arr.length) {
            // current hits max = cant possible
            if (current > max) {
                return 0;
            }
            max = Math.max(max, current + arr[current]);
            current++;
        }

        return 1;
    }

    // jump game 2
    // minimum jump to reach at n -1 length
    // https://leetcode.com/problems/jump-game-ii/submissions/1061608286/
    public int jump(int[] nums) {
        int right =0;
        int count= 0;
        int left =0;
        while(right < nums.length - 1){
            int sum =0;
            for(int i = left; i<= right ; i++){
                sum = Math.max(sum, i + nums[i]);
            }
            left = right+1;
            right = sum;
            count++;
        }

        return count;
        
    }

}
