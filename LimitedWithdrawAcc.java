package bank;

import java.util.concurrent.RejectedExecutionException;

public class LimitedWithdrawAcc extends Account {

    double allowedWithdrawSum;

    LimitedWithdrawAcc(double maxWithdrawSum,double initialSum,int accId){
        super(initialSum,accId);
        balance = initialSum;
        this.accId = accId;
        allowedWithdrawSum = maxWithdrawSum;
    }
@Override
    public void withdraw(int sum)throws RejectedExecutionException {
        if(sum > allowedWithdrawSum)
        {
            throw new RejectedExecutionException("The sum is over the limit for this type of account");
        }
        super.withdraw(sum);

    }


}
