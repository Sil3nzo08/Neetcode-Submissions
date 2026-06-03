class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 3 x 5 matrix has 3 rows and 5 columns
        int m = matrix.length;
        int n = matrix[0].length;

        // Just treat the 2D matrix as if it was just a normal 1D array
        int lp = 0;
        int rp = m*n - 1;

        while (lp <= rp) {
            int mp = lp + ((rp - lp) / 2);
            int elem = matrix[mp / n][mp % n];

            if (elem == target) {
                return true;
            } else if (elem > target) {
                rp = mp - 1;
            } else {
                lp = mp + 1;
            }
        }

        return false;
    }
}
