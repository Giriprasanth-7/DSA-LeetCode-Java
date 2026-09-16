class Solution {
    public int numberOfSets(int n, int k) {
        final long MOD = 1_000_000_007;
        long ans = 1;
        int total = n + k - 1;
        int r = 2 * k;
        for (int i = 1; i <= r; i++) {
            ans = ans * (total - r + i) % MOD;
            ans = ans * modInverse(i, MOD) % MOD;
        }
        return (int) ans;
    }
    private long modInverse(long x, long mod) {
        return power(x, mod - 2, mod);
    }
    private long power(long a, long b, long mod) {
        long result = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = result * a % mod;
            }
            a = a * a % mod;
            b >>= 1;
        }
        return result;
    }
}
