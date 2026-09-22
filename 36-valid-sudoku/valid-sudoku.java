class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] box = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num!= '.') {
                    int val = num - '1';
                    int boxIndex = (i / 3) * 3 + j / 3;

                    if (row[i][val] || col[j][val] || box[boxIndex][val]) {
                        return false;
                    }
                    
                    row[i][val] = true;
                    col[j][val] = true;
                    box[boxIndex][val] = true;
                }
            }
        }
        return true;
    }
}