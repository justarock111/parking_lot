import lombok.Getter;

public class Entity {
    private EntityType entityType;
    @Getter
    private int startPosition, endPosition;

    public Entity(EntityType entityType, int startPosition, int endPosition){
        switch(entityType) {
            case SNAKE:
                if(endPosition >= startPosition){
                    throw new IllegalArgumentException("For snake, end position must be smaller");
                }
                break;
            case LADDER:
                if(endPosition <= startPosition){
                    throw new IllegalArgumentException("For ladder, start position must be smaller");
                }
        }

        this.entityType = entityType;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }
}
