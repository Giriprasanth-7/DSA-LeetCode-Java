class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        int[] dp = new int[k];
        for (int num : nums) {
            int rem = num % k;
            int[] next = new int[k];
            next[rem]++;
            for (int r = 0; r < k; r++) {
                int newRem = (r * rem) % k;
                next[newRem] += dp[r];
            }
            for (int r = 0; r < k; r++) {
                result[r] += next[r];
            }
            dp = next;
        }
        return result;
    }
}
