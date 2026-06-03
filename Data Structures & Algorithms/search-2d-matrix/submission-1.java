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
/*
================================
- Time: O(log(m*n)) 
    > I treated the matrix as a typical 1D array, and performed binary search on it using some clever division and modulo
      operations. This 1D array has the size m*n, so binary search makes the time complexity O(log(m*n))
- Space: O(1)
    > Just some extra variables to store pointers, and m and n.
================================

Key takeways:
    - The video solves it a different way. Since we know that the "first integer of every row is greater than the last
      integer of the previous row", it means that subsequent rows will always have bigger numbers and previous rows will
      always have smaller numbers. So, the video performs binary search on the rows first (to find the right row the
      element would be in), and then performs binary search in that row/array Two binary searches occur, once over the
      rows, and once over the 'columns', giving us a time complexity of "log(m) + log(n)", which simplifies to 
      "log(m*n)" due to log laws. 
    - Common decisions where you might need to consider using binary search is:
        > Sorted arrays/matrices
        > Find first/last occurrence
        > Minimum/Maximum satisfying some condition
        > Search space is monotonic
        > Problem is asking for some form of efficiency
        > Looking for a boundary... maybe...
*/
