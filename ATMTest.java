package bank;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.concurrent.RejectedExecutionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ATMTest {

@Spy ATM atm;
@Mock User mockUser;
@Mock UserManagement bank;
@Before
public void setUp() throws IllegalAccessException {
    mockUser = mock(User.class);
    bank = mock(UserManagement.class);
    atm = new ATM(bank);
    atm.setSystem(bank);
    when(bank.getUser(1234,1)).thenReturn(mockUser);
}

@Test
    public void shouldReturnTheMockUser() throws IllegalAccessException {
    atm.setCurrentUser(1234,1);

   assertEquals(mockUser,atm.checkUser());

}

@Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForMissingAccount() throws IllegalAccessException {
    atm.setCurrentUser(1234,1);

    when(mockUser.getAccount(1)).thenThrow(IllegalArgumentException.class);
    atm.deposit(100,1);
}

@Test
    public void shouldReturnThePreSetBalance() throws IllegalAccessException {
    atm.setCurrentUser(1234,1);

    Account mockAccount = mock(Account.class);
    when(mockUser.getAccount(1)).thenReturn(mockAccount);
    when(mockAccount.getBalance()).thenReturn(100.0);
    assertEquals(atm.getBalance(1),100,0);

}

@Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForHavingTheSameOldAndNewPin() throws IllegalAccessException {
    atm.setCurrentUser(1234,1);

    doThrow(IllegalArgumentException.class).when(mockUser).changePin(1234,1234);

    atm.changePin(1234,1234);
}

@Ignore
    public void loggedInUserShouldBeTheSameAsTheMockedUser() throws IllegalAccessException {
    atm.setCurrentUser(1234,1);

    assertEquals(atm.checkUser(),mockUser);

}

@Test(expected = IllegalAccessException.class)
    public void loggedInUserShouldBeNull() throws IllegalAccessException {
    assertEquals(atm.checkUser(),null);

}

@Test(expected = RejectedExecutionException.class)
public void shouldReachAccountMethodThenThrowException() throws IllegalAccessException {
    atm.setCurrentUser(1234,1);

    Account mockAccount = mock(Account.class);
    when(mockUser.getAccount(1)).thenReturn(mockAccount);
    doThrow(RejectedExecutionException.class).when(mockAccount).withdraw(250);
    atm.withdraw(250,1);
}



}