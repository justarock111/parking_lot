import lombok.Getter;

public class AttributeValue {
    @Getter
    private String value;
    @Getter
    private AttributeValueType type;

    public AttributeValue(String value, AttributeValueType type){
        this.value = value;
        this.type = type;
    }
}
