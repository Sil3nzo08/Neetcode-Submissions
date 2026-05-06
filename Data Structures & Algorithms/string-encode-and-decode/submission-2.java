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
