import java.util.List;

public class PercentExpense extends Expense {
    public PercentExpense(User owedTo, double totalAmount, List<User> owedFromList, List<Double> owedAmounts){
        super(owedTo, totalAmount, owedFromList, owedAmounts);
        checkTotalPercent();
    }

    private void checkTotalPercent(){
        double totalPercent = 0;
        for(Double percent: owedAmounts){
            totalPercent += percent;
        }

        if(totalPercent != 100.0){
            throw new IllegalArgumentException("Total percent does not add up to 100%");
        }
    }

    public void splitIntoUserBalances(){
        for(int i = 0; i < owedFromList.size(); i++){
            User user = owedFromList.get(i);
            double percent = owedAmounts.get(i);
            double amountForUser = totalAmount / 100 * percent;

            addBalance(user, amountForUser);
        }
    }
}
