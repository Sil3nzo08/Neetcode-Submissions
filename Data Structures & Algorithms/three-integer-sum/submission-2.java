class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int num_i = nums[i];

            int lp = i + 1;
            int rp = nums.length - 1;
            int target = -num_i;

            while (lp < rp) {
                int num_j = nums[lp];
                int num_k = nums[rp];
                int sum = num_j + num_k;

                if (sum == target && lp != i && rp != i) {
                    ans.add(List.of(num_i, num_j, num_k));

                    while (lp < rp && nums[lp] == num_j) {
                        lp++;
                    }

                    while (rp > lp && nums[rp] == num_k) {
                        rp--;
                    }

                } else if (sum < target) {
                    while (lp < rp && nums[lp] == num_j) {
                        lp++;
                    }
                } else if (sum > target) {
                    while (rp > lp && nums[rp] == num_k) {
                        rp--;
                    }
                }
            }

            while (i < nums.length && nums[i] == num_i) {
                i++;
            }
            i--;
        }

        return ans;
    }
}

/*
================================
- Time: O(n^2)
    > Sorting the integer array is O(nlog(n))
    > Outer loop (with index i) is O(n), then the two pointer algorithm inside that is
      computed for every i is O(n), which compoundingly gives us O(n^2).
    > Overall, the O(n^2) dominates over O(nlog(n)), so overall time is O(n^2)
- Space: O(1) extra space
    > Well, excluding the obvious List<List<Integer>> needed to construct the answer, we
      only need some variables for pointers and to hold targets and sum.
================================

Key takeways:
    - Looks like java algorithm for sorting gives us O(nlog(n))
    - While loops the goat for skipping over duplicates!
    - Sorting is needed to easily skip over duplicates (keep moving
      index until we find a new element since duplicates will be
      neighbours in sorted arrays)
    - Turns out don't need all while loops I have here:
        - You only need one, for the case where we find a threeSum! 
          In the cases where the threeSum is < target, rp-- and where
          threeSum > target, lp++, then in the case threeSum == target
          just have ONE while loop, either incrementing lp until new
          distinct val in array occurs, or decrementing rp in same 
          fashion. This is because, if we change one of the pointers
          so that the element is a different pointer, then threeSum
          is no longer == target (> target if we update lp), then the
          threeSum > target if statement case will automatically handle
          rp duplicates through repeatedly rp-- it. e.g:
          [-2, -2, 0, 0, 2, 2]
           lp               rp
          [-2, -2, 0, 0, 2, 2]
                   lp       rp  (lp moves due to while loop!)
          [-2, -2, 0, 0, 2, 2]
                   lp    rp 
          [-2, -2, 0, 0, 2, 2]
                   lp rp
*/
