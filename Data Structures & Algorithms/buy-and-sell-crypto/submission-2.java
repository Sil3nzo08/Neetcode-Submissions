class Solution {
    public int maxProfit(int[] prices) {
        int lp = 0; // Let's consider this position to be when we bought
        int rp = 0; // Let's consider this position to be when we sell

        // Sliding window
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[rp]) {
                rp = i;
            }

            if (prices[i] < prices[lp]) {
                // Found a cheaper purchase point
                maxProfit = Math.max(maxProfit, prices[rp] - prices[lp]);

                lp = i;
                rp = i;
            }
        }

        maxProfit = Math.max(maxProfit, prices[rp] - prices[lp]);
        return maxProfit;
    }
}
/*
================================
- Time: O(n) 
    > We go through the input array, 'prices', once, using the two-pointer solution above
- Space: O(1)
    > Just some extra space for pointers and storing the current max profit.
================================

Key takeaways:
    - This problem wasn't necessarily sliding window. More of a two-pointer solution, using lp as the buy point, and
      rp as the sell point. What might've been more elegant, and what was done in the video was to set lp, rp = 0, 1 
      respectively, and keep scanning the array with rp, updating lp to it if rp was at a new minimum price. Though, profit
      kept getting calculated for every 'valid' profit, that is, the sell point was higher than the buy point.
*/
