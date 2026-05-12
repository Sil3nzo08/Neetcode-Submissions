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
