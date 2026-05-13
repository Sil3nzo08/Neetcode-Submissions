class Solution {
    public int maxArea(int[] heights) {
        int ans = 0;

        int lp = 0;
        int rp = heights.length - 1;

        while (lp < rp) {
            int heightL = heights[lp];
            int heightR = heights[rp];

            int volume = (rp - lp) * Math.min(heightL, heightR);
            ans = Math.max(ans, volume);

            if (heightR > heightL) {
                lp++;
            } else {
                rp--;
            }
        }

        return ans;
    }
}
/*
================================
- Time: O(n)
    > Every element in the integer array only gets visited once, either by the left pointer or the right
      pointer.
- Space: O(1)
    > Just need some variables for the answer, pointers, left and right heights, and calculating volume.
================================

Key takeways:
    - Two pointer solution is goated here. We start with lp at the very left, and rp at the very right to
      maximize the width dimension of the container. Now, when moving the pointers inwards, you always move
      the one with the shorter height, because moving the one with the taller height doesn't increase the
      height of the container (the smaller of the two always dictates this), and only negatively decreases
      the width. Now imagine the two pointers have the same height, well then it actually doesn't matter
      which one you move towards the middle (sure you could look ahead one element and pick the one with
      the higher height, but in reality, this doesn't matter). To illustrate:
      [8, INFINITY, INFINITY, INFINITY, INFINITY, 8]
       lp                                         rp
      If we move the left pointer, well then the container's width decreases by 1, but the min height here
      is still 8. Similarly, moving the right pointer decreases the width by 1, but the min height here
      is still 8. Order doesn't matter here, as lp at 8 and rp at 8 gives highest volume out of all
      permutations in the middle of these pointers.  
*/
