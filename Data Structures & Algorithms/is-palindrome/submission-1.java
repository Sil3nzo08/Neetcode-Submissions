class Solution {
    public boolean isPalindrome(String s) {
        // Check palindrome validity
        int leftPointer = 0;
        int rightPointer = s.length() - 1;
        while (leftPointer < rightPointer) {
            char left = Character.toLowerCase(s.charAt(leftPointer));
            char right = Character.toLowerCase(s.charAt(rightPointer));

            if (!Character.isLetter(left) && !Character.isDigit(left)) {
                leftPointer++;
                continue;
            } 
            if (!Character.isLetter(right) && !Character.isDigit(right)) {
                rightPointer--;
                continue;
            } 

            if (left != right) {
                //System.out.println(left + "|" + right);
                return false;
            }

            leftPointer++;
            rightPointer--;
        }

        return true;
    }
}

/*
================================
- Time: O(n)
    > Looping through all elements to construct string
    > Then checking each character in string builded string
    > O(n) + O(n) = O(2n) = O(n)
- Space: O(n)
    > Because of the string builder
================================
*/
