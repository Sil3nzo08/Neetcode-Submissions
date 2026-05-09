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
/*
================================
- Time: O(n) amortized
    > Creating hashset and putting in elements is O(n) amortized because of hashing
    > Looping through each num is O(n). The nested while loop is tricky to understand but still ensures each
      element in the hashset is visited once. e.g: 
        {5, 6, 7, 9, 10, 11, 12}
        element 5 gets visited, then check 6 and 7. 
        elements 6 and 7 don't get visited into the while loop since they don't start a consecutive sequence
        element 9 gets visited, but not in while loop as it doesn't start a consecutive sequence
        element 10 gets visited, then checks 11 and 12
        elements 11 and 12 don't get visited into the while loop since they don't start a consecutive sequence
- Space: O(n)
    > Storing the elements into a hashset
================================

Key takeways:
    - Hashset the goat! O(1) amortized for add() and contains() methods.
*/
