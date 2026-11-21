import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }
        int[] newParent = parent.clone();
        int[] last = new int[26];
        Arrays.fill(last, -1);
        dfs(0, adj, s, last, newParent);
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            tree[newParent[i]].add(i);
        }
        int[] ans = new int[n];
        calculate(0, tree, ans);
        return ans;
    }
    private void dfs(int node, List<Integer>[] adj, String s, int[] last, int[] newParent) {
        int c = s.charAt(node) - 'a';
        int old = last[c];
        if (old != -1) {
            newParent[node] = old;
        }
        last[c] = node;
        for (int child : adj[node]) {
            dfs(child, adj, s, last, newParent);
        }
        last[c] = old;
    }
    private int calculate(int node, List<Integer>[] tree, int[] ans) {
        int size = 1;
        for (int child : tree[node]) {
            size += calculate(child, tree, ans);
        }
        ans[node] = size;
        return size;
    }
}
