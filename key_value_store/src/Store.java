import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.DataFormatException;

public class Store {
    private Map<String, Value> map;
    private ConcurrentHashMap<String, AttributeValueType> types;

    public Store(){
        map = Collections.synchronizedMap(new TreeMap<>());
        types = new ConcurrentHashMap<String, AttributeValueType>();
    }

    public String get(String key){
        Value value = map.get(key);
        if(value == null){
            return "No entry found for " + key;
        } else {
            return value.toString();
        }
    }

    public void put(String[] args){
        String key = args[1];
        Value value = new Value();
        value.initialiseAttributePairs(args);

        recordAttributeType(value);
        map.put(key, value);

    }

    public void delete(String key){
        map.remove(key);
    }

    private void recordAttributeType(Value value) throws IllegalArgumentException{
        for(Map.Entry<String, AttributeValue> attribute: value.getAttributes().entrySet()){
            String attributeKey = attribute.getKey();
            AttributeValueType actualType = attribute.getValue().getType();
            AttributeValueType expectedType = types.get(attributeKey);

            if(expectedType != null && expectedType != actualType){
                throw new IllegalArgumentException("Invalid data format");
            }

            types.put(attributeKey, actualType);
        }
    }

    public String search(String attributeKey, String attributeValue){
        List<String> resultKeys = new ArrayList<>();

        for(Map.Entry<String, Value> pair: map.entrySet()){
            Value value = pair.getValue();
            boolean found = value.search(attributeKey, attributeValue);

            if(found){
                resultKeys.add(pair.getKey());
            }
        }

        return String.join(",", resultKeys);
    }

    public String keys(){
        Set<String> keys = map.keySet();
        return String.join(",", keys);
    }
}
