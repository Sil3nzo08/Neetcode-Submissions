class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Num --> Index
        Map<Integer, Integer> numbers = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int neededNum = target - nums[i];
            if (numbers.containsKey(neededNum)) {
                return new int[] {numbers.get(neededNum), i};
            } else {
                numbers.put(nums[i], i);
            }
        }

        // Shouldn't reach here
        return null;
    }
}
