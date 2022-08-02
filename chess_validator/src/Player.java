import lombok.Getter;

public class Player {
    @Getter
    Side side;
    String id;

    public Player(String id, Side side){
        this.id = id;
        this.side = side;
    }
}
