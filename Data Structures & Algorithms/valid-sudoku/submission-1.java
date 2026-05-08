class Solution {
    public boolean isValidSudoku(char[][] board) {
        /*
        -- row 0
        -- row 1
        -- row 2
        -- row 3
        ...
        */
        Map<Integer, List<Character>> rowCheck = new HashMap<>();

        /*
        -- col 0 | col 1 | col 2 | col 3 | ...
        ...
        */
        Map<Integer, List<Character>> columnCheck = new HashMap<>();

        /*
        -- 0 1 2
        -- 3 4 5
        -- 6 7 8
        */
        Map<Integer, List<Character>> boxCheck = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            rowCheck.put(i, new ArrayList<>());
            columnCheck.put(i, new ArrayList<>());
            boxCheck.put(i, new ArrayList<>());
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char val = board[i][j];

                if (val == '.') { continue; }

                List<Character> row = rowCheck.get(i);
                List<Character> column = columnCheck.get(j);
                List<Character> box = boxCheck.get(3*(i / 3) + (j / 3));

                for (char c : row) {
                    if (val == c) {
                        return false;
                    }
                }
                for (char c : column) {
                    if (val == c) {
                        return false;
                    }
                }
                for (char c : box) {
                    if (val == c) {
                        return false;
                    }
                }

                row.add(val);
                column.add(val);
                box.add(val);
            }
        }

        //System.out.println(rowCheck);
        //System.out.println(columnCheck);
        //System.out.println(boxCheck);

        return true;
    }
}

/*
====== KEY TAKEAWAYS ======
 - Use hashsets if you want to:
    1. Detect duplicates
    2. Fastemembership checking
    3. Removing duplicates
    4. Track visited nodes/states
    5. HERE, use hashset as opposed to list, to avoid scanning
 - Do NOT use hashsets if you need:
    1. Ordering
    2. Duplicates
    3. Indexing
*/
