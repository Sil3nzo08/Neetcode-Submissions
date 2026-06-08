class Solution {
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) { return ""; }

        Map<Character, Integer> mapT = new HashMap<>();
        Map<Character, Integer> mapS = new HashMap<>();

        // Populate map T
        int matchesWithT = 0; // matches should equal num of entries in mapT
        for (int i = 0; i < t.length(); i++) {
            mapT.put(t.charAt(i), mapT.getOrDefault(t.charAt(i), 0) + 1); 
        }
        //System.out.println("T:" + mapT);

        // Sliding window on string s
        int ansLP = 0;
        int ansRP = 0;
        int minLength = Integer.MAX_VALUE;

        int lp = 0;
        for (int rp = 0; rp < s.length(); rp++) {
            // Add char at rp
            char rChar = s.charAt(rp);
            if (!mapT.containsKey(rChar)) { continue; }

            mapS.put(rChar, mapS.getOrDefault(rChar, 0) + 1);
            int currTallyR = mapS.get(rChar);
            if (currTallyR == mapT.get(rChar)) {
                // Tally in S now matches tally in T
                matchesWithT++;
            }
            
            // Shrink lp if valid
            while (matchesWithT == mapT.size()) {
                    char lChar = s.charAt(lp);
                    
                    if (!mapT.containsKey(lChar)) { lp++; continue; }
                    //System.out.println("LCHAR: " + lChar);

                    mapS.put(lChar, mapS.get(lChar) - 1);
                    int currTallyL = mapS.get(lChar);
                    if (currTallyL == mapT.get(lChar) - 1) {
                        // Tally in S no longer matches tally in T
                        matchesWithT--;

                        // Record minLength if needed
                        if (minLength > rp - lp + 1) {
                            minLength = rp - lp + 1;
                            ansRP = rp;
                            ansLP = lp;
                        }
                    }
                    
                    lp++;
            }

            //System.out.println(mapS);
            //System.out.println("lp: " + lp + " rp: " + rp);
            //System.out.println(s.substring(lp, rp + 1));
            //System.out.println(matchesWithT);
        }

        //System.out.println(mapT);

        //System.out.println("LP: " + ansLP + " RP: " + ansRP);
         
        if (minLength == Integer.MAX_VALUE) { return ""; }
        return s.substring(ansLP, ansRP + 1);
    }
}
/* === LEGACY CODE ===

for (int rp = 0; rp < s.length(); rp++) {
            // Add char at rp
            char rChar = s.charAt(rp);
            if (!mapT.containsKey(rChar)) { continue; }

            mapS.put(rChar, mapS.getOrDefault(rChar, 0) + 1);
            int currTallyR = mapS.get(rChar);
            if (currTallyR == mapT.get(rChar)) {
                // Tally in S now matches tally in T
                matchesWithT++;
            } else if (currTallyR == mapT.get(rChar) + 1) {
                // Tally in S no longer matches tally in T
                matchesWithT--;
            }
            
            // Shrink lp
            while (true) {
                    char lChar = s.charAt(lp);
                    if (mapT.containsKey(lChar) && mapS.get(rChar) <= mapT.get(rChar)) { break; }

                    System.out.println("LCHAR: " + lChar);

                    if (mapT.containsKey(lChar)) {
                        mapS.put(lChar, mapS.get(lChar) - 1);

                        int currTallyL = mapS.get(lChar);
                        if (currTallyL == mapT.get(lChar)) {
                            // Tally in S now matches tally in T
                            matchesWithT++;
                        } else if (currTallyL == mapT.get(lChar) - 1) {
                            // Tally in S no longer matches tally in T
                            matchesWithT--;
                        }
                    }
                    lp++;
            }

            // I believe a valid window will be produced here, so:
            if ((matchesWithT == mapT.size()) && (minLength > rp - lp + 1)) {
                minLength = rp - lp + 1;
                ansRP = rp;
                ansLP = lp;
            }

            System.out.println(mapS);
            System.out.println("lp: " + lp + " rp: " + rp);
            System.out.println(s.substring(lp, rp + 1));
            System.out.println(matchesWithT);
        }

*/
