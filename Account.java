package bank;
import java.util.concurrent.RejectedExecutionException;


public class Account implements Acc {



    Account(double initialSum,int accId){
        balance = initialSum;
    }

   protected double balance;
   protected int accId;


    public void withdraw(int sum)throws RejectedExecutionException {
        if(balance < sum)
        {
            throw new RejectedExecutionException("Not enough balance to perform this operation");
        }
        balance -= sum;
    }

    public void deposit(int sum){balance += sum;}
    public double getBalance(){return balance;}

}
