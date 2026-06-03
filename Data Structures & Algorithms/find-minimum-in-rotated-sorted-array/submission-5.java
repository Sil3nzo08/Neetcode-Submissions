class Solution {
    public int findMin(int[] nums) {
        int lp = 0;
        int rp = nums.length - 1;

        int startingVal = nums[0];
        int min = startingVal;
        while (lp <= rp) {
            if (startingVal < nums[rp]) {
                break;
            }

            int mp = lp + ((rp - lp) / 2);

            if (nums[mp] >= startingVal) {
                // Keep going up the sequence, haven't reached the rotated point yet
                lp = mp + 1;
            } else if (nums[mp] < startingVal) {
                // Found the rotated bit, need to backtrack now
                min = Math.min(min, nums[mp]);

                rp = mp - 1;
            }
        }

        return min;
    }
}
/*
================================
- Time: O(log(n)) 
    > Because we are performing binary search on the input array.
- Space: O(1)
    > Just need some extra space for pointers and variables.
================================

Key takeaways:
    - There's an optimization that the video talked about, where the rp
*/
