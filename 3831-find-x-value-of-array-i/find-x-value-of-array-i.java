class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        // dp[r] stores the number of non-empty subarrays ending at the current index
        // whose elements' product modulo k equals r.
        long[] dp = new long[k];

        for (int val : nums) {
            int currentRem = val % k;
            long[] nextDp = new long[k];

            // 1. A subarray of length 1 consisting solely of the current element:
            nextDp[currentRem]++;

            // 2. Extend each previous subarray ending at the previous index:
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (int) ((1L * r * currentRem) % k);
                    nextDp[newRem] += dp[r];
                }
            }

            // Accumulate into the total counts and move to next state:
            for (int r = 0; r < k; r++) {
                result[r] += nextDp[r];
            }
            dp = nextDp;
        }

        return result;
    }
}