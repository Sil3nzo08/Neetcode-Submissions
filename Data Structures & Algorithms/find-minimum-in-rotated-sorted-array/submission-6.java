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
    - There's an optimization that the video talked about, which was inserted here at lines 9-11.
      You can break the loop once the rp goes past the right pivot. e.g:
      [5, 6, 7, 3, 4]
      lp    rp
      In such cases, we've gone past the pivot, and due to the rotation behaviour, we can tell this
      because the rp is bigger than the starting value 5, in which case we can terminate the 
      binary search early because the rest of the elements inside the [lp, rp] range won't be the
      minimum.
    - You can think of the array as TWO sorted sub-sequences, and you are always trying to get to
      the RIGHT sorted portion (the one with the pivot) since that's where the minimum lives. 
    - You should think to use binary search here because we are trying to find a boundary (the
      pivot) a.k.a finding when the sorted sequence breaks. There's also mentioning of ordering
      here, even if it isn't complete. And always ask yourself "can one comparison tell me which 
      half contains the answer?". 
*/
