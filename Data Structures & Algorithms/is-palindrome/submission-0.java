class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();

        // Construct new string to actually verify palindrome upon
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
            } else if (Character.isDigit(c)) {
                sb.append(c);
            }
        }

        // Check palindrome validity
        int leftPointer = 0;
        int rightPointer = sb.length() - 1;
        while (leftPointer < rightPointer) {
            char left = sb.charAt(leftPointer);
            char right = sb.charAt(rightPointer);

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
