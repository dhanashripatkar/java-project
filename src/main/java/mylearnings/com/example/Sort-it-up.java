package mylearnings.com.example;

class Sort {

    public static void main(String args[]) {
        Sort sort = new Sort();
        int[] arr = { 1, 1, 2, 0, 1, 2 };
        arr = sort.sortWithN(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public int[] sortWithN(int[] arr) {

        // arr consist of 0, 1, 2 only
        int length = arr.length;
        int left = 0;
        int right = length - 1;
        int current = 0;

        while (current <= right) {
            if (arr[current] == 2) {
                arr[current] = arr[right];
                arr[right] = 2;
                right--;
            } else if (arr[current] == 1) {
                current++;
            } else if (arr[current] == 0) {
                arr[current] = arr[left];
                arr[left] = 0;
                current++;
                left++;
              
            }
        }

        return arr;
    }

}
