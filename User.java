package bank;
import java.util.HashMap;
import java.util.concurrent.RejectedExecutionException;

public class User {
    private int pin;
    int userId;

    User(int pin,int userId){
        this.pin = pin;
        this.userId = userId;
    }

    HashMap<Integer,Acc> accounts = new HashMap<>();

    public boolean authentication(int attemptedPin){
        return attemptedPin == pin;
    }

   public Acc getAccount(int accountNumber){
        if(accounts.containsKey(accountNumber)){
            return accounts.get(accountNumber);
        }
        else{throw new IllegalArgumentException("Account not found");}
    }

    public Acc addAccount(Acc newAcc,int accountId){
        if(accounts.containsKey(accountId)){
            throw new RejectedExecutionException("Account with such Id already exists");
        }
        accounts.put(accountId,newAcc);
        return newAcc;
    }

    public void changePin(int oldpin,int newpin)throws IllegalAccessException,IllegalArgumentException{
        if(!authentication(oldpin)){
            throw new IllegalAccessException("Wrong pin");
        }
        if(newpin == oldpin){
            throw new IllegalArgumentException("New pin cannot be the same as old pin");
        }
        pin = newpin;

    }

}
