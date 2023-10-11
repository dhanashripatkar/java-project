package mylearnings.com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class KFrequentElement {

    class Node {
        int key;
        int value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        KFrequentElement kFrequentElement = new KFrequentElement();
        int arr[] = { 10, 25, -12, -6, 20, 20 };
        int[] res = kFrequentElement.topKFrequent(arr, 2);
    }

    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        PriorityQueue<Node> queue = new PriorityQueue<>(k, (o1, o2) -> Integer.compare(o2.value, o1.value));
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            queue.add(node);
        }
        int count = 0;
        while (!queue.isEmpty() && count < k) {
            Node node = queue.poll();
            System.out.println(node.key);
            res[count] = node.key;
            count++;
        }
        System.gc();
        return res;

    }

}
