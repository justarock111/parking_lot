import lombok.Getter;

public abstract class Piece {
    @Getter
    protected int row;
    @Getter
    protected int col;
    @Getter
    protected Side side;

    public Piece(Side side, int row, int col){
        this.side = side;
        this.row = row;
        this.col = col;
    }

    public abstract boolean checkCanMove(int[] endPos, boolean isCapture);

    public void move(int[] endPos){
        this.row = endPos[0];
        this.col = endPos[1];
    }

    @Override
    public abstract String toString();
}
