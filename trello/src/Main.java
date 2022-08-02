import java.util.Scanner;

public class Main {
    private static Trello trello;

    public static void main(String[] args) {
	// write your code here
        trello = new Trello();
        trello.addMember("user1", "name1", "email1");
        trello.addMember("user2", "name2", "email2");
        trello.addMember("user3", "name3", "email3");

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] lineArgs = line.split(" ");
            executeCommand(lineArgs);
        }
    }

    private static void executeCommand(String[] lineArgs){
        FirstWord firstWord = FirstWord.valueOf(lineArgs[0]);
        switch(firstWord){
            case BOARD:
                executeBoardCommand(lineArgs);
                break;
            case LIST:
                executeListCommand(lineArgs);
                break;
            case CARD:
                executeCardCommand(lineArgs);
                break;
            case SHOW:
                executeShowCommand(lineArgs);
                break;
            default:
                throw new IllegalArgumentException("Invalid command");
        }
    }

    private static void executeBoardCommand(String[] lineArgs){
        if(lineArgs.length < 2){
            throw new IllegalArgumentException("Invalid number of terms");
        }

        String secondWord = lineArgs[1];
        switch(secondWord){
            case "CREATE":
                trello.createBoard(lineArgs[2]);
                break;
            case "DELETE":
                trello.deleteBoard(lineArgs[2]);
                break;
            default:
                trello.modifyBoard(lineArgs[1], lineArgs[2], lineArgs[3]);
        }
    }

    private static void executeListCommand(String[] lineArgs){
        String secondWord = lineArgs[1];
        switch(secondWord){
            case "CREATE":
                trello.createList(lineArgs[2], lineArgs[3]);
                break;
            case "DELETE":
                trello.deleteList(lineArgs[2]);
                break;
            default:
                trello.modifyList(lineArgs[1], lineArgs[2], lineArgs[3]);
        }
    }

    private static void executeCardCommand(String[] lineArgs){
        if(lineArgs.length < 2){
            throw new IllegalArgumentException("Invalid number of terms");
        }

        String secondWord = lineArgs[1];
        switch(secondWord){
            case "CREATE":
                trello.createCard(lineArgs[2], lineArgs[3]);
                break;
            case "MOVE":
                trello.moveCard(lineArgs[2], lineArgs[3]);
                break;
            case "DELETE":
                trello.deleteCard(lineArgs[2]);
                break;
            default:
                trello.modifyCard(lineArgs);
        }
    }

    private static void executeShowCommand(String[] lineArgs){
        if(lineArgs.length == 1){
            trello.showBoards();
            return;
        }

        String secondWord = lineArgs[1];
        switch(secondWord){
            case "BOARD":
                trello.showBoard(lineArgs[2]);
                break;
            case "LIST":
                trello.showList(lineArgs[2]);
                break;
            case "CARD":
                trello.showCard(lineArgs[2]);
                break;
            default:
               throw new IllegalArgumentException("Invalid show command");
        }
    }
}
