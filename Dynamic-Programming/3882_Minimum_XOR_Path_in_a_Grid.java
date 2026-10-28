import java.util.*;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        BitSet[] dp = new BitSet[n];

        for (int j = 0; j < n; j++) {
            dp[j] = new BitSet(1024);
        }

        dp[0].set(grid[0][0]);

        for (int j = 1; j < n; j++) {
            int value = grid[0][j];
            BitSet next = new BitSet(1024);

            for (int x = dp[j - 1].nextSetBit(0);
                 x >= 0;
                 x = dp[j - 1].nextSetBit(x + 1)) {
                next.set(x ^ value);
            }
            dp[j] = next;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int value = grid[i][j];
                BitSet next = new BitSet(1024);

                // Paths from above
                for (int x = dp[j].nextSetBit(0);
                     x >= 0;
                     x = dp[j].nextSetBit(x + 1)) {
                    next.set(x ^ value);
                }

                // Paths from the left
                if (j > 0) {
                    for (int x = dp[j - 1].nextSetBit(0);
                         x >= 0;
                         x = dp[j - 1].nextSetBit(x + 1)) {
                        next.set(x ^ value);
                    }
                }

                dp[j] = next;
            }
        }

        return dp[n - 1].nextSetBit(0);
    }
}
