import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Main {
    private static Store store;

    public static void main(String[] args) {
        store = new Store();
        Scanner scanner = new Scanner(System.in);

        boolean isContinue = true;
        while(isContinue){
            String line = scanner.nextLine();
            String[] lineArgs = line.split(" ");

            isContinue = executeCommand(lineArgs);
        }
    }

    private static boolean executeCommand(String[] lineArgs){
        String command = lineArgs[0];
        switch(command) {
            case "get":
                String getOutput = store.get(lineArgs[1]);
                System.out.println(getOutput);
                break;
            case "put":
                try {
                    store.put(lineArgs);
                } catch (IllegalArgumentException e){
                    System.out.println("Data Type Error");
                }
                break;
            case "delete":
                store.delete(lineArgs[1]);
                break;
            case "search":
                String searchOutput = store.search(lineArgs[1], lineArgs[2]);
                System.out.println(searchOutput);
                break;
            case "keys":
                String keysOutput = store.keys();
                System.out.println(keysOutput);
                break;
            case "exit":
                return false;
        }
        return true;
    }

}
