package bank;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.RejectedExecutionException;

import static org.junit.Assert.*;

public class AccountTest {

    Account testAccount;
    @Before
    public void setUp(){
        testAccount = new Account(200,1);
    }


    @Test
    public void shouldHaveBalanceOfZero() {

        testAccount.withdraw(200);
        assertEquals(0,testAccount.getBalance(),0);
    }

    @Test(expected = RejectedExecutionException.class)
    public void shouldRejectAndThrowException() {

        testAccount.withdraw(300);

    }

    @Test
    public void ShouldAdd200ToBalance() {

        testAccount.deposit(200);
        assertEquals(400,testAccount.getBalance(),0);
    }

}