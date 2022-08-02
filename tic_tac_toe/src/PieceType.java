import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum PieceType {
    CROSS('X'), CIRCLE('O');

    @Getter
    private char value;
    private static Map<Character, PieceType> valueMap;

    static {
        valueMap = new HashMap<>();
        valueMap.put('X', CROSS);
        valueMap.put('O', CIRCLE);
    }

    PieceType(char value){
        this.value = value;
    }

    public static PieceType getPieceType(char value){
        return valueMap.get(value);
    }
}
