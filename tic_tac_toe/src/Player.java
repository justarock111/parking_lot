import lombok.Getter;

public class Player {
    @Getter
    private String name;
    @Getter
    private PieceType pieceType;

    public Player(PieceType pieceType, String name){
        this.name = name;
        this.pieceType = pieceType;
    }
}
