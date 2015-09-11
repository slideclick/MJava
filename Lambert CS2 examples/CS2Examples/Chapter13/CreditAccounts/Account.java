// Account
// (c) 2000 Ken Lambert and Martin Osborne

public class Account extends Object{

    private String cardId;
    private String name;
    private double balance;

    public Account()
    {
        cardId = "";
        name = "";
        balance = 0;
    }
    
    public Account (String cardId, String name, double balance)
    {
        this.cardId = cardId;
        this.name = name;
        this.balance = balance;
    }
    
    public String getCardId ()
    {
        return cardId;
    }
    
    public String getName ()
    {
        return name;
    }
    
    public double getBalance ()
    {
        return balance;
    }
    
    public void deposit (double amount)
    {
        balance += amount;
    }
    
    public boolean withdraw (double amount)
    {
        if (amount > balance) 
            return false;
        else{
            balance -= amount;
            return true;
        }
    }
    
    public String toString()
    {
        return cardId + "  " + name + "  " + balance;
    }

}

