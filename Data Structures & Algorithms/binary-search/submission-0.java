class Solution {
    public int search(int[] nums, int target) {
        int lp = 0;
        int rp = nums.length - 1;

        while (lp <= rp) {
            int mp = lp + ((rp - lp) / 2);
            //System.out.println(mp);

            if (nums[mp] == target) {
                return mp;
            } else if (nums[mp] > target) {
                // Move right pointer down
                rp = mp - 1;
            } else {
                // Move left pointer up
                lp = mp + 1;
            }
        }

        return -1;
    }
}
