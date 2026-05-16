class Solution {
    public int trap(int[] height) {
        int lp = 0;
        int rp = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;

        int totalTrapped = 0;
        while (lp < rp) {
            int heightL = height[lp];
            int heightR = height[rp];

            leftMax = Math.max(leftMax, heightL);
            rightMax = Math.max(rightMax, heightR);

            if (leftMax < rightMax) {
                // Update the weaker height
                leftMax = Math.max(leftMax, heightL);
                totalTrapped += Math.min(leftMax, rightMax) - heightL;

                lp++;
            } else {
                // Update the weaker height
                rightMax = Math.max(rightMax, heightR);
                totalTrapped += Math.min(leftMax, rightMax) - heightR;

                rp--;
            }
        }

        return totalTrapped;
    }
}
/*
================================
- Time: O(n) 
    > Each element in the "height" array only gets visited once, either by the lp, or rp.

- Space: O(1)
    > A couple of variables needed. We need to store the max for the left and right sides, and the two
      pointers, as well as counting the total units of water trapped
================================

Key takeways:
    - This question is very tricky, but the main thing to note is that the amount of water at a given column
      is given by the maximum wall heights to the left and right of it (the minimum of these maximum 
      heights), because no matter how big the second height is, the water is always overflow over the wall
      that is smaller (hence the Math.min(leftMax, rightMax)). Brute force would tell us to recompute this
      max for every position, but a genius way to do it is to have two arrays that will precompute the 
      leftMax and rightMax for each position.
    - But an even more genius solution would be 2 pointer, where we 'lock in' the water trapped at a certain
      column based on the pointer's position. Consider lp at 3rd column and rp at 6th column:
      [1, 3, 3, 51, 2, 3, 4]
             lp        rp
                       |----| RightMax = 4
       |------| LeftMax = 3
      Since leftMax is the smaller of the two maxes, we can 'lock in' the water trapped at lp's column,
      because no matter how high the right max is for this SPECIFIC Position, since left max is 3 (we know
      that for certain since we've explored all left elements of lp), water will always overflow over the
      left wall first, regardless if right had a wall of 40, 80, 120, infinity, you get the idea. Remember,
      water will always overflow first over the smaller of the two maximum walls on the left and right.
*/
