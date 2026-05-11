class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            int otherIndex = binarySearch(target - num, numbers);

            if (otherIndex == -1) {
                continue;
            } else {
                return new int[] {i + 1, otherIndex + 1};
            }
        }

        // Shouldn't reach here.
        return null;
    }

    private int binarySearch(int target, int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        //System.out.println("Here");

        while (l <= r) {
            int m = (l + r) / 2;
            //System.out.println(l + " " + m + " " + r);

            if (arr[m] == target) {
                return m;
            } else if (arr[m] > target) {
                r = m - 1;
            } else if (arr[m] < target) {
                l = m + 1;
            }
        }

        return -1;
    }
}
