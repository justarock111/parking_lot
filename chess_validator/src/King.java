public class King extends Piece{
    public King(Side side, int row, int col){
        super(side, row, col);
    }

    @Override
    public boolean checkCanMove(int[] endPos, boolean isCapture){
        int rowDiff = Math.abs(row - endPos[0]);
        int colDiff = Math.abs(col - endPos[1]);

        return rowDiff < 2 && colDiff < 2;
    }

    @Override
    public String toString(){
        return side.getValue() + "K";
    }
}
