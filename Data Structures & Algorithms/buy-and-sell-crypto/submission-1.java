class Solution {
    public int maxProfit(int[] prices) {
        int lp = 0; // Let's consider this position to be when we bought
        int rp = 0; // Let's consider this position to be when we sell

        // Sliding window
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            //System.out.println("BUY: " + lp + " SELL: " + rp);
            if (prices[i] > prices[rp]) {
                rp = i;
            }

            if (prices[i] < prices[lp]) {
                // Found a cheaper purchase point
                //System.out.println("CHANGE PURCHASE POINT TO " + i);
                maxProfit = Math.max(maxProfit, prices[rp] - prices[lp]);

                lp = i;
                rp = i;
            }
        }

        maxProfit = Math.max(maxProfit, prices[rp] - prices[lp]);
        return maxProfit;
    }
}
