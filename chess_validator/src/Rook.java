public class Rook extends Piece{
    public Rook(Side side, int row, int col){
        super(side, row, col);
    }

    @Override
    public boolean checkCanMove(int[] endPos, boolean isCapture){
        if(row == endPos[0] || col == endPos[1]){
            return true;
        }

        return false;
    }

    @Override
    public String toString(){
        return side.getValue() + "R";
    }
}