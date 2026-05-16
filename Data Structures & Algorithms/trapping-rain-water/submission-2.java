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

            if (heightL < heightR) {
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
