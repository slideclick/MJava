import java.util.*;

public class CreditAccountsModel extends Object{

    private Map map;

    public CreditAccountsModel()
    {                
        map = new HashMap();
    }

    public Account getAccount (String accountId)
    {
        return (Account)(map.get (accountId)); 
    }

    public boolean insertAccount (Account account)
    {
        if (map.containsKey (account.getCardId()))
            return false;
        else{
            map.put (account.getCardId(), account);
            return true;
        }    
    }
    
    public void removeAccount (Account account)
    {
        map.remove (account.getCardId());
    }
    
    public String toString()
    {
        Iterator iter = map.values().iterator();
        String str = "";
        while (iter.hasNext())
            str += iter.next() + "\n";
        return str;
    }
}

