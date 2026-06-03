class Solution {
    // THE MORE ELEGANT SOLUTION
    public int search(int[] nums, int target) {
        int lp = 0;
        int rp = nums.length - 1;

        int startingVal = nums[0];
        int pivot = 0;
        // Find pivot
        while (lp <= rp) {
            int mp = lp + ((rp - lp) / 2);

            if (nums[mp] >= startingVal) {
                // In the first sorted portion
                lp = mp + 1;
            } else {
                // nums[mp] < startingVal, so we are in 2nd sorted portion, shift down
                if (nums[pivot] > nums[mp]) { pivot = mp; }
                
                rp = mp - 1;
            }
        }

        // Binary search on the sub-sorted array
        lp = 0;
        rp = nums.length - 1;

        if (target >= startingVal && pivot != 0) {
            // target in 1st portion
            rp = pivot - 1;
        } else {
            // target < startingVal, so in 2nd portion
            lp = pivot;
        }

        // Performing the binary search on sub-sorted array now
        while (lp <= rp) {
            int mp = lp + ((rp - lp) / 2);

            if (nums[mp] == target) {
                return mp;
            } else if (nums[mp] > target) {
                rp = mp - 1;
            } else {
                lp = mp + 1;
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
