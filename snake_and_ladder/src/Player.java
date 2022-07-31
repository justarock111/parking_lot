import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Player {
    @Getter
    private String name;
    @Getter
    private int position;
    @Getter
    private List<Integer> previousPositions;

    public Player(String name){
        this.name = name;
        this.position = 0;
        this.previousPositions = new ArrayList<>();
    }

    public void move(int newPosition){
        this.position = newPosition;
    }

    public void savePosition(int position){
        previousPositions.add(position);
    }

    public void removeSavedPositions(){
        previousPositions.clear();
    }

}
