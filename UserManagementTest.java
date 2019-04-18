package bank;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.concurrent.RejectedExecutionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserManagementTest {


    @Spy UserManagement bank;
    @Mock User mockUser;
    @Before
    public void setUp(){
        bank = Mockito.spy(UserManagement.class);
        mockUser = mock(User.class);
    }

    @Test
    public void shouldPutUserInCollectionAndReturnIt() throws IllegalAccessException {

        when(mockUser.authentication(1234)).thenReturn(true);
        bank.putUserInCollection(mockUser,1);
        assertEquals(mockUser,bank.getUser(1234,1));
    }

    @Test
    public void shouldCreateUserAndPutItInCollectionThenCheckItsId() throws IllegalAccessException {
        bank.createUser(1234,1);
        assertEquals(1,bank.getUser(1234,1).userId);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldCreateUsersWithCollidingIdsThenThrowException(){

        bank.createUser(1234,1);
        bank.createUser(1234,1);

    }

    @Test(expected = RejectedExecutionException.class)
    public void shouldCreateStandartAccountThenThrowException(){

        when(mockUser.addAccount(any(),anyInt())).thenThrow(RejectedExecutionException.class);
        bank.createStandartAccount(150,mockUser);

    }

    @Test(expected = RejectedExecutionException.class)
    public void shouldCreateOverdraftAccountThenThrowException(){


        when(mockUser.addAccount(any(),anyInt())).thenThrow(RejectedExecutionException.class);
        bank.createOverdraftAccount(150,100,mockUser);

    }


    @Test(expected = RejectedExecutionException.class)
    public void shouldCreateLimitedWithdrawAccountThenThrowException(){


        when(mockUser.addAccount(any(),anyInt())).thenThrow(RejectedExecutionException.class);
        bank.createLimitedWithdrawAccount(150,100,mockUser);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReachSenderAccountAndCatchItsException ()throws IllegalAccessException {

        when(bank.getUser(1234,1)).thenReturn(mockUser);
        when(mockUser.getAccount(1)).thenThrow(IllegalArgumentException.class);
        bank.jointAccounts(1234,1,1234,1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReachReceivingAccountAndCatchItsException ()throws IllegalAccessException {

        User mockUser2 = mock(User.class);
        when(bank.getUser(1234,1)).thenReturn(mockUser);
        when(mockUser2.addAccount(any(),anyInt())).thenThrow(IllegalArgumentException.class);
        bank.jointAccounts(1234,1,1234,1);

    }


}