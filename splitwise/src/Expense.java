import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class Expense {
    protected User owedTo;
    protected double totalAmount;
    protected List<User> owedFromList;
    protected List<Double> owedAmounts;

    public static Expense constructExpense(User owedTo, double totalAmount, List<User> owedFromList,
                                           List<Double> owedAmounts, String expenseType){
        switch(expenseType){
            case "EQUAL":
                return new EqualExpense(owedTo, totalAmount, owedFromList, owedAmounts);
            case "EXACT":
                return new ExactExpense(owedTo, totalAmount, owedFromList, owedAmounts);
            case "PERCENT":
                return new PercentExpense(owedTo, totalAmount, owedFromList, owedAmounts);
            default:
                throw new IllegalArgumentException("Invalid expense type");
        }
    }

    protected Expense(User owedTo, double totalAmount, List<User> owedFromList, List<Double> owedAmounts){
        this.owedTo = owedTo;
        this.totalAmount = totalAmount;
        this.owedFromList = owedFromList;
        this.owedAmounts = owedAmounts;
    }


    public abstract void splitIntoUserBalances();

    protected void addBalance(User user, double amountOwedTo){
        if(user == owedTo){
            return;
        }

        double amountOwedFrom = owedTo.getAmountOwed(user);
        amountOwedFrom = Math.min(amountOwedFrom, amountOwedTo);
        owedTo.addBalance(user, -1 * amountOwedFrom);

        double actualAmountOwed = amountOwedTo - amountOwedFrom;
        user.addBalance(owedTo, actualAmountOwed);

    }
}
