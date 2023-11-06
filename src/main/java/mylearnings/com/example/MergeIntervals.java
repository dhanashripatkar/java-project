package mylearnings.com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public static void main(String args[]) {
        MergeIntervals mergeIntervals = new MergeIntervals();
        int[][] input = { { 0, 1 }, { 3, 5 }, { 4, 8 }, { 9, 10 }, { 10, 12 } };
        ArrayList<int[]> res = mergeIntervals.mergeMeetings(input);
        for (int j = 0; j < res.size(); j++) {
            int[] ans = res.get(j);
            System.out.println(ans[0] + " " + ans[1]);
        }
    }

    public ArrayList<int[]> mergeMeetings(int[][] meetings) {
        ArrayList<int[]> ans = new ArrayList<>();
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        ans.add(meetings[0]);

        for (int i = 1; i < meetings.length; i++) {
            int currentStart = meetings[i][0];
            // check if current elements's starttime is lesser than previous endtime
            if (currentStart <= ans.get(ans.size() - 1)[1]) {
                ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], meetings[i][1]);
            } else {
                ans.add(meetings[i]);
            }
        }

        return ans;
    }

    /**
     * Given an array of intervals intervals where intervals[i] = [starti, endi],
     * return the minimum number of intervals you need to remove to make the rest of
     * the intervals non-overlapping.
     * 
     * Example 1:
     * 
     * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
     * Output: 1
     * Explanation: [1,3] can be removed and the rest of the intervals are
     * non-overlapping.
     * 
     * @param intervals
     * @return
     */

    public int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);
        int count = 0;

        for (int i = 1; i < intervals.length; i++) {
            int current = intervals[i][0];
            if (current < list.get(list.size() - 1)[1]) {
                count++;
                if (list.get(list.size() - 1)[1] > intervals[i][1]) {
                    list.get(list.size() - 1)[1] = intervals[i][1];
                }
            } else {
                list.add(intervals[i]);
            }
        }
        System.gc();
        return count;
    }

    /**
     * nsert newInterval into intervals such that intervals is still sorted in
     * ascending order by starti and intervals still does not have any overlapping
     * intervals (merge overlapping intervals if necessary).
     * 
     * Return intervals after the insertion.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
     * Output: [[1,5],[6,9]]
     * 
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();

        for (int[] interval : intervals) {
            if (null == newInterval || interval[1] < newInterval[0]) {
                // after
                res.add(interval);
            } else if (interval[0] > newInterval[1]) {
                // before
                res.add(newInterval);
                res.add(interval);
                newInterval = null;
            } else {
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
        }
        if (newInterval != null) {
            res.add(newInterval);
        }
        return res.toArray(new int[res.size()][]);
    }

}
