package mylearnings.com.example;

import java.util.PriorityQueue;

public class PriorityQueueHeap {

	// https://leetcode.com/submissions/detail/1057766279/
	public int findKthLargest(int[] nums, int k) {
		int n = nums.length;

		// by sorting
		// Arrays.sort(nums);
		// if(n < k) {
		// return -1;
		// } else {
		// return nums[n-k];
		// }

		// without sorting
		PriorityQueue<Integer> pq = new PriorityQueue<>(k);

		for (int i = 0; i < n; i++) {
			if (pq.size() < k) {
				pq.offer(nums[i]);
			} else if (nums[i] > pq.peek()) {
				pq.poll();
				pq.offer(nums[i]);
			}
		}
		System.gc();
		return pq.peek();
	}

	/**
	 * Input
	 * ["KthLargest", "add", "add", "add", "add", "add"]
	 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
	 * Output
	 * [null, 4, 5, 5, 8, 8]
	 */

	// https://leetcode.com/submissions/detail/1057760828/
	class KthLargest {
		int pqSize;
		PriorityQueue<Integer> queue;

		public KthLargest(int k, int[] nums) {
			this.pqSize = k;
			this.queue = new PriorityQueue<>(k);
			for (int i = 0; i < nums.length; i++) {
				add(nums[i]);
			}
		}

		public int add(int val) {
			if (queue.size() < pqSize) {
				queue.offer(val);
			} else if (val > queue.peek()) {
				queue.poll();
				queue.offer(val);
			}
			return queue.peek();
		}
	}

	/**
	 * 
	 * 
	 * Input: stones = [2,7,4,1,8,1]
	 * Output: 1
	 * Explanation:
	 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
	 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
	 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
	 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the
	 * value of the last stone.
	 * 
	 * @param stones
	 * @return
	 */
	// https://leetcode.com/submissions/detail/1057782844/
	public int lastStoneWeight(int[] stones) {
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		for (int i = 0; i < stones.length; i++) {
			pq.offer(stones[i]);
		}

		while (pq.size() >= 1) {
			if (pq.size() == 1) {
				return pq.peek();
			}
			Integer y = pq.poll();
			Integer x = pq.poll();
			if (x != y) {
				y = y - x;
				pq.offer(y);
			}
		}
		return 0;
	}

	private double getDisctance(int[] point) {
		return Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
	}

	private int[][] kthNearestPoints(int[][] points, int k) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Double.compare(getDisctance(b), getDisctance(a)));
		int[][] res = new int[k][2];

		for (int i = 0; i < points.length; i++) {
			pq.add(points[i]);
			if (pq.size() > k) {
				pq.poll();
			}
		}
		int count = 0;
		while (!pq.isEmpty()) {
			res[count++] = pq.poll();
		}
		return res;
	}
}
