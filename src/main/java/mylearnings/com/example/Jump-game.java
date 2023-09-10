package mylearnings.com.example;

class JumpGame {

    public static void main(String args[]) {
        JumpGame jumpGame = new JumpGame();
        int[] arr = { 3, 1, 2, 1, 7 };
        int result = jumpGame.isValidGame(arr);
        System.out.println("result : " + result);

    }

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

}
