
public class Board {
    private final int BOARD_SIZE = 3;
    private char[][] board;
    private int[] rowCounts, colCounts;
    private int numUnfilled, diagCount, reverseDiagCount;

    public Board() {
        board = new char[1 + BOARD_SIZE][1 + BOARD_SIZE];
        for (int r = 0; r <= BOARD_SIZE; r++) {
            for (int c = 0; c <= BOARD_SIZE; c++) {
                board[r][c] = '-';
            }
        }

        this.rowCounts = new int[1 + BOARD_SIZE];
        this.colCounts = new int[1 + BOARD_SIZE];
        this.diagCount = reverseDiagCount = 0;
        this.numUnfilled = BOARD_SIZE * BOARD_SIZE;
    }

    public char getPiece(int row, int col) {
        return board[row][col];
    }

    public State addPiece(PieceType pieceType, int row, int col) {
        board[row][col] = pieceType.getValue();
        numUnfilled--;

        int magnitude = pieceType == PieceType.CIRCLE ? 1 : -1;
        rowCounts[row] += magnitude;
        colCounts[col] += magnitude;

        if (row == col) {
            diagCount += magnitude;
        } else if (row + col == BOARD_SIZE + 1) {
            reverseDiagCount += magnitude;
        }

        return determineState(row, col);
    }

    private State determineState(int row, int col) {
        if(numUnfilled == 0){
            return State.GAME_OVER;
        }

        boolean rowComplete = Math.abs(rowCounts[row]) == BOARD_SIZE;
        boolean rowColComplete = rowComplete
                || Math.abs(colCounts[col]) == BOARD_SIZE;
        boolean eitherComplete = rowColComplete
                || Math.abs(diagCount) == BOARD_SIZE
                || Math.abs(reverseDiagCount) == BOARD_SIZE;
        return eitherComplete? State.WON: State.PLAYING;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 1; r <= BOARD_SIZE; r++) {
            for (int c = 1; c <= BOARD_SIZE; c++) {
                sb.append(board[r][c]);

                if (c < BOARD_SIZE) {
                    sb.append(' ');
                }
            }

            if (r < BOARD_SIZE) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

}
