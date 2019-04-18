package bank;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.RejectedExecutionException;

import static org.junit.Assert.assertEquals;

public class OverDraftTest {



    private OverDraft overDraftAccount;

   @Before
    public void setUp(){
     overDraftAccount = new OverDraft(200,100,1);
    }

    @Test
    public void shouldHaveNegativeBalance()
    {
        overDraftAccount.withdraw(150);
        assertEquals(-50,overDraftAccount.getBalance(),0.5);
    }

    @Test(expected = RejectedExecutionException.class)
    public void shouldThrowExceptionWhenAttemptingToWithdrawMoreThanOverdraft()
    {
        overDraftAccount.withdraw(301);
    }

    @Test
    public void balanceShouldEqualAllowedOverdraft(){
        overDraftAccount.withdraw(300);
        assertEquals(-200,overDraftAccount.getBalance(),0);

    }

   }









