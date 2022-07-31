import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Controller {
    private TreeMap<String, User> users;
    private Scanner scanner;

    public Controller(){
        users = new TreeMap<String, User>();
        scanner = new Scanner(System.in);
    }

    public void addUser(String userId, String name, String email, String phone){
        User newUser = new User(userId, name, email, phone);
        users.put(userId, newUser);
    }

    public void start(){
        while(true){
            String line = scanner.nextLine();
            String[] args = line.split(" ");

            switch(args[0]){
                case "EXPENSE":
                    executeExpenseCommand(args);
                    break;
                case "SHOW":
                    executeShowCommand(args);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid command");
            }
        }
    }

    private void executeExpenseCommand(String[] args){
        try {
            int offset = 1;
            String owedToId = args[offset++];
            User owedTo = users.get(owedToId);
            double totalAmount = Double.valueOf(args[offset++]);

            int numOwedFrom = Integer.valueOf(args[offset++]);
            List<User> owedFromList = parseOwedFromList(args, offset, numOwedFrom);
            offset += numOwedFrom;

            String expenseType = args[offset++];
            List<Double> owedAmounts = parseOwedAmounts(args, offset);

            Expense expense = Expense.constructExpense(owedTo, totalAmount,
                    owedFromList, owedAmounts, expenseType);
            expense.splitIntoUserBalances();

        } catch (NumberFormatException e){
            throw new NumberFormatException("Invalid type of args");
        } catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException("Invalid number of args");
        }
    }

    private List<User> parseOwedFromList(String[] args, int offset, int numOwedFrom){
        List<User> owedFromList = new ArrayList<>();

        for(int i = offset; i < numOwedFrom + offset; i++){
            String owedFromId = args[i];
            User owedFrom = users.get(owedFromId);
            owedFromList.add(owedFrom);
        }

        return owedFromList;
    }

    private List<Double> parseOwedAmounts(String[] args, int offset){
        List<Double> owedAmounts = new ArrayList<>();

        for(int i = offset; i < args.length; i++){
            double owedAmount = Double.valueOf(args[i]);
            owedAmounts.add(owedAmount);
        }

        return owedAmounts;
    }

    private void executeShowCommand(String[] args){
        if(args.length == 1){
            showBalances();
            return;
        }

        if(args.length != 2){
            throw new IndexOutOfBoundsException("Invalid number of args");
        }

        try {
            String userId = args[1];
            showUserBalances(userId);
        } catch (NumberFormatException e){
            throw new NumberFormatException("Invalid type of args");
        }
    }

    private void showBalances(){
        boolean hasBalance = false;
        for(User user: users.values()){
            boolean userHasBalance = user.showBalances();
            hasBalance = hasBalance || userHasBalance;
        }

        if(!hasBalance){
            System.out.println("No balances");
        }
    }

    public void showUserBalances(String userId){
        User user = users.get(userId);
        boolean hasBalance = user.showBalances();

        if(!hasBalance){
            System.out.println("No balances");
        }
    }
}

