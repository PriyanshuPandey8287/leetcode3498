class Solution {
    static class Node {
        int prod;
        int[] count;

        Node(int k) {
            this.prod = 1;
            this.count = new int[k];
        }
    }

    private int k;
    private Node[] tree;
    private int n;

    private Node merge(Node left, Node right) {
        Node res = new Node(k);
        res.prod = (left.prod * right.prod) % k;

        for (int r = 0; r < k; r++) {
            res.count[r] += left.count[r];
            int newRem = (left.prod * r) % k;
            res.count[newRem] += right.count[r];
        }
        return res;
    }

    private void build(int node, int l, int r, int[] nums) {
        tree[node] = new Node(k);
        if (l == r) {
            int val = nums[l] % k;
            tree[node].prod = val;
            tree[node].count[val] = 1;
            return;
        }
        int mid = (l + r) >> 1;
        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            int rem = val % k;
            tree[node].prod = rem;
            java.util.Arrays.fill(tree[node].count, 0);
            tree[node].count[rem] = 1;
            return;
        }
        int mid = (l + r) >> 1;
        if (idx <= mid) {
            update(node * 2, l, mid, idx, val);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, val);
        }
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Pair holding accumulated answer and current product
    private int currentProd;
    private int ansCount;

    private void query(int node, int l, int r, int ql, int qr, int targetX) {
        if (ql <= l && r <= qr) {
            for (int rem = 0; rem < k; rem++) {
                if ((currentProd * rem) % k == targetX) {
                    ansCount += tree[node].count[rem];
                }
            }
            currentProd = (currentProd * tree[node].prod) % k;
            return;
        }
        int mid = (l + r) >> 1;
        if (ql <= mid) {
            query(node * 2, l, mid, ql, qr, targetX);
        }
        if (qr > mid) {
            query(node * 2 + 1, mid + 1, r, ql, qr, targetX);
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int q = queries.length;
        int[] result = new int[q];

        for (int i = 0; i < q; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value);

            currentProd = 1;
            ansCount = 0;
            query(1, 0, n - 1, start, n - 1, x);

            result[i] = ansCount;
        }

        return result;
    }
}