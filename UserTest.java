package bank;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.RejectedExecutionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class UserTest {

    User usr;

    @Before
    public void setUp(){
         usr = new User(1234,1);
    }

    @Test
    public void authentication() {

        assertEquals(true,usr.authentication(1234));
    }

    @Test
    public void auth_rejected(){

        assertEquals(false,usr.authentication(1243));
    }

    @Test
    public void addAccount() {
        Account mockAccount = mock(Account.class);
        usr.addAccount(mockAccount,1);
        assertEquals(mockAccount,usr.accounts.get(1));
    }

    @Test(expected = RejectedExecutionException.class)
    public void addAccount_Rejected() {
        Account mockAccount1 = mock(Account.class);
        usr.addAccount(mockAccount1,1);
        Account mockAccount2 = mock(Account.class);
        usr.addAccount(mockAccount2,1);

    }

    @Test
    public void changePin() throws IllegalAccessException {

        usr.changePin(1234,4321);
        assertEquals(true,usr.authentication(4321));

    }

    @Test(expected = IllegalArgumentException.class)
    public void changePin_SamePin() throws IllegalAccessException {
        usr.changePin(1234,1234);
    }

    @Test(expected = IllegalAccessException.class)
    public void changePin_WrongPin() throws IllegalAccessException {
        usr.changePin(4321,1234);
    }
}