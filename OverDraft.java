package bank;

import java.util.concurrent.RejectedExecutionException;

public class OverDraft extends Account { // extend accounts



    double allowedOverDraft;

    OverDraft(double overDraft,double initialSum,int accId){
        super(initialSum,accId);
        balance = initialSum;
        this.accId = accId;
        allowedOverDraft = overDraft;
    }

    @Override
    public void withdraw(int sum)throws RejectedExecutionException {
        if(allowedOverDraft < Math.abs(sum - balance))
        {
            throw new RejectedExecutionException("Balance is not enough to perform that operation");
        }
        balance -= sum;

    }
}
