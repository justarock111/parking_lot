import lombok.Getter;

import java.util.*;

public class User {
    @Getter
    private String id;
    private String name, email, phone;
    private TreeMap<String, Double> balances;

    public User(String id, String name, String email, String phone){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;

        this.balances = new TreeMap<>();
    }

    public double getAmountOwed(User user){
        Double amountOwed = balances.get(user.getId());
        return amountOwed == null? 0.0: amountOwed;
    }

    public void addBalance(User owedTo, double amount){
        String owedToId = owedTo.getId();
        Double oldAmount = balances.get(owedToId);
        if(oldAmount == null){
            oldAmount = 0.0;
        }

        double newAmount = oldAmount + amount;
        if(newAmount == 0){
            balances.remove(owedToId);
        } else {
            balances.put(owedToId, oldAmount + amount);
        }
    }

    public boolean showBalances(){
        for(Map.Entry<String, Double> kvp: balances.entrySet()){
            String output = String.format("%s owes %s: %.2f",
                    id, kvp.getKey(), kvp.getValue());
            System.out.println(output);
        }

        boolean hasBalance = balances.size() > 0;
        return hasBalance;
    }
}
