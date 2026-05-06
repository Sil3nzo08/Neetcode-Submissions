class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < nums.length + 1; i++) {
            buckets.add(new ArrayList<>());
        }

        // Count frequencies
        int[] frequencies = new int[2001];
        for (int num : nums) {
            frequencies[num + 1000]++;
        }

        // Place frequencies into buckets
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] == 0) {
                continue;
            }

            List<Integer> arr = buckets.get(frequencies[i]);
            arr.add(i - 1000);
        }

        System.out.println(buckets.toString());
        // Extract top k from buckets
        int[] ans = new int[k];
        int newIndex = 0;
        for (int i = buckets.size() - 1; i >= 0; i--) {
            List<Integer> arr = buckets.get(i);
            for (int num : arr) {
                ans[newIndex] = num;
                newIndex++;

                if (newIndex == k) {
                    return ans;
                }
            }
        }

        return new int[2];
    }
}
