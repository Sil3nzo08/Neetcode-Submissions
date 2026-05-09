class Solution {
    public int longestConsecutive(int[] nums) {
        int ans = 0;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int sequenceLength = 1;
        for (int num : set) {
            // Not start of a sequence
            if (set.contains(num - 1)) { continue; }

            // Will start a sequence
            while (true) {
                if (set.contains(num + 1)) {
                    num++;
                    sequenceLength++;
                } else {
                    // Sequence ends here
                    if (sequenceLength > ans) {
                        ans = sequenceLength;
                    }

                    sequenceLength = 1;
                    break;
                }
            }
        }

        return ans;
    }
}
