class Solution {
    class Node {
        int product;
        int[] cnt;
        Node(int size) {
            cnt = new int[size];
        }
    }
    int n, k;
    int[] nums;
    Node[] tree;
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.nums = nums;
        this.k = k;
        n = nums.length;
        tree = new Node[4 * n];
        build(1, 0, n - 1);
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
            nums[index] = value;
            update(1, 0, n - 1, index, value);
            Node node = query(1, 0, n - 1, start, n - 1);
            result[i] = node.cnt[x];
        }
        return result;
    }
    void build(int node, int left, int right) {
        if (left == right) {
            tree[node] = new Node(k);
            int rem = nums[left] % k;
            tree[node].product = rem;
            tree[node].cnt[rem] = 1;
            return;
        }
        int mid = left + (right - left) / 2;
        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }
    void update(int node, int left, int right, int index, int value) {
        if (left == right) {
            tree[node] = new Node(k);
            int rem = value % k;
            tree[node].product = rem;
            tree[node].cnt[rem] = 1;
            return;
        }
        int mid = left + (right - left) / 2;
        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }
    Node query(int node, int left, int right, int ql, int qr) {
        if (ql <= left && right <= qr) {
            return tree[node];
        }
        int mid = left + (right - left) / 2;
        if (qr <= mid) {
            return query(node * 2, left, mid, ql, qr);
        }
        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, right, ql, qr);
        }
        Node leftNode = query(node * 2, left, mid, ql, qr);
        Node rightNode = query(node * 2 + 1, mid + 1, right, ql, qr);
        return merge(leftNode, rightNode);
    }
    Node merge(Node leftNode, Node rightNode) {
        Node result = new Node(k);
        result.product = (leftNode.product * rightNode.product) % k;
        for (int r = 0; r < k; r++) {
            result.cnt[r] += leftNode.cnt[r];
        }
        for (int r = 0; r < k; r++) {
            int newRemainder = (leftNode.product * r) % k;
            result.cnt[newRemainder] += rightNode.cnt[r];
        }
        return result;
    }
}
