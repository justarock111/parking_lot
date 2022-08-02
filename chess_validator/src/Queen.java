public class Queen extends Piece{
    public Queen(Side side, int row, int col){
        super(side, row, col);
    }

    @Override
    public boolean checkCanMove(int[] endPos, boolean isCapture){
        if(row == endPos[0] || col == endPos[1]){
            return true;
        }

        int rowDiff = Math.abs(row - endPos[0]);
        int colDiff = Math.abs(col - endPos[1]);
        return rowDiff == colDiff;
    }

    @Override
    public String toString(){
        return side.getValue() + "Q";
    }
}