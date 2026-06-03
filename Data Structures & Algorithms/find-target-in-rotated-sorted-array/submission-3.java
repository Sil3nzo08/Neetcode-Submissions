class Solution {
    public int search(int[] nums, int target) {
        int lp = 0;
        int rp = nums.length - 1;

        int startingVal = nums[0];
        if (target < startingVal) {
            // In 2nd sorted portion
            while (lp <= rp) {
                int mp = lp + ((rp - lp) / 2);
                //System.out.println("LP: " + lp + " RP: " + rp + " MP: " + mp);
                int elem = nums[mp];
                
                if (elem < target) {
                    lp = mp + 1;
                } else if (elem > target) {
                    if (elem >= startingVal) {
                        lp = mp + 1;
                    } else {
                        rp = mp - 1;
                    }
                } else {
                    return mp;
                }
            }
        } else {
            // In 1st portion
            while (lp <= rp) {
                int mp = lp + ((rp - lp) / 2);
                //System.out.println("LP: " + lp + " RP: " + rp + " MP: " + mp);
                int elem = nums[mp];
                
                if (elem < target) {
                    if (elem < startingVal) {
                        rp = mp - 1;
                    } else {
                        lp = mp + 1;
                    }
                } else if (elem > target) {
                    rp = mp - 1;
                } else {
                    return mp;
                }
            }
        }

        return -1;
    }
}
