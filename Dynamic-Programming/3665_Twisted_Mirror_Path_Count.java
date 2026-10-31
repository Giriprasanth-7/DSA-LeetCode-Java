class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long MOD = 1_000_000_007L;

        long[] dp = new long[n + 1];
        dp[1] = 1;

        for (int i = 0; i < m; i++) {
            long left = 0;

            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    long temp = left;
                    left = dp[j + 1];
                    dp[j + 1] = temp;
                } else {
                    long ways = (left + dp[j + 1]) % MOD;
                    left = ways;
                    dp[j + 1] = ways;
                }
            }
        }

        return (int) dp[n];
    }
}
