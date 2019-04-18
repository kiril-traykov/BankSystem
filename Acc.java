package bank;

import java.util.concurrent.RejectedExecutionException;

public interface Acc {

    public void withdraw(int sum)throws RejectedExecutionException;
    public void deposit(int sum);
    public double getBalance();

}
