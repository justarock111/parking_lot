public class Main {

    public static void main(String[] args) {
	// write your code here
        Game game = new Game();
        game.initialiseEntities(EntityType.SNAKE);
        game.initialiseEntities(EntityType.LADDER);
        game.initialisePlayers();
        game.startGame();
    }
}
