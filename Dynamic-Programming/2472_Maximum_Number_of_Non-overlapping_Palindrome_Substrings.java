class Solution {
    public int maxPalindromes(String s, int k) {
     int n = s.length();
        boolean[][] pal = new boolean[n][n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = (i == 0) ? 0 : dp[i - 1];
            for (int j = i; j >= 0; j--) {
                if (s.charAt(j) == s.charAt(i)
                        && (i - j <= 2 || pal[j + 1][i - 1])) {
                    pal[j][i] = true;
                    if (i - j + 1 >= k) {
                        int count = (j == 0) ? 1 : dp[j - 1] + 1;
                       dp[i] = Math.max(dp[i], count);
                    }
                }
            }
        }
        return dp[n - 1];   
    }
}
