import lombok.Builder;

public class Bishop extends Piece{
    public Bishop(Side side, int row, int col){
        super(side, row, col);
    }

    @Override
    public boolean checkCanMove(int[] endPos, boolean isCapture){
        int rowDiff = Math.abs(row - endPos[0]);
        int colDiff = Math.abs(col - endPos[1]);
        return rowDiff == colDiff;
    }

    @Override
    public String toString(){
        return side.getValue() + "B";
    }
}