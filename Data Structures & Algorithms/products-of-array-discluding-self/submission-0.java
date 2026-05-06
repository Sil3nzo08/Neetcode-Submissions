class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        int[] suffix = new int[nums.length];
        int[] prefix = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int prefixNum = nums[i];
            int suffixNum = nums[nums.length - 1 - i];

            if (i == 0) {
                prefix[i] = prefixNum;
                suffix[nums.length - 1 - i] = suffixNum;
            } else {
                prefix[i] = prefix[i - 1] * prefixNum;
                suffix[nums.length - 1 - i] = suffix[nums.length - i] * suffixNum;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                ans[i] = suffix[i + 1];
            } else if (i == nums.length - 1) {
                ans[i] = prefix[i - 1];
            } else {
                ans[i] = prefix[i - 1] * suffix[i + 1];
            }            
        }

        //System.out.println(Arrays.toString(prefix) + " " + Arrays.toString(suffix));

        return ans;
    }
}  
