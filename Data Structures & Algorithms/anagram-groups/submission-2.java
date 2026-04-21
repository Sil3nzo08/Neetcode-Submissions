class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<>();

        boolean added = false;
        for (String s : strs) {
            int[] occurrences = new int[26];
            for (char c : s.toCharArray()) {
                occurrences[c - 'a']++;
            }

            String key = Arrays.toString(occurrences);
            //System.out.println(key);

            List<String> arr = anagrams.getOrDefault(key, new ArrayList<>());
            arr.add(s);
            anagrams.put(key, arr);
        }

        List<List<String>> ans = new ArrayList<>();
        for (List<String> anagram : anagrams.values()) {
            ans.add(anagram);
        }

        return ans;
    }
}
