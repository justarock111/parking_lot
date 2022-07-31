import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Game {
    private Board board;
    private Queue<Player> players;
    private Dice dice;
    private Scanner scanner;

    private static final int DICE_VALUE_SIX = 6;

    public Game(){
        board = new Board();
        players = new LinkedList<>();
        dice = new Dice();
        scanner = new Scanner(System.in);
    }

    public void initialiseEntities(EntityType entityType){
        int numSnakes = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < numSnakes; i++){
            int startPos = scanner.nextInt();
            int endPos = scanner.nextInt();
            scanner.nextLine();
            board.addEntity(entityType, startPos, endPos);
        }
    }

    public void initialisePlayers(){
        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < numPlayers; i++){
            String name = scanner.nextLine();
            Player player = new Player(name);
            players.add(player);
        }
    }

    public void startGame() {
        while(!players.isEmpty()){
            Player currentPlayer = players.poll();
            int diceValue = dice.roll();
            boolean stillPlaying = makeMove(currentPlayer, diceValue);


            if(stillPlaying){
                players.add(currentPlayer);
            } else {
                System.out.println(currentPlayer.getName() + "  wins the game");
            }
        }
    }

    private boolean makeMove(Player currentPlayer, int diceValue){
        int oldPosition = currentPlayer.getPosition();
        int newPosition = getNewPosition(currentPlayer, diceValue);
        currentPlayer.move(newPosition);

        String output = String.format(
                "%s rolled a %d and moved from %d to %d",
        currentPlayer.getName(), diceValue, oldPosition, newPosition);
        System.out.println(output);

        return newPosition < board.MAX_POSITION;
    }

    private int getNewPosition(Player currentPlayer, int diceValue){
        recordConsecutiveRolls(currentPlayer, diceValue);

        int currentPosition = currentPlayer.getPosition();
        int newPosition = currentPosition + diceValue;

        if(newPosition > board.MAX_POSITION) {
            newPosition = currentPosition;
        }

        boolean hasEntity = board.hasEntity(newPosition);
        if(hasEntity){
            Entity entity = board.getEntity(newPosition);
            newPosition = entity.getEndPosition();
        }

        List<Integer> previousPositions = currentPlayer.getPreviousPositions();
        boolean consecutiveSixes = previousPositions.size() >= 3;
        if(consecutiveSixes){
            int previousPosition = previousPositions.get(0);
            newPosition = previousPosition;

            previousPositions.clear();
        }

        return newPosition;
    }

    private void recordConsecutiveRolls(Player currentPlayer, int diceValue){
        int currentPosition = currentPlayer.getPosition();
        if(diceValue == DICE_VALUE_SIX){
            currentPlayer.savePosition(currentPosition);
        } else {
            currentPlayer.removeSavedPositions();
        }
    }
}
