class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // Find maximum value in the array
        int maxVal = piles[0];
        for (int i = 1; i < piles.length; i++) {
            if (maxVal < piles[i]) {
                maxVal = piles[i];
            }
        }

        // Binary search on k
        int lp = 1;
        int rp = maxVal;
        int ans = 0;
        while (lp <= rp) {
            int k = lp + ((rp - lp) / 2);

            int hoursNeeded = 0;
            for (int i = 0; i < piles.length; i++) {
                hoursNeeded += Math.ceil((double) piles[i] / k);
            }
            //System.out.println("k: " + k + " hours: " + hoursNeeded);

            if (hoursNeeded <= h) {
                if (ans == 0 || ans > k) { ans = k; }
                
                rp = k - 1;
            } else {
                lp = k + 1;
            }
        } 

        return ans;
    }
}
