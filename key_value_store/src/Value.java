import lombok.Getter;

import java.util.*;

public class Value {
    @Getter
    private LinkedHashMap<String, AttributeValue> attributes;

    public Value(){
        attributes = new LinkedHashMap<>();
    }

    public void initialiseAttributePairs(String[] args){
        for(int i = 2; i < args.length; i += 2){
            addAttributePair(args, i, i + 1);
        }
    }

    private void addAttributePair(String[] args, int attributeKeyIdx, int attributeValueIdx){
        String attributeKey = args[attributeKeyIdx];
        String attributeValueString = args[attributeValueIdx];
        AttributeValue attributeValue = AttributeValueFactory.constructAttributeValue(attributeValueString);

        attributes.put(attributeKey, attributeValue);
    }

    public boolean search(String attributeKey, String attributeValue){
        if(!attributes.containsKey(attributeKey)){
            return false;
        }

        String actualAttributeValue = attributes.get(attributeKey).getValue();
        return actualAttributeValue.equals(attributeValue);
    }

    @Override
    public String toString(){
        List<String> attributeStrings = new ArrayList<>();
        for(Map.Entry<String, AttributeValue> attribute: attributes.entrySet()){
            attributeStrings.add(attribute.getKey() + ": " + attribute.getValue().getValue());
        }

        return String.join(", ", attributeStrings);
    }
}
