import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(User owedTo, double totalAmount, List<User> owedFromList, List<Double> owedAmounts){
    super(owedTo, totalAmount, owedFromList, owedAmounts);
    checkTotalAmount();
}

    private void checkTotalAmount(){
        double usersAmount = 0;
        for(Double owedAmount: owedAmounts){
            usersAmount += owedAmount;
        }

        if(usersAmount != totalAmount){
            throw new IllegalArgumentException("Users amount does not add up to total amount");
        }
    }

    public void splitIntoUserBalances(){
        for(int i = 0; i < owedFromList.size(); i++){
            User user = owedFromList.get(i);
            double owedAmount = owedAmounts.get(i);

            addBalance(user, owedAmount);
        }
    }
}
