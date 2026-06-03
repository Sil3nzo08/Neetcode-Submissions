class Solution {
    public int search(int[] nums, int target) {
        int lp = 0;
        int rp = nums.length - 1;

        int startingVal = nums[0];
        if (target < startingVal) {
            // In 2nd sorted portion
            while (lp <= rp) {
                int mp = lp + ((rp - lp) / 2);
                int elem = nums[mp];
                
                if (elem < target) {
                    lp = mp + 1;
                } else if (elem > target) {
                    if (elem >= startingVal) {
                        lp = mp + 1;
                    } else {
                        rp = mp - 1;
                    }
                } else {
                    return mp;
                }
            }
        } else {
            // In 1st portion
            while (lp <= rp) {
                int mp = lp + ((rp - lp) / 2);
                int elem = nums[mp];
                
                if (elem < target) {
                    if (elem < startingVal) {
                        rp = mp - 1;
                    } else {
                        lp = mp + 1;
                    }
                } else if (elem > target) {
                    rp = mp - 1;
                } else {
                    return mp;
                }
            }
        }

        return -1;
    }
}
/*
================================
- Time: O(log(n)) 
    > We run a binary search on the nums array depending on whether the target would sit in the 
      left sorted portion (1st one) or the right sorted portion (2nd one).
- Space: O(1)
    > Just some extra variables for pointers, starting val, etc.
================================

Key takeaways:
    - Probably made it more complicated than it had to be. There are two while loops, but only one
      needs to run, and there's a ton of if statements nested into one another. While totally 
      valid, and all test cases work, I feel like the more elegant solution would be to find the
      pivot, to essentially find the boundaries of the two sub-sorted arrays, then perform another
      binary search in this sub-sorted array. 2 binary searches still lead to O(log(n)). 
*/
