class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        comb = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1]
                        + comb[i - 1][j]) % MOD;
            }
        }

        return (int) ((dfs(nums, 0, n - 1) - 1 + MOD) % MOD);
    }

    private long dfs(int[] nums, int start, int end) {
        if (start >= end) return 1;

        int root = nums[start];
        int split = start + 1;

        while (split <= end && nums[split] < root) {
            split++;
        }

        int leftCount = 0;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] < root) leftCount++;
        }

        int[] left = new int[leftCount];
        int[] right = new int[end - start - leftCount];

        int l = 0, r = 0;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] < root) {
                left[l++] = nums[i];
            } else {
                right[r++] = nums[i];
            }
        }

        long leftWays = dfs(left, 0, left.length - 1);
        long rightWays = dfs(right, 0, right.length - 1);

        return comb[end - start][leftCount] * leftWays % MOD
                * rightWays % MOD;
    }
}
