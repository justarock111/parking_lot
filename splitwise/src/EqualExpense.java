import java.util.List;

public class EqualExpense extends Expense {
    public EqualExpense(User owedTo, double totalAmount, List<User> owedFromList, List<Double> owedAmounts){
        super(owedTo, totalAmount, owedFromList, owedAmounts);
    }

    public void splitIntoUserBalances(){
        double amountPerUser = totalAmount / owedFromList.size();
        boolean hasRemainder = amountPerUser % 0.01 > 0.0;
        double remainder = hasRemainder? 0.01 : 0.00;

        User firstUser = owedFromList.get(0);
        addBalance(firstUser, amountPerUser + remainder );

        for(int i = 1; i < owedFromList.size(); i++){
            User user = owedFromList.get(i);
            addBalance(user, amountPerUser);
        }
    }
}
