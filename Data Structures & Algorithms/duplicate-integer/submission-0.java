class Solution {
    public boolean hasDuplicate(int[] nums) {
        Map<Integer, Integer> duplicates = new HashMap<>();

        for (int num : nums) {
            if (duplicates.containsKey(num)) {
                return true;
            } else {
                duplicates.put(num, 1);
            }
        }

        return false;
    }
}