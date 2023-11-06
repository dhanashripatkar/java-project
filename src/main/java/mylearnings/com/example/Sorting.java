package mylearnings.com.example;

public class Sorting {
    public static void main(String args[]) {
        Sorting sorting = new Sorting();
        int[] arr = { 4, 8, 2, 9, 1, 3 };
        sorting.mergeSort(arr);

        // print
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public void mergeSort(int[] nums) {
        devide(nums, 0, nums.length - 1);
    }

    public void devide(int[] nums, int start, int end) {
        if (start >= end) { // imp >= 
            return;
        }
        int mid = start + (end - start) / 2;
        devide(nums, start, mid);
        devide(nums, mid + 1, end);
        concor(nums, start, end, mid);
    }

    public void concor(int[] arr, int start, int end, int mid) {
        int[] merge = new int[end - start + 1];// imp

        int index1 = start;
        int index2 = mid + 1;
        int x = 0;

        // imp condition
        while (index1 <= mid && index2 <= end) {
            if (arr[index1] <= arr[index2]) {
                merge[x++] = arr[index1++];
            } else {
                merge[x++] = arr[index2++];
            }
        }

        while (index1 <= mid) {
            merge[x++] = arr[index1++];
        }

        while (index2 <= end) {
            merge[x++] = arr[index2++];
        }

        for (int i = 0, j = start; i < merge.length; i++, j++) {
            arr[j] = merge[i]; // imp
        }

    }
}
