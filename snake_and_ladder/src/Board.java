import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Integer, Entity> entities;
    public final int MAX_POSITION = 100;

    public Board(){
        entities = new HashMap<>();
    }

    public void addEntity(EntityType entityType, int startPos, int endPos){
        Entity entity = new Entity(entityType, startPos, endPos);
        entities.put(startPos, entity);
    }

    public boolean hasEntity(int position){
        return entities.containsKey(position);
    }

    public Entity getEntity(int position){
        return entities.get(position);
    }


}
