import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        String initialBoard = game.displayBoard();
        System.out.println(initialBoard);

        while(true){
            String line = scanner.nextLine();
            String[] lineArgs = line.split(" ");

            if(lineArgs[0].equals("exit")){
                break;
            }

            String output = game.move(lineArgs);
            System.out.println(output);
        }
    }
}
