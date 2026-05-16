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
    > Creating hashset and putting in elements is O(n) amortized because of hashing
    > Looping through each num is O(n). The nested while loop is tricky to understand but still ensures each
      element in the hashset is visited once. e.g: 
        {5, 6, 7, 9, 10, 11, 12}
        element 5 gets visited, then check 6 and 7. 
        elements 6 and 7 don't get visited into the while loop since they don't start a consecutive sequence
        element 9 gets visited, but not in while loop as it doesn't start a consecutive sequence
        element 10 gets visited, then checks 11 and 12
        elements 11 and 12 don't get visited into the while loop since they don't start a consecutive sequence
- Space: O(1)
    > Storing the elements into a hashset
================================

Key takeways:
    - Hashset the goat! O(1) amortized for add() and contains() methods.
*/
