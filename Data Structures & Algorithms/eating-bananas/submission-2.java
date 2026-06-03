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
                hoursNeeded += (piles[i] + k - 1) / k;
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
/*
================================
- Time: O(mlog(n)), where m = size of 'piles', and n = max value in array
    > We perform binary search on an 'imaginary' array [1..n] (our range for k values), so that's
      where we get log(n). And, for every k we choose in our binary search, we loop over the the 
      piles to calculate the amount of hours needed for koko to consume all bananas with that given
      k, thus giving us mlog(n). 
    > Finding the max value in the pile gives us O(m), but this gets dominated by the O(mlog(n))
- Space: O(1)
    > Extra space for some variables needed for binary searching, storing max value, and storing 
      the best valid k ("ans"). 
================================

Key takeaways:
    - Math.ceil() is actually much slower than just using pure arithmetic. The formula equivalent
      of Math.ceil() is: 
        Math.ceil(a / b) = (a + b - 1) / b
    - It doesn't apply to this question, since I believe the constraints don't cause integer overflow
      to happen (10^9 * 10^6 = 10^15, which falls within the 10^32 range for ints in java), but we 
      should convert "hoursNeeded" from type 'int' to 'long' to handle larger inputs (longs have 
      double the capacity)
    - Left pointer should be initialised to 1 not 0, since division by 0 leads to errors, and also it
      doesn't make sense for koko to be eating 0 bananas. Going to be eating forever...
    - We can use binary search here, because the total hours needed DECREASES always as the eating
      speed INCREASES. So, for a certain k value, if it could not eat all the bananas in the given
      hours limit, then we know ALL smaller k values (slower eating speeds) couldn't possible meet
      the hour limit too, so we can effectively rule them out. Similarly, if we could eat all the
      bananas for a given k, then we don't need to check the higher values for k (they could or 
      could not meet the hour limit, but we don't care due to the objective of the question)
      because the question is asking for the smallest k possible, effectively ruling them out. This
      behaves like binary search, and so we perform binary search on k, with an 'imaginary' array
      [1..m], where m is the max value in the array.
    - m is the upper limit for k, because at k = m, we can already eat all piles in one go, and 
      increasing the amount we can eat doesn't decrease the hours (since we can only eat at ONE pile
      per hour, as stated by the question). 
    - Two ways to recognize binary search is needed: 
        1. You're finding a boundary. e.g:
            k = 1   -> too slow
            k = 2   -> too slow
            k = 3   -> works
            k = 4   -> works
            ...
        2. The function is monotonic. As eating speed increases, hours needed decreases. e.g:
            k = 2   -> 15 hours
            k = 3   -> 11 hours
            k = 4   -> 9 hours
            k = 5   -> 8 hours
            k = 6   -> 7 hours
           So, if k = 5 works, then k = 6, 7, 8, ... must also work. Similarly, if k = 5 doesn't
           work, then k = 1, 2, 3, 4 won't work either. 
*/
