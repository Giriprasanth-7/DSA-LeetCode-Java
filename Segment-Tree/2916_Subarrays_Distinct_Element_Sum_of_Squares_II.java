class Solution {
    static final long MOD = 1_000_000_007L;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        long[] sum = new long[4 * n];
        long[] sq = new long[4 * n];
        long[] lazy = new long[4 * n];
        int[] last = new int[100001];

        java.util.Arrays.fill(last, -1);
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            update(1, 0, n - 1, last[x] + 1, i, sum, sq, lazy);
            ans = (ans + sq[1]) % MOD;
            last[x] = i;
        }

        return (int) ans;
    }

    private void update(int node, int l, int r, int ql, int qr,
                        long[] sum, long[] sq, long[] lazy) {
        if (qr < l || r < ql) return;

        if (ql <= l && r <= qr) {
            apply(node, l, r, 1, sum, sq, lazy);
            return;
        }

        push(node, l, r, sum, sq, lazy);

        int mid = (l + r) >>> 1;
        update(node * 2, l, mid, ql, qr, sum, sq, lazy);
        update(node * 2 + 1, mid + 1, r, ql, qr, sum, sq, lazy);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    private void apply(int node, int l, int r, long v,
                       long[] sum, long[] sq, long[] lazy) {
        long len = r - l + 1L;
        sq[node] = (sq[node] + 2 * v % MOD * sum[node] % MOD
                + v * v % MOD * len % MOD) % MOD;
        sum[node] = (sum[node] + v * len) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }

    private void push(int node, int l, int r,
                      long[] sum, long[] sq, long[] lazy) {
        if (lazy[node] == 0 || l == r) return;

        int mid = (l + r) >>> 1;
        apply(node * 2, l, mid, lazy[node], sum, sq, lazy);
        apply(node * 2 + 1, mid + 1, r, lazy[node], sum, sq, lazy);
        lazy[node] = 0;
    }
}
