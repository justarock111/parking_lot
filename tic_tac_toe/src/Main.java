import java.util.Scanner;

public class Main {
    private static Game game;
    private static Scanner scanner;

    public static void main(String[] args) {
        game = new Game();
        scanner = new Scanner(System.in);

        initialisePlayer();
        initialisePlayer();

        String boardString = game.getBoardString();
        System.out.println(boardString);
        playMoves();

    }

    private static void initialisePlayer(){
        String str = scanner.nextLine();
        String[] args = str.split(" ");
        String pieceType = args[0], name = args[1];

        game.addPlayer(pieceType.charAt(0), name);
    }

    private static void playMoves(){
        while(true){
            String line = scanner.nextLine();
            if(line.equals("exit")){
                break;
            }
            String[] args = line.split(" ");
            int row = Integer.valueOf(args[0]), col = Integer.valueOf(args[1]);

            String output = game.addPieceType(row, col);
            System.out.println(output);
        }
    }
}
