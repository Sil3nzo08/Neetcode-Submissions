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
