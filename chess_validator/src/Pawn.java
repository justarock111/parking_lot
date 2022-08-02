public class Pawn extends Piece{
    private int initialRow, initialCol;

    public Pawn(Side side, int row, int col){
        super(side, row, col);
        this.initialRow = row;
        this.initialCol = col;
    }

    @Override
    public boolean checkCanMove(int[] endPos, boolean isCapture){
        int dir = side == Side.WHITE? -1: 1;
        int rowDiff = (endPos[0] - row) * dir;
        int colDiff = (endPos[1] - col) * dir;

        if(rowDiff == 1 && colDiff < 2){
            return true;
        }

        boolean isTwoSteps = rowDiff == 2 && colDiff == 0;
        boolean isFirstMove = endPos[0]==  initialRow + (2 * dir)
                && endPos[1] == initialCol;
        boolean isValidMove = isFirstMove && isTwoSteps && !isCapture;
        return isValidMove;
    }

    @Override
    public String toString(){
        return side.getValue() + "P";
    }
}