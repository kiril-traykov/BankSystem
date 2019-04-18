package bank;
import java.util.concurrent.RejectedExecutionException;

public class ATM {

    private User currentUser;
    private Acc currentAccount;
    private UserManagement bank;

    ATM(UserManagement bank){
        this.bank = bank;
    }

    void setCurrentUser(int pin,int userId) throws IllegalAccessException {
        currentUser = bank.getUser(pin,userId);
    }

   void withdraw(int amount,int accNum) throws IllegalAccessException{
        checkUser();
       currentAccount = currentUser.getAccount(accNum);
       currentAccount.withdraw(amount);
    }

   void deposit(int amount,int accNum) throws IllegalAccessException,RejectedExecutionException{
        checkUser();
       currentAccount = currentUser.getAccount(accNum);
       currentAccount.deposit(amount);
   }

   Double getBalance(int accNum) throws IllegalAccessException,RejectedExecutionException {
        checkUser();
       currentAccount = currentUser.getAccount(accNum);
       return currentAccount.getBalance();

   }

   void changePin(int oldPin,int newPin) throws IllegalAccessException,IllegalArgumentException{
        checkUser();
       currentUser.changePin(oldPin,newPin);
    }

     User checkUser() throws IllegalAccessException {
        if(currentUser == null){
            throw new IllegalAccessException("Not logged in");
        }
        return currentUser;
    }

    public void setSystem(UserManagement bank){
        this.bank = bank;
    }

}
