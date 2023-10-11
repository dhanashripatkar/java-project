package mylearnings.com.example;

import java.util.ArrayList;
import java.util.Arrays;

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

}
