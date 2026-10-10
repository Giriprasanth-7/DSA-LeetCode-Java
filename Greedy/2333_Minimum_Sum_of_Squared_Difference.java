import java.util.*;
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long total = (long) k1 + k2;
        int n = nums1.length;
        int[] diff = new int[n];
        long sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i];
            max = Math.max(max, diff[i]);
        }
        if (total >= sum) return 0;
        int left = 0, right = max;
        while (left < right) {
            int mid = left + (right - left) / 2;
            long needed = 0;
            for (int d : diff) {
                if (d > mid) needed += d - mid;
            }
            if (needed <= total) right = mid;
            else left = mid + 1;
        }
        int level = left;
        long remaining = total;
        long answer = 0;
        for (int d : diff) {
            if (d > level) {
                remaining -= d - level;
                answer += (long) level * level;
            } else {
                answer += (long) d * d;
            }
        }
        for (int d : diff) {
            if (remaining > 0 && d >= level && level > 0) {
                answer -= (long) level * level;
                answer += (long) (level - 1) * (level - 1);
                remaining--;
            }
        }
        return answer;
    }
}
