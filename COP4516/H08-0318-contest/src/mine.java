import java.util.Scanner;

public class mine {

    public static void solve(int[][] board) {
        int[][] newBoard = updateValues(board);
        printBoard(newBoard);
    }

    public static int[][] updateValues(int[][] board) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if(board[i][j] == 0) {
                    int count = checkAdjacent(board, i, j);
                    board[i][j] = count;
                }
            }
        }
        return board;
    }

    public static int checkAdjacent(int[][] board, int row, int col) {
        int count = 0;
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++) {
                if(i==0 && j==0)
                    continue;

                int newRow = row+i;
                int newCol = col+j;

                if(newRow > -1 && newRow < board.length && newCol > -1 && newCol < board[0].length) {
                    if(board[newRow][newCol] == -1)
                        count++;
                }
            }
        }
        return count;
    }

    public static void printBoard(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(j == (board[0].length-1)) {
                    if (board[i][j] == -1)
                        System.out.println('*');
                    else
                        System.out.println(board[i][j]);
                }
                else {
                    if (board[i][j] == -1)
                        System.out.print('*');
                    else
                        System.out.print(board[i][j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int rows = input.nextInt();
        int cols = input.nextInt();
        String ch;

        while(rows != 0 && cols != 0) {

            int[][] board = new int[rows][cols];

            for(int i = 0; i < rows; i++) {
                ch = input.next();
                char[] charArray = ch.toCharArray();
                for(int j = 0; j < cols; j++) {
                    if(charArray[j] == '.') {    // double check this logic, use .equals() ???
                        board[i][j] = 0;
                    }
                    else
                        board[i][j] = -1;
                }
            }

            solve(board);

            rows = input.nextInt();
            cols = input.nextInt();
        }
    }

}
