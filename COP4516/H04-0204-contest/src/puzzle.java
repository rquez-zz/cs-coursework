import java.util.*;

public class puzzle {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        HashMap<String, Integer> hashTable = new HashMap<>();

        // Queue of unique puzzle states
        Queue<PuzzleState> queue = new ArrayDeque<>();

        // Queue the winning state
        int[][] winningBoard = { {1,2,3}, {4,5,6}, {7,8,0} };
        PuzzleState firstState = new PuzzleState(winningBoard, 0, new Cell(2,2), null);
        queue.add(firstState);

        // Perform BFS on winning state, exit when input board is found
        while (!queue.isEmpty()) {

            // Pop off the next state in queue
            PuzzleState currentState = queue.poll();

            String tableString = stringify(currentState.board);
            hashTable.put(tableString, currentState.movesToState);

            // Get possible states from this state
            ArrayList<PuzzleState> possibleStates = getAvailableMoves(currentState);

            for (PuzzleState state : possibleStates) {
                if (!hashTable.containsKey(stringify(state.board)))
                   queue.add(state);
            }
        }

        int numPuzzles = scanner.nextInt();
        for (int i = 0; i < numPuzzles; i++) {

            // Build Puzzle
            int[][] puzzle = new int[3][3];
            for (int j = 0; j < 3; j++) {
                int[] row = new int[3];
                row[0] = scanner.nextInt();
                row[1] = scanner.nextInt();
                row[2] = scanner.nextInt();
                puzzle[j] = row;
            }

            System.out.println(hashTable.get(stringify(puzzle)));
        }
    }

    private static String stringify(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int c : row) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static ArrayList<PuzzleState> getAvailableMoves(PuzzleState state) {

        // Get neighbor cells
        ArrayList<Cell> neighbors = getNeighbors(state);

        // Build states for each neighbor
        ArrayList<PuzzleState> moves = new ArrayList<>();
        for (Cell c : neighbors) {

            // Create new puzzle board
            int[][] newBoard = buildNewMove(state.board, state.openCell, c);

            // Build state
            PuzzleState newState = new PuzzleState(newBoard, state.movesToState + 1, c, state.openCell);

            // Add state
            moves.add(newState);
        }

        return moves;
    }

    private static int[][] buildNewMove(int[][] puzzle, Cell openCell, Cell c) {

        // Copy puzzle
        int[][] board = new int[puzzle.length][puzzle.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
               board[i][j] = puzzle[i][j];
            }
        }

        // Swap cells
        board[c.row][c.col] = 0;
        board[openCell.row][openCell.col] = puzzle[c.row][c.col];

        return board;
    }

    private static ArrayList<Cell> getNeighbors(PuzzleState state) {

        Cell openCell = state.openCell;

        ArrayList<Cell> result = new ArrayList();
        if (openCell.col - 1 >= 0) {
            result.add(new Cell(openCell.row, openCell.col - 1));
        }
        if (openCell.col + 1 <= 2) {
            result.add(new Cell(openCell.row, openCell.col + 1));
        }
        if (openCell.row + 1 <= 2) {
            result.add(new Cell(openCell.row + 1, openCell.col));
        }
        if (openCell.row - 1 >= 0) {
            result.add(new Cell(openCell.row - 1, openCell.col));
        }

        int i = 0;
        while (state.previousOpenCell != null && i < result.size()) {
            if (state.previousOpenCell.equals(result.get(i)))
                result.remove(i);
            i++;
        }

        return result;
    }
}

class PuzzleState {

    public int[][] board;
    public int movesToState;
    public Cell openCell;
    public Cell previousOpenCell;

    public PuzzleState(int[][] board, int movesToState, Cell openCell, Cell previousOpenCell) {
        this.board = board;
        this.movesToState = movesToState;
        this.openCell = openCell;
        this.previousOpenCell = previousOpenCell;
    }
}

class Cell {
    int row;
    int col;
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean equals(Cell c) {
        if (this.row == c.row && this.col == c.col) return true;
        else return false;
    }
}
