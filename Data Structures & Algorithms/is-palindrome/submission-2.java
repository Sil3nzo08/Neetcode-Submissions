class Solution {
    public boolean isPalindrome(String s) {
        // Check palindrome validity
        int leftPointer = 0;
        int rightPointer = s.length() - 1;
        while (leftPointer < rightPointer) {
            char left = Character.toLowerCase(s.charAt(leftPointer));
            char right = Character.toLowerCase(s.charAt(rightPointer));

            // Keep shifting up, if we don't have an alphanumeric character
            if (!Character.isLetter(left) && !Character.isDigit(left)) {
                leftPointer++;
                continue;
            } 
            // Keep shifting down, if we don't have an alphanumeric character
            if (!Character.isLetter(right) && !Character.isDigit(right)) {
                rightPointer--;
                continue;
            } 

            // Character check to see if same for palindrome
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
    > Looping through all elements in string
- Space: O(1)
    > Have a couple of integer and character variables.
    > Number of space isn't growing as input grows
================================
*/
