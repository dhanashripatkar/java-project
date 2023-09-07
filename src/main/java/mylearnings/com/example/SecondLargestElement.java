package mylearnings.com.example;

import java.util.LinkedList;

public class SecondLargestElement {

    public static int findSecondLargest(int n, int[] arr) {
        if (n == 1) {
            return -1;
        }
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        // find largest one
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > first) {
                first = arr[i];
            }
        }

        // now find second largest one

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > second && arr[i] != first) {
                second = arr[i];
            }
        }

        if (second == Integer.MIN_VALUE) {
            return -1;
        } else {
            System.out.println(second);
            return second;
        }

        // List<Integer> list = new ArrayList<>();
        // for(int num: arr){
        // if(!list.contains(num)){
        // list.add(num);
        // }
        // }

        // Collections.sort(list);
        // //System.out.println(list);
        // int size = list.size();
        // if(size == 1) {
        // return -1;
        // } else {
        // return list.get(size-2);
        // }
    }

    public static void main(String[] args) {
        int arr[] = { 10, 25, -12, -6, 20, 20 };
        findSecondLargest(6, arr);
    }

    void implementLinklist() {
        final LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(10);
        list.add(100);
        list.addLast(789);
        list.remove(1);
    }

}
