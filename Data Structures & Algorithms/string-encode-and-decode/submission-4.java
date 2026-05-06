class Solution {

    public String encode(List<String> strs) {
        String ans = "";
        for (String str : strs) {
            ans += str.length() + "#";
            ans += str;
        } 

        //System.out.println(ans);
        return ans;
    }

    public List<String> decode(String str) {
        // Base case:
        if (str == "") {return new ArrayList<>(); }

        // Non-base case
        List<String> ans = new ArrayList<>();
        char[] arr = str.toCharArray();
        int arrIndex = 0;

        while (true) {
            // Get the length of the element to be added
            String lenOfElem = "";
            while (arr[arrIndex] != '#') {
                lenOfElem += arr[arrIndex++];
            }

            // Get actual element
            arrIndex++; // Skip over the "#"
            String elem = "";
            for (int i = 0; i < Integer.valueOf(lenOfElem); i++) {
                elem += arr[arrIndex++];
            }

            // Add element to answer
            ans.add(elem);
            //System.out.println(lenOfElem + "|" + elem);

            if (arrIndex == arr.length) {
                break;
            }
        }
        
        // Should never reach this really.
        return ans;
    }
}

/*
Should use a StringBuilder for better times in Java. Works better than straight up 
concatenation.

Also, love the idea here, because it always works. Even if the message was purely just
numbers + "#". E.g:
With list to encode: "[123#234, #41232, 34##233, 32#332, 1578#]"
We have: "6#123#2346##412327#34##23332#6#33214#578#"
Because we put the instruction AT THE FRONT, we don't actually care about the body. For
here, we have "6#", which is always our instruction because we put it at the front of the
actual string, so then we scan the next 6 characters, we don't care what they are, and
then we are met with 6#. How do we know this is our instruction? Because of the scanning
pattern, those 6 previous characters were part of the string, but these 2 are not, otherwise
the first instruction would've been 8#, not 6#. In other words, after every scan/instruction,
we always end up at another instruction. Clever isn't it.
*/
