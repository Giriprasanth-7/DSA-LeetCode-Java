import java.util.*;
class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        int m = queries.length;
        int[][] q = new int[m][2];
        for (int i = 0; i < m; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }
        Arrays.sort(q, (a, b) -> Integer.compare(a[0], b[0]));
        int[] ans = new int[m];
        int[] count = new int[n + 1];
        int left = 0, right = 0, active = 0;
        for (int[] query : q) {
            int start = query[0] - x;
            int end = query[0];
            while (right < logs.length && logs[right][1] <= end) {
                int id = logs[right++][0];
                if (count[id]++ == 0) active++;
            }
            while (left < right && logs[left][1] < start) {
                int id = logs[left++][0];
                if (--count[id] == 0) active--;
            }
            ans[query[1]] = n - active;
        }
        return ans;
    }
}
