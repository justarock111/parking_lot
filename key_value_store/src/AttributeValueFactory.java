public class AttributeValueFactory {
    public static AttributeValue constructAttributeValue(String attributeValueString) {
        AttributeValueType type = AttributeValueType.STRING;
        if(isBoolean(attributeValueString)){
            type = AttributeValueType.BOOLEAN;
        } else if(isInteger(attributeValueString)){
            type = AttributeValueType.INTEGER;
        } else if(isDouble(attributeValueString)){
            type = AttributeValueType.DOUBLE;
        }

        return new AttributeValue(attributeValueString, type);
    }

    private static boolean isBoolean(String str){
        return str.equals("true") || str.equals("false");
    }

    private static boolean isInteger(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    private static boolean isDouble(String str){
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
