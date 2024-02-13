package com.lowleveldesign.crms.Repositories.User;

import com.lowleveldesign.crms.Models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class UserRepo implements IUserRepo{
    private Map<UUID, User> usersMap; //Initialization should be done inside constructor

    private UserRepo( ) {
        this.usersMap  = new HashMap<>();
    }


    @Override
    public User addUser(User user) {
        usersMap.put(user.getUserId(), user);
        return user;
    }

    @Override
    public User getUserById(UUID userId) {
        User clonedUser = new User();
        User user = usersMap.get(userId);

        //This validation should be done at Repo class or service class or both.
        if(user != null) {
            clonedUser.setUserId(user.getUserId());
            clonedUser.setUserName(user.getUserName());
            return clonedUser;
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> clonedUsers = new ArrayList<User>();

        //Immutability
        //Don't return the same object reference that is being stored inside map
        //instead deep clone it.
        for(UUID uuid: usersMap.keySet()){
            User clonedUser = new User();
            User user = usersMap.get(uuid);

            clonedUser.setUserId(user.getUserId());
            clonedUser.setUserName(user.getUserName());

            clonedUsers.add(clonedUser);
        }
        return clonedUsers;
    }
}
