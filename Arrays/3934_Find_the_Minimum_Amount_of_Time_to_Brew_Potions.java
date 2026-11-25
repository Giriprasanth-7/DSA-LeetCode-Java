import java.util.*;

class Solution {
    private static final long MOD1 = 1000000007L;
    private static final long MOD2 = 1000000009L;
    private static final long BASE1 = 911382323L;
    private static final long BASE2 = 972663749L;

    private int[] nums;
    private int n;

    public int smallestUniqueSubarray(int[] nums) {
        this.nums = nums;
        this.n = nums.length;

        int left = 1, right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean check(int len) {
        long pow1 = 1, pow2 = 1;

        for (int i = 1; i < len; i++) {
            pow1 = (pow1 * BASE1) % MOD1;
            pow2 = (pow2 * BASE2) % MOD2;
        }

        long h1 = 0, h2 = 0;

        for (int i = 0; i < len; i++) {
            h1 = (h1 * BASE1 + nums[i]) % MOD1;
            h2 = (h2 * BASE2 + nums[i]) % MOD2;
        }

        HashMap<Long, Integer> freq = new HashMap<>();

        freq.put((h1 << 32) | h2, 1);

        for (int i = len; i < n; i++) {
            h1 = ((h1 - (pow1 * nums[i - len]) % MOD1 + MOD1) * BASE1 + nums[i]) % MOD1;
            h2 = ((h2 - (pow2 * nums[i - len]) % MOD2 + MOD2) * BASE2 + nums[i]) % MOD2;

            long key = (h1 << 32) | h2;
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
