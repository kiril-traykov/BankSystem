package bank;

import java.util.HashMap;

public class UserManagement {

    private int nextAccountId = 0;
    private HashMap<Integer,User> users = new HashMap<Integer,User>();

    void putUserInCollection(User newUser,int userId){
        users.put(userId,newUser);
    }

    User getUser(int pin,int userId) throws IllegalAccessException {
        if(!users.containsKey(userId)){
            throw new IllegalArgumentException("No such user found");
        }

        if(users.get(userId).authentication(pin)){
            return users.get(userId);
        }
        else{
            throw new IllegalAccessException("Wrong pin");
        }

    }

    User createUser(int pin,int userId){
        if(users.containsKey(userId)){
            throw new IllegalArgumentException("User with such id already exists");
        }
        User newUser = new User(pin,userId);
        putUserInCollection(newUser,userId);
        return newUser;
    }

    int createStandartAccount(int initialSum,User owner){
        Acc newAccount = new Account(initialSum,++nextAccountId);
        owner.addAccount(newAccount,nextAccountId);
        return nextAccountId;
    }

    int createOverdraftAccount(int initialSum,int overdraft,User owner){
        Acc newAccount = new OverDraft(overdraft,initialSum,++nextAccountId);
        owner.addAccount(newAccount,nextAccountId);
        return nextAccountId;
    }

    int createLimitedWithdrawAccount(int initialSum,int limitation,User owner){
        Acc newAccount = new LimitedWithdrawAcc(limitation,initialSum,++nextAccountId);
        owner.addAccount(newAccount,nextAccountId);
        return nextAccountId;
    }

    void jointAccounts(int receivingUserId,int accountId,int ownerPin,int ownerId) throws IllegalAccessException {
        if(!users.containsKey(receivingUserId)){
            throw new IllegalArgumentException("No receiving user exists");
        }
        User sendingUser = getUser(ownerPin,ownerId);
        users.get(receivingUserId).addAccount(sendingUser.getAccount(accountId),accountId);

    }


}
