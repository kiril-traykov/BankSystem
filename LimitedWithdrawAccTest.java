package bank;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.RejectedExecutionException;

import static org.junit.Assert.assertEquals;

public class LimitedWithdrawAccTest {


    LimitedWithdrawAcc limitedWithdrawAccount;


    @Before
    public void setUp(){
       limitedWithdrawAccount = new LimitedWithdrawAcc(100,150,1);
    }


    @Test
    public void shouldWithdrawMaximumAllowedSum()
    {
        limitedWithdrawAccount.withdraw(100);
        assertEquals(50,limitedWithdrawAccount.getBalance(),0);
    }

    @Test(expected = RejectedExecutionException.class)
    public void shouldRejectWithdrawAndThrowException(){

        limitedWithdrawAccount.withdraw(101);

    }


}