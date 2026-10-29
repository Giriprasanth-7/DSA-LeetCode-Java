import java.util.*;

class Solution {
    int n, log, timer;
    int[] tin, tout, depth, bit, parent;
    int[][] up;
    char[] s;
    List<Integer>[] adj;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.s = s.toCharArray();
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        log = 1;
        while ((1 << log) <= n) log++;

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        up = new int[log][n];
        bit = new int[2 * n + 2];

        buildEuler();

        for (int j = 1; j < log; j++)
            for (int i = 0; i < n; i++)
                up[j][i] = up[j - 1][up[j - 1][i]];

        for (int i = 0; i < n; i++) {
            int mask = 1 << (this.s[i] - 'a');
            update(tin[i], mask);
            update(tout[i], mask);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            int firstSpace = q.indexOf(' ');
            if (q.charAt(0) == 'u') {
                int secondSpace = q.indexOf(' ', firstSpace + 1);
                int u = Integer.parseInt(q.substring(firstSpace + 1, secondSpace));
                char c = q.charAt(secondSpace + 1);

                if (this.s[u] != c) {
                    int mask = (1 << (this.s[u] - 'a')) ^ (1 << (c - 'a'));
                    update(tin[u], mask);
                    update(tout[u], mask);
                    this.s[u] = c;
                }
            } else {
                int secondSpace = q.indexOf(' ', firstSpace + 1);
                int u = Integer.parseInt(q.substring(firstSpace + 1, secondSpace));
                int v = Integer.parseInt(q.substring(secondSpace + 1));
                int l = lca(u, v);

                int mask = prefix(tin[u]) ^ prefix(tin[v])
                         ^ (1 << (this.s[l] - 'a'));

                ans.add((mask & (mask - 1)) == 0);
            }
        }
        return ans;
    }

    void buildEuler() {
        int[] nodes = new int[2 * n];
        int[] parents = new int[2 * n];
        int[] next = new int[2 * n];
        int top = 0;
        nodes[0] = 0;
        parents[0] = 0;
        timer = 0;

        while (top >= 0) {
            int u = nodes[top];

            if (next[top] == 0) {
                parent[u] = parents[top];
                up[0][u] = parents[top];
                tin[u] = ++timer;
            }

            if (next[top] < adj[u].size()) {
                int v = adj[u].get(next[top]++);
                if (v == parents[top]) continue;

                depth[v] = depth[u] + 1;
                nodes[++top] = v;
                parents[top] = u;
                next[top] = 0;
            } else {
                tout[u] = ++timer;
                top--;
            }
        }
    }

    void update(int i, int mask) {
        for (; i < bit.length; i += i & -i) bit[i] ^= mask;
    }

    int prefix(int i) {
        int mask = 0;
        for (; i > 0; i -= i & -i) mask ^= bit[i];
        return mask;
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int t = u; u = v; v = t;
        }

        int diff = depth[u] - depth[v];
        for (int j = 0; j < log; j++)
            if ((diff & (1 << j)) != 0) u = up[j][u];

        if (u == v) return u;

        for (int j = log - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }
        return parent[u];
    }
}
