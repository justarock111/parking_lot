import java.util.ArrayList;
import java.util.List;

public class Board {
    private Piece[][] board;
    public static final int BOARD_SIZE = 8;
    private final int WHITE_FIRST_ROW = 6, WHITE_SECOND_ROW = 7;
    private final int BLACK_FIRST_ROW = 1, BLACK_SECOND_ROW = 0;
    
    public Board(){
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        initialiseOneSide(Side.WHITE, WHITE_FIRST_ROW, WHITE_SECOND_ROW);
        initialiseOneSide(Side.BLACK, BLACK_FIRST_ROW, BLACK_SECOND_ROW);
    }
    
    private void initialiseOneSide(Side side, int firstRow, int secRow){
        initialisePawns(side, firstRow);
        initialiseRooks(side, secRow);
        initialiseKnights(side, secRow);
        initialiseBishops(side, secRow);
        initialiseKingQueen(side, secRow);
    }

    private void initialisePawns(Side side, int row){
        for(int col = 0; col < 8; col++){
            Piece pawn = new Pawn(side, row, col);
            board[row][col] = pawn;
        }
    }

    private void initialiseRooks(Side side, int row){
        Piece rook1 = new Rook(side, row, 0);
        board[row][0] = rook1;

        Piece rook2 = new Rook(side, row, 7);
        board[row][7] = rook2;
    }

    private void initialiseKnights(Side side, int row){
        Piece knight1 = new Knight(side, row, 1);
        board[row][1] = knight1;

        Piece knight2 = new Knight(side, row, 6);
        board[row][6] = knight2;
    }

    private void initialiseBishops(Side side, int row){
        Piece bishop1 = new Bishop(side, row, 2);
        board[row][2] = bishop1;

        Piece bishop2 = new Bishop(side, row, 5);
        board[row][5] = bishop2;
    }

    private void initialiseKingQueen(Side side, int row){
        Piece queen = new Queen(side, row, 3);
        board[row][3] = queen;

        Piece king = new King(side, row, 4);
        board[row][4] = king;
    }

    public String display(){
        StringBuilder rows = new StringBuilder();
        for(char row = 0; row < BOARD_SIZE; row++){
            rows.append("\n");
            rows.append(displayRow(row));
        }

        return rows.toString();
    }

    private String displayRow(int row){
        List<String> rowList = new ArrayList<>();
        for(int col = 0; col < 8; col++){
            Piece piece = board[row][col];

            if(piece == null){
                rowList.add("--");
            } else {
                rowList.add(piece.toString());
            }
        }

        return String.join(" ", rowList);
    }

    public Piece getPiece(int[] pos){
        return board[pos[0]][pos[1]];
    }

    public boolean move(Piece startPiece, int[] endPos, boolean isCapture){
        boolean canMove = checkCanMove(startPiece, endPos, isCapture);
        if(!canMove){
            return false;
        }

        board[startPiece.getRow()][startPiece.getCol()] = null;
        startPiece.move(endPos);
        board[endPos[0]][endPos[1]] = startPiece;
       return true;
    }

    private boolean checkCanMove(Piece startPiece, int[] endPos, boolean isCapture) {
        boolean canPieceMove = startPiece.checkCanMove(endPos, isCapture);
        if(!canPieceMove || startPiece instanceof King || startPiece instanceof Knight){
            return canPieceMove;
        }

        int rowDiff = endPos[0] - startPiece.getRow();
        int colDiff = endPos[1] - startPiece.getCol();

        int[] incr = new int[2];
        incr[0] = getSign(rowDiff);
        incr[1] = getSign(colDiff);

        boolean noLeap = checkNoLeap(startPiece, endPos, incr);
        return noLeap;
    }

    private int getSign(int value){
        if(value > 0) {
            return 1;
        } else if(value < 0){
            return -1;
        } else {
            return 0;
        }
    }
    private boolean checkNoLeap(Piece startPiece, int[] endPos, int[] incr){
        int row = startPiece.getRow(), col = startPiece.getCol();
        row += incr[0]; col += incr[1];
        while(row != endPos[0] && col != endPos[1]){
            Piece piece = board[row][col];
            if(piece != null){
                return false;
            }

            row += incr[0];
            col += incr[1];
        }
        return true;
    }

}
