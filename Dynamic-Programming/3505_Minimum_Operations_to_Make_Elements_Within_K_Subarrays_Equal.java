import java.util.*;
class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        TreeMap<Integer, Integer> lo = new TreeMap<>(), hi = new TreeMap<>();
        long[] sum = new long[2], cost = new long[n - x + 1];
        int[] size = new int[2];
        for (int i = 0; i < n; i++) {
            int v = nums[i];
            if (size[0] == 0 || v <= lo.lastKey()) {
                lo.merge(v, 1, Integer::sum);
                size[0]++;
                sum[0] += v;
            } else {
                hi.merge(v, 1, Integer::sum);
                size[1]++;
                sum[1] += v;
            }
            if (i >= x) {
                v = nums[i - x];
                if (lo.containsKey(v)) {
                    remove(lo, v);
                    size[0]--;
                    sum[0] -= v;
                } else {
                    remove(hi, v);
                    size[1]--;
                    sum[1] -= v;
                }
            }
            int target = (Math.min(i + 1, x) + 1) / 2;
            while (size[0] > target) {
                v = lo.lastKey();
                remove(lo, v);
                size[0]--;
                sum[0] -= v;
                hi.merge(v, 1, Integer::sum);
                size[1]++;
                sum[1] += v;
            }
            while (size[0] < target) {
                v = hi.firstKey();
                remove(hi, v);
                size[1]--;
                sum[1] -= v;
                lo.merge(v, 1, Integer::sum);
                size[0]++;
                sum[0] += v;
            }
            if (i >= x - 1) {
                long median = lo.lastKey();
                cost[i - x + 1] = median * size[0] - sum[0] + sum[1] - median * size[1];
            }
        }
        long inf = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1], curr = new long[n + 1];
        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, inf);
            for (int i = 1; i <= n; i++) {
                curr[i] = curr[i - 1];
                if (i >= x && prev[i - x] != inf)
                    curr[i] = Math.min(curr[i], prev[i - x] + cost[i - x]);
            }
            long[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prev[n];
    }
    private void remove(TreeMap<Integer, Integer> map, int v) {
        int c = map.get(v);
        if (c == 1) map.remove(v);
        else map.put(v, c - 1);
    }
}
