class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) { return false; }

        // Construct character frequency in s1
        int[] s1CharFreq = new int[26];
        int[] s2CharFreq = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1CharFreq[s1.charAt(i) - 'a'] += 1;
            s2CharFreq[s2.charAt(i) - 'a'] += 1; // Populate s2 as well to same length
        }

        // Check for premutation equality
        if (Arrays.equals(s1CharFreq, s2CharFreq)) {
            return true;
        }

        // Sliding window (fixed size)
        int lp = 0;
        for (int rp = s1.length(); rp < s2.length(); rp++) {
            //System.out.println("RP: " + rp);
            //System.out.println(Arrays.toString(s1CharFreq));
            //System.out.println(Arrays.toString(s2CharFreq));

            // Re-validate window by shifting lp up by 1
            s2CharFreq[s2.charAt(rp) - 'a'] += 1;
            s2CharFreq[s2.charAt(lp) - 'a'] -= 1;
            lp++;
            
            // Check for premutation equality
            if (Arrays.equals(s1CharFreq, s2CharFreq)) {
                return true;
            }
        }

        return false;
    }
}
