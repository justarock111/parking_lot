import java.util.Random;

public class Dice {
    private Random random;
    private final int MAX_VALUE = 6;

    public Dice(){
        random = new Random();
    }

    public int roll(){
        return random.nextInt(MAX_VALUE) + 1;
    }
}
