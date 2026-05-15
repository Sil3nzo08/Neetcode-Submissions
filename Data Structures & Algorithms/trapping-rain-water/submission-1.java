class Solution {
    public int trap(int[] height) {
        int[] prefixMax = new int[height.length];
        int[] suffixMax = new int[height.length];

        int currPreMax = 0;
        int currSufMax = 0;
        for (int i = 0; i < height.length; i++) {
            currPreMax = Math.max(currPreMax, height[i]);
            currSufMax = Math.max(currSufMax, height[height.length - 1 - i]);

            prefixMax[i] = currPreMax;
            suffixMax[height.length - 1 - i] = currSufMax;
        }

        int totalTrapped = 0;
        for (int i = 0; i < height.length; i++) {
            totalTrapped += Math.max(0, Math.min(prefixMax[i], suffixMax[i]) - height[i]);
        }

        return totalTrapped;
    }
}
