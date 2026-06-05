class Solution {
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> freq = new HashMap<>();     
        int longestRepeat = 0;   

        // Sliding window
        int greatestFrequency = 0;
        int numAtGreatestFrequency = 0;
        
        int lp = 0;
        for (int rp = 0; rp < s.length(); rp++) {
            // Record new frequency
            int currFreq = freq.getOrDefault(s.charAt(rp), 0) + 1;
            freq.put(s.charAt(rp), currFreq);
            greatestFrequency = Math.max(greatestFrequency, currFreq);

            System.out.println("freq: " + greatestFrequency);

            int k_left = k - ((rp - lp + 1) - greatestFrequency);
            while (k_left < 0) {
                System.out.println("CHANGING K: " + k_left);
                System.out.println(freq);

                // shift lp up
                int lFreq = freq.get(s.charAt(lp));
                freq.put(s.charAt(lp), lFreq - 1);
                lp++;

                if (lFreq == greatestFrequency) {
                    greatestFrequency--;
                    for (int frequency : freq.values()) {
                        greatestFrequency = Math.max(greatestFrequency, frequency);
                    }
                } 

                // recompute k needed
                k_left = k - ((rp - lp + 1) - greatestFrequency);
                System.out.println("new K: " + k_left);
            }
            System.out.println("not in while loop: " + freq);

            longestRepeat = Math.max(longestRepeat, rp - lp + 1);
        } 

        //System.out.println(freq);

        return longestRepeat;
    }
}
