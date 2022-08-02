import lombok.Getter;

public enum Side {
    WHITE("W"), BLACK("B");

    @Getter
    private String value;
    private Side(String value){
        this.value = value;
    }
}
